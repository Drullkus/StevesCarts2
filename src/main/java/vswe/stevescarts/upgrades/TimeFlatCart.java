package vswe.stevescarts.upgrades;

import vswe.stevescarts.api.upgrades.BaseUpgradeEffect;
import vswe.stevescarts.helpers.Localization;

public class TimeFlatCart extends BaseUpgradeEffect
{
    private final int ticks;

    public TimeFlatCart(final int ticks)
    {
        this.ticks = ticks;
    }

    @Override
    public String getName()
    {
        return Localization.UPGRADES.CART_FLAT.translate(((getSeconds() >= 0) ? "+" : "") + getSeconds(), String.valueOf(getSeconds()));
    }

    protected int getSeconds()
    {
        return ticks / 20;
    }

    public int getTicks()
    {
        return ticks;
    }
}
