package vswe.stevescarts.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import vswe.stevescarts.helpers.ResourceHelper;
import vswe.stevescarts.modules.ModuleBase;
import vswe.stevescarts.modules.realtimers.ModuleCakeServer;

public class ModelCake extends ModelCartbase
{
    private static ResourceLocation texture;
    private ModelPart[] cakes;

    @Override
    protected int getTextureHeight()
    {
        return 256;
    }

    @Override
    public ResourceLocation getResource(final ModuleBase module)
    {
        return ModelCake.texture;
    }

    public ModelCake()
    {
        //TODO
        //        cakes = new ModelRenderer[6];
        //        for (int i = 0; i < cakes.length; ++i)
        //        {
        //            cakes[i] = createCake(6 - i);
        //        }
    }

    private ModelPart createCake(final int slices)
    {
        //TODO
        //        final ModelRenderer cake = new ModelRenderer(this, 0, 22 * (6 - slices));
        //        AddRenderer(cake);
        //        cake.addBox(-7.0f, -4.0f, -7.0f, 2 * slices + ((slices == 6) ? 2 : 1), 8, 14, 0.0f);
        //        cake.setPos(0.0f, -9.0f, 0.0f);
        //        return cake;

        return null;
    }

    @Override
    public void applyEffects(final ModuleBase module, PoseStack matrixStack, VertexConsumer rtb, final float yaw, final float pitch, final float roll)
    {
        int count;
        if (module != null)
        {
            count = ((ModuleCakeServer) module).getRenderSliceCount();
        }
        else
        {
            count = 6;
        }
        for (int i = 0; i < cakes.length; ++i)
        {
            //            cakes[i].isHidden = (6 - i != count);
        }
    }

    static
    {
        ModelCake.texture = ResourceHelper.getResource("/models/cakeModel.png");
    }
}
