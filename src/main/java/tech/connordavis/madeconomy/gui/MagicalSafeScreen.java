package tech.connordavis.madeconomy.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tech.connordavis.madeconomy.containers.MagicalSafeContainer;
import tech.connordavis.madeconomy.utils.ModProperties;

public class MagicalSafeScreen extends ContainerScreen<MagicalSafeContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(ModProperties.MOD_ID, "textures/gui/magical_safe.png");

    public MagicalSafeScreen(MagicalSafeContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 174;
        this.ySize = 158;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        this.renderBackground(matrixStack);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
        this.font.drawString(matrixStack, this.title.getString(), 3.0f, 3.0f, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 3.0f, (float) this.ySize - 107, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
    }
}
