package vswe.stevescarts.blocks.tileentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.IFluidTank;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import vswe.stevescarts.containers.ContainerDistributor;
import vswe.stevescarts.helpers.DistributorSetting;
import vswe.stevescarts.helpers.DistributorSide;
import vswe.stevescarts.helpers.Localization;
import vswe.stevescarts.helpers.storages.SCTank;
import vswe.stevescarts.init.ModBlocks;
import vswe.stevescarts.network.PacketHandler;
import vswe.stevescarts.network.packets.PacketDistributorTile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TileEntityDistributor extends TileEntityBase implements WorldlyContainer, MenuProvider
{
    private final ArrayList<DistributorSide> sides;
    private boolean dirty;

    private TileEntityManager[] inventories;
    public IItemHandler[] invHandlers = new IItemHandler[6];
    public boolean hasTop;
    public boolean hasBot;

    public final Map<Direction, IFluidHandler> fluidHandlerMap;

    public TileEntityDistributor(BlockPos blockPos, BlockState blockState)
    {
        super(ModBlocks.EXTERNAL_DISTRIBUTOR_TILE.get(), blockPos, blockState);
        dirty = true;
        (sides = new ArrayList<>()).add(new DistributorSide(0, Localization.GUI.DISTRIBUTOR.SIDE_ORANGE, Direction.UP));
        sides.add(new DistributorSide(1, Localization.GUI.DISTRIBUTOR.SIDE_PURPLE, Direction.DOWN));
        sides.add(new DistributorSide(2, Localization.GUI.DISTRIBUTOR.SIDE_YELLOW, Direction.NORTH));
        sides.add(new DistributorSide(3, Localization.GUI.DISTRIBUTOR.SIDE_GREEN, Direction.WEST));
        sides.add(new DistributorSide(4, Localization.GUI.DISTRIBUTOR.SIDE_BLUE, Direction.SOUTH));
        sides.add(new DistributorSide(5, Localization.GUI.DISTRIBUTOR.SIDE_RED, Direction.EAST));
        fluidHandlerMap = new HashMap<>();
        for (Direction facing : Direction.values())
        {
            fluidHandlerMap.put(facing, new IFluidHandler()
            {
                @Override
                public int getTanks()
                {
                    final IFluidTank[] tanks = TileEntityDistributor.this.getTanks(facing);
                    return tanks.length;
                }

                @Nonnull
                @Override
                public FluidStack getFluidInTank(int tank)
                {
                    final IFluidTank[] tanks = TileEntityDistributor.this.getTanks(facing);
                    return tanks[tank].getFluid();
                }

                @Override
                public int getTankCapacity(int tank)
                {
                    final IFluidTank[] tanks = TileEntityDistributor.this.getTanks(facing);
                    return tanks[tank].getCapacity();
                }

                @Override
                public boolean isFluidValid(int tank, @Nonnull FluidStack stack)
                {
                    final IFluidTank[] tanks = TileEntityDistributor.this.getTanks(facing);
                    return tanks[tank].isFluidValid(stack);
                }

                @Override
                public int fill(FluidStack resource, FluidAction action)
                {
                    final IFluidTank[] tanks = TileEntityDistributor.this.getTanks(facing);
                    int amount = 0;
                    for (final IFluidTank tank : tanks)
                    {
                        amount += tank.fill(resource, action);
                    }
                    return amount;
                }

                @Nonnull
                @Override
                public FluidStack drain(FluidStack resource, FluidAction action)
                {
                    return TileEntityDistributor.this.drain(facing, resource, resource.getAmount(), action);
                }

                @Nonnull
                @Override
                public FluidStack drain(int maxDrain, FluidAction action)
                {
                    return TileEntityDistributor.this.drain(facing, FluidStack.EMPTY, maxDrain, action);
                }
            });
        }
    }

    public ArrayList<DistributorSide> getSides()
    {
        return sides;
    }

    @Override
    public void load(@NotNull CompoundTag compoundNBT)
    {
        super.load(compoundNBT);
        for (final DistributorSide side : getSides())
        {
            side.setData(compoundNBT.getInt("Side" + side.getId()));
        }
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag compoundNBT)
    {
        super.saveAdditional(compoundNBT);
        for (final DistributorSide side : getSides())
        {
            compoundNBT.putInt("Side" + side.getId(), side.getData());
        }
    }

    @Override
    public void tick()
    {
        dirty = true;
    }

    protected void sendPacket(final int id)
    {
        sendPacket(id, new byte[0]);
    }

    protected void sendPacket(final int id, final byte data)
    {
        sendPacket(id, new byte[]{data});
    }

    public void sendPacket(final int id, final byte[] data)
    {
        PacketHandler.sendToServer(new PacketDistributorTile(getBlockPos(), id, data));
    }

    public void receivePacket(final int id, final byte[] data, final ServerPlayer player)
    {
        if (id == 0 || id == 1)
        {
            final byte settingId = data[0];
            final byte sideId = data[1];
            if (settingId >= 0 && settingId < DistributorSetting.settings.size() && sideId >= 0 && sideId < getSides().size())
            {
                if (id == 0)
                {
                    getSides().get(sideId).set(settingId);
                }
                else
                {
                    getSides().get(sideId).reset(settingId);
                }
            }
        }
    }
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt)
    {
        handleUpdateTag(pkt.getTag());
    }

    public TileEntityManager[] getInventories()
    {
        if (dirty)
        {
            generateInventories();
            dirty = false;
        }
        return inventories;
    }

    public HashMap<Integer, Integer> getInventorySides()
    {
        TileEntityManager[] managers = getInventories();
        HashMap<Integer, Integer> map = new HashMap<>();
        int id = 0;
        for (int i = 0; i < managers.length; i++)
        {
            for (int j = 0; j < managers[i].getContainerSize(); j++)
            {
                map.put(id++, i);
            }
        }
        return map;
    }

    private void generateInventories()
    {
        final TileEntityManager bot = generateManager(-1);
        final TileEntityManager top = generateManager(1);
        hasTop = (top != null);
        hasBot = (bot != null);
        inventories = populateManagers(top, bot, hasTop, hasBot);
    }

    private TileEntityManager[] populateManagers(TileEntityManager topElement, TileEntityManager botElement, boolean hasTopElement, boolean hasBotElement)
    {
        if (!hasTopElement && !hasBotElement)
        {
            return new TileEntityManager[0];
        }
        if (!hasBotElement)
        {
            return new TileEntityManager[]{topElement};
        }
        if (!hasTopElement)
        {
            return new TileEntityManager[]{botElement};
        }
        return new TileEntityManager[]{botElement, topElement};
    }

    private TileEntityManager generateManager(final int y)
    {
        if (level.getBlockEntity(getBlockPos().offset(0, y, 0)) instanceof TileEntityManager tile)
        {
            return tile;
        }
        return null;
    }

    @Override
    public boolean isUsableByPlayer(final Player entityplayer)
    {
        return level.getBlockEntity(getBlockPos()) == this && entityplayer.distanceToSqr(entityplayer) <= 64.0;
    }

    private int translateSlotId(final int slot, TileEntityManager manager)
    {
        return slot % manager.getContainerSize();
    }

    private TileEntityManager getManagerFromSlotId(final int slot)
    {
        final TileEntityManager[] invs = getInventories();
        int id = getInventorySides().getOrDefault(slot, 0);
        if (!hasTop || !hasBot)
        {
            id = 0;
        }
        if (id < 0 || id >= invs.length)
        {
            return null;
        }
        return invs[id];
    }

    @Override
    public int getContainerSize()
    {
        return Arrays.stream(getInventories()).mapToInt(TileEntityManager::getContainerSize).sum();
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public @NotNull ItemStack getItem(int slot)
    {
        final TileEntityManager manager = getManagerFromSlotId(slot);
        if (manager != null)
        {
            return manager.getItem(translateSlotId(slot, manager));
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount)
    {
        final TileEntityManager manager = getManagerFromSlotId(slot);
        if (manager != null)
        {
            return manager.removeItem(translateSlotId(slot, manager), amount);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot)
    {
        final TileEntityManager manager = getManagerFromSlotId(slot);
        if (manager != null)
        {
            return manager.removeItemNoUpdate(translateSlotId(slot, manager));
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack)
    {
        final TileEntityManager manager = getManagerFromSlotId(slot);
        if (manager != null)
        {
            manager.setItem(translateSlotId(slot, manager), stack);
        }
    }

    @Override
    public boolean stillValid(@NotNull Player player)
    {
        return true;
    }

    private boolean isChunkValid(final DistributorSide side, final TileEntityManager manager, final int chunkId, final boolean top)
    {
        for (final DistributorSetting setting : DistributorSetting.settings)
        {
            if (setting.isEnabled(this) && side.isSet(setting.getId()) && setting.isValid(manager, chunkId, top))
            {
                return true;
            }
        }
        return false;
    }

    //Drain target or if target is empty drain whatever is available.
    private FluidStack drain(final Direction from, @Nonnull FluidStack target, int maxDrain, final IFluidHandler.FluidAction doDrain)
    {
        FluidStack totalDrained = FluidStack.EMPTY;

        final IFluidTank[] tanks = getTanks(from);
        for (IFluidTank tank : tanks) {
            FluidStack contents = tank.getFluid();
            if (contents.isEmpty() || (!target.isEmpty() && !contents.isFluidEqual(target))) {
                continue;
            }

            FluidStack drained = tank.drain(maxDrain, doDrain);
            if (drained.isEmpty()) {
                continue;
            }

            maxDrain -= drained.getAmount();
            if (totalDrained.isEmpty()) {
                totalDrained = drained;
            } else {
                totalDrained.grow(drained.getAmount());
            }

            //Now that we have started to extract "some fluid" we can not accept any other fluid we may find.
            if (target.isEmpty()) {
                target = drained;
            }

            if (maxDrain <= 0) return totalDrained;
        }

        return totalDrained;
    }

    private boolean hasAnyTank(Direction facing)
    {
        return facing != null && getInventories().length > 0 && getTanks(facing).length > 0;
    }

    public SCTank[] getTanks(final Direction direction)
    {
        final TileEntityManager[] invs = getInventories();
        if (invs.length > 0)
        {
            for (final DistributorSide side : getSides())
            {
                if (side.getSide() == direction)
                {
                    final ArrayList<SCTank> tanks = new ArrayList<>();
                    if (hasTop && hasBot)
                    {
                        populateTanks(tanks, side, invs[0], false);
                        populateTanks(tanks, side, invs[1], true);
                    }
                    else if (hasTop)
                    {
                        populateTanks(tanks, side, invs[0], true);
                    }
                    else if (hasBot)
                    {
                        populateTanks(tanks, side, invs[0], false);
                    }
                    return tanks.toArray(new SCTank[tanks.size()]);
                }
            }
        }
        return new SCTank[0];
    }

    private void populateTanks(final ArrayList<SCTank> tanks, final DistributorSide side, final TileEntityManager manager, final boolean top)
    {
        if (manager instanceof final TileEntityLiquid fluid)
        {
            final SCTank[] managerTanks = fluid.getTanks();
            for (int i = 0; i < 4; ++i)
            {
                if (isChunkValid(side, manager, i, top) && !tanks.contains(managerTanks[i]))
                {
                    tanks.add(managerTanks[i]);
                }
            }
        }
    }

    private void populateSlots(final ArrayList<Integer> slotchunks, final DistributorSide side, final TileEntityManager manager, final boolean top)
    {
        if (manager instanceof TileEntityCargo)
        {
            for (int i = 0; i < 4; ++i)
            {
                if (isChunkValid(side, manager, i, top))
                {
                    final int chunkid = i + (top ? 4 : 0);
                    if (!slotchunks.contains(chunkid))
                    {
                        slotchunks.add(chunkid);
                    }
                }
            }
        }
    }

    @Override
    public int[] getSlotsForFace(@NotNull Direction direction)
    {
        final TileEntityManager[] invs = getInventories();
        if (invs.length > 0)
        {
            for (final DistributorSide otherSide : getSides())
            {
                if (otherSide.getFacing() == direction)
                {
                    final ArrayList<Integer> slotchunks = new ArrayList<>();
                    if (hasTop && hasBot)
                    {
                        populateSlots(slotchunks, otherSide, invs[0], false);
                        populateSlots(slotchunks, otherSide, invs[1], true);
                    }
                    else if (hasTop)
                    {
                        populateSlots(slotchunks, otherSide, invs[0], true);
                    }
                    else if (hasBot)
                    {
                        populateSlots(slotchunks, otherSide, invs[0], false);
                    }
                    final int[] ret = new int[slotchunks.size() * 15];
                    int id = 0;
                    for (final int chunkid : slotchunks)
                    {
                        for (int i = 0; i < 15; ++i)
                        {
                            ret[id] = chunkid * 15 + i;
                            ++id;
                        }
                    }
                    return ret;
                }
            }
        }
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int id, @NotNull ItemStack itemStack, @Nullable Direction direction)
    {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int id, @NotNull ItemStack itemStack, @NotNull Direction direction)
    {
        return true;
    }

    @Override
    public void clearContent()
    {
    }

    @Override
    public @NotNull Component getDisplayName()
    {
        return Component.literal("container.distributor");
    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player playerEntity)
    {
        return new ContainerDistributor(id, playerInventory, this, new SimpleContainerData(0));
    }
}
