package tech.connordavis.madeconomy.tileentities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import tech.connordavis.madeconomy.tileentities.MagicalSafeTileEntity;

public class MagicalSafeRenderer extends TileEntityRenderer<MagicalSafeTileEntity> {
    public MagicalSafeRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(MagicalSafeTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }
}
