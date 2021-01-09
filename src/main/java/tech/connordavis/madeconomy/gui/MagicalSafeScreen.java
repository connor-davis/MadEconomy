package tech.connordavis.madeconomy.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tech.connordavis.madeconomy.MadEconomy;
import tech.connordavis.madeconomy.containers.MagicalSafeContainer;

public class MagicalSafeScreen extends ContainerScreen<MagicalSafeContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(MadEconomy.ID, "textures/gui/magical_safe.png");

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
        this.drawMouseoverTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
        super.drawForeground(p_230451_1_, p_230451_2_, p_230451_3_);
        this.textRenderer.draw(p_230451_1_, this.title.getString(), 3.0f, 3.0f, 16777215);
        this.textRenderer.draw(p_230451_1_, this.playerInventory.getDisplayName().getString(), 3.0f, (float) this.ySize - 92, 16777215);
    }

    @Override
    protected void drawBackground(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        this.drawTexture(p_230450_1_, x, y, 0, 0, this.xSize, this.ySize);
    }

}
