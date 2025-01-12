package vswe.stevescarts.modules.addons;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.player.Player;
import vswe.stevescarts.api.modules.template.ModuleAddon;
import vswe.stevescarts.api.modules.template.ModuleEngine;
import vswe.stevescarts.client.guis.GuiMinecart;
import vswe.stevescarts.entities.EntityMinecartModular;
import vswe.stevescarts.helpers.Localization;
import vswe.stevescarts.helpers.ResourceHelper;
import vswe.stevescarts.init.ModSerializers;
import vswe.stevescarts.init.ModSerializers.ShortArray;

public class ModulePowerObserver extends ModuleAddon
{
    private final EntityDataAccessor<ShortArray> AREA_DATA = createDw(ModSerializers.SHORT_ARRAY.get());
    private final EntityDataAccessor<ShortArray> POWER_LEVEL = createDw(ModSerializers.SHORT_ARRAY.get());
    private int currentEngine;

    public ModulePowerObserver(final EntityMinecartModular cart)
    {
        super(cart);
        currentEngine = -1;
    }

    @Override
    public void initDw() {
        registerDw(AREA_DATA, new ShortArray(4));
        registerDw(POWER_LEVEL, new ShortArray(4));
    }

    public short[] getAreaData() {
        return getDw(AREA_DATA).getArray();
    }

    public short[] getPowerLevel() {
        return getDw(POWER_LEVEL).getArray();
    }

    public void setAreaData(short[] shorts) {
        updateDw(AREA_DATA, new ShortArray(shorts));
    }
    public void setPowerLevel(short[] shorts) {
        updateDw(POWER_LEVEL, new ShortArray(shorts));
    }

    @Override
    public int numberOfDataWatchers() {
        return 2;
    }

    @Override
    public boolean hasGui()
    {
        return true;
    }

    @Override
    public boolean hasSlots()
    {
        return false;
    }

    @Override
    public int guiWidth()
    {
        return 190;
    }

    @Override
    public int guiHeight()
    {
        return 150;
    }

    @Override
    public void drawForeground(GuiGraphics guiGraphics, GuiMinecart gui)
    {
        drawString(guiGraphics, gui, getModuleName(), 8, 6, 4210752);
        for (int i = 0; i < 4; ++i)
        {
            final int[] rect = getPowerRect(i);
            drawString(guiGraphics, gui, getPowerLevel()[i] + Localization.MODULES.ADDONS.K.translate(new String[0]), rect, 4210752);
        }
    }

    private boolean removeOnPickup()
    {
        return true;
    }

    @Override
    public void drawBackground(GuiGraphics guiGraphics, GuiMinecart gui, final int x, final int y) {
        for (int i = 0; i < getCart().getEngines().size(); ++i) {
            if (!removeOnPickup() || currentEngine != i) {
                drawEngine(guiGraphics, gui, i, getEngineRect(i));
            }
        }
        ResourceHelper.bindResource("/gui/observer.png");
        for (int i = 0; i < 4; ++i) {
            int[] rect = getAreaRect(i);
            drawImage(guiGraphics, gui, rect, 18, 22 * i);
            if (inRect(x, y, rect)) {
                drawImage(guiGraphics, gui, rect, 18, 22 * (i + 4));
            }
            int count = 0;
            for (int j = 0; j < getCart().getEngines().size(); ++j) {
                if ((getAreaData()[i] & 1 << j) != 0x0) {
                    drawEngine(guiGraphics, gui, j, getEngineRectInArea(i, count));
                    ++count;
                }
            }
            ResourceHelper.bindResource("/gui/observer.png");
            rect = getPowerRect(i);
            if (isAreaActive(i)) {
                drawImage(guiGraphics, gui, rect, 122, 0);
            } else {
                drawImage(guiGraphics, gui, rect, 122 + rect[2], 0);
            }
            if (inRect(x, y, rect)) {
                drawImage(guiGraphics, gui, rect, 122 + rect[2] * 2, 0);
            }
        }
        if (currentEngine != -1) {
            drawEngine(guiGraphics, gui, currentEngine, getEngineRectMouse(x, y + getCart().getRealScrollY()));
        }
    }

    private void drawEngine(GuiGraphics guiGraphics, GuiMinecart gui, int id, int[] rect)
    {
        ModuleEngine engine = getCart().getEngines().get(id);
        int initialHeight = rect[3];
        rect = cloneRect(rect);
        int offset = 0;
        if (!doStealInterface())
        {
            offset = handleScroll(rect);
        }
        if (rect[3] <= 0) return;
        if (initialHeight != rect[3]) {
            gui.pushScissor();
        }
        gui.drawModuleIcon(guiGraphics, engine.getItemStack(), gui.getGuiLeft() + getX() + rect[0], gui.getGuiTop() + getY() + rect[1] + offset, 0, 0, 0, 0);
        if (initialHeight != rect[3]) {
            gui.popScissor();
        }
    }

    private int[] getAreaRect(final int id)
    {
        return new int[]{10, 40 + 25 * id, 104, 22};
    }

    private int[] getEngineRect(final int id)
    {
        return new int[]{11 + id * 20, 21, 16, 16};
    }

    private int[] getEngineRectMouse(final int x, final int y)
    {
        return new int[]{x - 8, y - 8, 16, 16};
    }

    private int[] getEngineRectInArea(final int areaid, final int number)
    {
        final int[] area = getAreaRect(areaid);
        return new int[]{area[0] + 4 + number * 20, area[1] + 3, 16, 16};
    }

    private int[] getPowerRect(final int areaid)
    {
        final int[] area = getAreaRect(areaid);
        return new int[]{area[0] + area[2] + 10, area[1] + 2, 35, 18};
    }

    @Override
    public void drawMouseOver(GuiGraphics guiGraphics, GuiMinecart gui, final int x, final int y)
    {
        for (int i = 0; i < getCart().getEngines().size(); ++i)
        {
            if (!removeOnPickup() || currentEngine != i)
            {
                final ModuleEngine engine = getCart().getEngines().get(i);
                drawStringOnMouseOver(guiGraphics, gui, engine.getData().getName() + "\n" + Localization.MODULES.ADDONS.OBSERVER_INSTRUCTION.translate(), x, y, getEngineRect(i));
            }
        }
        for (int i = 0; i < 4; ++i)
        {
            int count = 0;
            for (int j = 0; j < getCart().getEngines().size(); ++j)
            {
                if ((getAreaData()[i] & 1 << j) != 0x0)
                {
                    final ModuleEngine engine2 = getCart().getEngines().get(j);
                    drawStringOnMouseOver(guiGraphics, gui, engine2.getData().getName() + "\n" + Localization.MODULES.ADDONS.OBSERVER_REMOVE.translate(), x, y, getEngineRectInArea(i, count));
                    ++count;
                }
            }
            if (currentEngine != -1)
            {
                drawStringOnMouseOver(guiGraphics, gui, Localization.MODULES.ADDONS.OBSERVER_DROP.translate(), x, y, getAreaRect(i));
            }
            drawStringOnMouseOver(guiGraphics, gui, Localization.MODULES.ADDONS.OBSERVER_CHANGE.translate() + "\n" + Localization.MODULES.ADDONS.OBSERVER_CHANGE_10.translate(), x, y, getPowerRect(i));
        }
    }

    @Override
    public int numberOfGuiData()
    {
        return 8;
    }

    @Override
    public int numberOfPackets()
    {
        return 3;
    }

    @Override
    protected void receivePacket(final int id, final byte[] data, final Player player)
    {
        if (id == 0)
        {
            final int area = data[0];
            final int engine = data[1];
            final short[] areaData = this.getAreaData();
            final int n = area;
            areaData[n] |= (short) (1 << engine);
            setAreaData(areaData);
        }
        else if (id == 1)
        {
            final int area = data[0];
            final int engine = data[1];
            final short[] areaData2 = getAreaData();
            final int n2 = area;
            areaData2[n2] &= (short) ~(1 << engine);
            setAreaData(areaData2);
        }
        else if (id == 2)
        {
            final int area = data[0];
            final int button = data[1] & 0x1;
            final boolean shift = (data[1] & 0x2) != 0x0;
            int change = (button == 0) ? 1 : -1;
            if (shift)
            {
                change *= 10;
            }
            short[] powerLevel = getPowerLevel();
            short value = powerLevel[area];
            value += (short) change;
            if (value < 0)
            {
                value = 0;
            }
            else if (value > 999)
            {
                value = 999;
            }
            powerLevel[area] = value;
            setPowerLevel(powerLevel);
        }
    }

    @Override
    public void mouseReleased(GuiMinecart gui, int x, int y, int button) {
        if (button != -1) {
            if (button == 0) {
                for (int i = 0; i < 4; ++i) {
                    final int[] rect = getAreaRect(i);
                    if (inRect(x, y, rect)) {
                        sendPacket(0, new byte[]{(byte) i, (byte) currentEngine});
                        break;
                    }
                }
            }
            currentEngine = -1;
        }
    }

    @Override
    public void mouseClicked(final GuiMinecart gui, final int x, final int y, final int button)
    {
        for (int i = 0; i < 4; ++i)
        {
            final int[] rect = getPowerRect(i);
            if (inRect(x, y, rect))
            {
                sendPacket(2, new byte[]{(byte) i, (byte) (button | (Screen.hasShiftDown() ? 2 : 0))});
                break;
            }
        }
        if (button == 0)
        {
            for (int i = 0; i < getCart().getEngines().size(); ++i)
            {
                final int[] rect = getEngineRect(i);
                if (inRect(x, y, rect))
                {
                    currentEngine = i;
                    break;
                }
            }
        }
        else if (button == 1)
        {
            for (int i = 0; i < 4; ++i)
            {
                int count = 0;
                for (int j = 0; j < getCart().getEngines().size(); ++j)
                {
                    if ((getAreaData()[i] & 1 << j) != 0x0)
                    {
                        final int[] rect2 = getEngineRectInArea(i, count);
                        if (inRect(x, y, rect2))
                        {
                            sendPacket(1, new byte[]{(byte) i, (byte) j});
                            break;
                        }
                        ++count;
                    }
                }
            }
        }
    }

    public boolean isAreaActive(final int area)
    {
        int power = 0;
        for (int i = 0; i < getCart().getEngines().size(); ++i)
        {
            final ModuleEngine engine = getCart().getEngines().get(i);
            if ((getAreaData()[area] & 1 << i) != 0x0)
            {
                power += engine.getTotalFuel();
            }
        }
        return power > getPowerLevel()[area] * 1000;
    }

    @Override
    protected void Save(final CompoundTag tagCompound, final int id) {
        for (int i = 0; i < 4; ++i) {
            tagCompound.putShort(generateNBTName("AreaData" + i, id), getAreaData()[i]);
            tagCompound.putShort(generateNBTName("PowerLevel" + i, id), getPowerLevel()[i]);
        }
    }

    @Override
    protected void Load(final CompoundTag tagCompound, final int id) {
        short[] areaData = new short[4];
        short[] powerLevel = new short[4];
        for (int i = 0; i < 4; ++i) {
            areaData[i] = tagCompound.getShort(generateNBTName("AreaData" + i, id));
            powerLevel[i] = tagCompound.getShort(generateNBTName("PowerLevel" + i, id));
        }
        setAreaData(areaData);
        setPowerLevel(powerLevel);
    }
}
