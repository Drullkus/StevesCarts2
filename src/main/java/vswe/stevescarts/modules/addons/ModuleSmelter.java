package vswe.stevescarts.modules.addons;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vswe.stevescarts.client.guis.GuiMinecart;
import vswe.stevescarts.api.slots.SlotStevesCarts;
import vswe.stevescarts.containers.slots.SlotCartCrafterResult;
import vswe.stevescarts.containers.slots.SlotFurnaceInput;
import vswe.stevescarts.entities.EntityMinecartModular;
import vswe.stevescarts.helpers.RecipeHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class ModuleSmelter extends ModuleRecipe
{
    private int energyBuffer;
    private int cooldown;

    public ModuleSmelter(final EntityMinecartModular cart)
    {
        super(cart);
        cooldown = 0;
    }

    @Override
    public void update()
    {
        if (getCart().level().isClientSide)
        {
            return;
        }
        if (getCart().hasFuelForModule() && energyBuffer < 10)
        {
            ++energyBuffer;
        }
        if (cooldown <= 0)
        {
            if (energyBuffer == 10)
            {
                @Nonnull ItemStack recipe = getStack(0);
                @Nonnull ItemStack result = ItemStack.EMPTY;
                if (!recipe.isEmpty())
                {
                    if (getSmeltingResult() != null) result = getSmeltingResult();
                }
                if (!result.isEmpty())
                {
                    result = result.copy();
                }
                if (!result.isEmpty() && getCart().getModules() != null)
                {
                    prepareLists();
                    if (canCraftMoreOfResult(result))
                    {
                        final NonNullList<ItemStack> originals = NonNullList.create();
                        for (int i = 0; i < allTheSlots.size(); ++i)
                        {
                            @Nonnull ItemStack item = allTheSlots.get(i).getItem();
                            originals.add((item.isEmpty()) ? ItemStack.EMPTY : item.copy());
                        }
                        int i = 0;
                        while (i < inputSlots.size())
                        {
                            @Nonnull ItemStack item = inputSlots.get(i).getItem();
                            if (!item.isEmpty() && ItemStack.isSameItem(item, recipe) && ItemStack.isSameItemSameTags(item, recipe))
                            {
                                @Nonnull ItemStack itemStack = item;
                                itemStack.shrink(1);
                                if (itemStack.getCount() <= 0)
                                {
                                    inputSlots.get(i).set(ItemStack.EMPTY);
                                }
                                getCart().addItemToChest(result, getValidSlot(), null);
                                if (result.getCount() != 0)
                                {
                                    for (int j = 0; j < allTheSlots.size(); ++j)
                                    {
                                        allTheSlots.get(j).set(originals.get(j));
                                    }
                                    break;
                                }
                                energyBuffer = 0;
                                break;
                            }
                            else
                            {
                                ++i;
                            }
                        }
                    }
                }
            }
            cooldown = 40;
        }
        else
        {
            --cooldown;
        }
    }

    @Nullable
    public SmeltingRecipe getRecipeSmelting()
    {
        return RecipeHelper.findSmeltRecipe(getStack(0), getCart().level()).map(RecipeHolder::value).orElse(null);
    }

    @Nullable
    public ItemStack getSmeltingResult()
    {
        AbstractCookingRecipe recipe = getRecipeSmelting();
        if (recipe != null)
        {
            return recipe.getResultItem(RegistryAccess.EMPTY).copy();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getConsumption(final boolean isMoving)
    {
        if (energyBuffer < 10)
        {
            return 15;
        }
        return super.getConsumption(isMoving);
    }

    @Override
    public boolean hasGui()
    {
        return true;
    }

    @Override
    protected int getInventoryWidth()
    {
        return 1;
    }

    @Override
    protected int getInventoryHeight()
    {
        return 2;
    }

    @Override
    protected SlotStevesCarts getSlot(final int slotId, final int x, final int y)
    {
        if (y == 0)
        {
            return new SlotFurnaceInput(getCart(), slotId, 10 + 18 * x, 15 + 18 * y);
        }
        return new SlotCartCrafterResult(getCart(), slotId, 10 + 18 * x, 15 + 18 * y);
    }

    @Override
    public int numberOfGuiData()
    {
        return super.numberOfGuiData() + 1;
    }

    @Override
    protected void checkGuiData(final Object[] info)
    {
        super.checkGuiData(info);
        updateGuiData(info, super.numberOfGuiData() + 0, (short) energyBuffer);
    }

    @Override
    public void receiveGuiData(final int id, final short data)
    {
        super.receiveGuiData(id, data);
        if (id == super.numberOfGuiData() + 0)
        {
            energyBuffer = data;
        }
    }

    @Override
    public void onInventoryChanged()
    {
        super.onInventoryChanged();
        if (getCart().level().isClientSide)
        {
            if (!getStack(0).isEmpty() && !getSmeltingResult().isEmpty())
            {
                setStack(1, getSmeltingResult());
            }
            else
            {
                setStack(1, ItemStack.EMPTY);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawForeground(GuiGraphics guiGraphics, GuiMinecart gui)
    {
        super.drawForeground(guiGraphics, gui);
        drawString(guiGraphics, gui, getModuleName(), 8, 6, 4210752);
    }

    @Override
    public int guiWidth()
    {
        return canUseAdvancedFeatures() ? 100 : 45;
    }

    @Override
    protected int[] getArea()
    {
        return new int[]{32, 25, 16, 16};
    }

    @Override
    protected boolean canUseAdvancedFeatures()
    {
        return false;
    }

    @Override
    protected void Load(final CompoundTag tagCompound, final int id)
    {
        super.Load(tagCompound, id);
        energyBuffer = tagCompound.getByte(generateNBTName("Buffer", id));
    }

    @Override
    protected void Save(final CompoundTag tagCompound, final int id)
    {
        super.Save(tagCompound, id);
        tagCompound.putByte(generateNBTName("Buffer", id), (byte) energyBuffer);
    }

    @Override
    protected int getLimitStartX()
    {
        return 55;
    }

    @Override
    protected int getLimitStartY()
    {
        return 15;
    }
}
