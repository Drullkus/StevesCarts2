package vswe.stevescarts.containers.slots;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import vswe.stevescarts.api.slots.SlotStevesCarts;
import vswe.stevescarts.modules.workers.tools.ModuleWoodcutter;

import javax.annotation.Nonnull;

public class SlotSapling extends SlotStevesCarts
{
    private ModuleWoodcutter module;

    public SlotSapling(final Container iinventory, final ModuleWoodcutter module, final int i, final int j, final int k)
    {
        super(iinventory, i, j, k);
        this.module = module;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack itemstack)
    {
        return itemstack.is(ItemTags.SAPLINGS);
    }
}
