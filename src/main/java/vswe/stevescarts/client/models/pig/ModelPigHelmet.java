package vswe.stevescarts.client.models.pig;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import vswe.stevescarts.api.client.ModelCartbase;
import vswe.stevescarts.api.modules.ModuleBase;
import vswe.stevescarts.helpers.ResourceHelper;

public class ModelPigHelmet extends ModelCartbase
{
    public ModelPigHelmet(final boolean isOverlay)
    {
        super(getTexturedModelData().bakeRoot(), ResourceHelper.getResource("/models/minecart.png"));
    }

    public static LayerDefinition getTexturedModelData()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        //TODO
        //        this.isOverlay = isOverlay;
        //        final ModelRenderer Headwear = new ModelRenderer(this, 0, 0);
        //        AddRenderer(Headwear);
        //        Headwear.addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f);
        //        Headwear.setPos(-12.2f + (isOverlay ? 0.2f : 0.0f), -5.4f, 0.0f);
        //        Headwear.yRot = 1.5707964f;
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack p_225598_1_, @NotNull VertexConsumer p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_)
    {
        super.renderToBuffer(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

    //	@Override
    //	public void render(final Render render, final ModuleBase module, final float yaw, final float pitch, final float roll, final float mult, final float partialtime) {
    //		if (render == null || module == null) {
    //			return;
    //		}
    //		final ModulePig pig = (ModulePig) module;
    //		if (!pig.hasHelment() || (isOverlay && !pig.getHelmetMultiRender())) {
    //			return;
    //		}
    //		final float sizemult = 1.09375f + (isOverlay ? 0.020833334f : 0.0f);
    //		GlStateManager.scale(sizemult, sizemult, sizemult);
    //		if (pig.hasHelmetColor(isOverlay)) {
    //			final int color = pig.getHelmetColor(isOverlay);
    //			final float red = (color >> 16 & 0xFF) / 255.0f;
    //			final float green = (color >> 8 & 0xFF) / 255.0f;
    //			final float blue = (color & 0xFF) / 255.0f;
    //			GlStateManager.color(red, green, blue);
    //		}
    //		super.render(render, module, yaw, pitch, roll, mult, partialtime);
    //		GlStateManager.color(1.0f, 1.0f, 1.0f);
    //		GlStateManager.scale(1.0f / sizemult, 1.0f / sizemult, 1.0f / sizemult);
    //	}
}
