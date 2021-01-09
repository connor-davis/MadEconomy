package tech.connordavis.madeconomy.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tech.connordavis.madeconomy.MadEconomy;
import tech.connordavis.madeconomy.containers.MagicalSafeContainer;

import javax.annotation.Nonnull;

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
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        this.renderBackground(matrixStack);
        this.drawMouseoverTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY) {
        super.drawForeground(matrixStack, mouseX, mouseY);

        this.textRenderer.draw(matrixStack, this.title.getString(), 3.0f, 3.0f, 16777215);
        this.textRenderer.draw(matrixStack, this.playerInventory.getDisplayName().getString(), 3.0f, (float) this.ySize - 92, 16777215);
    }

    @Override
    protected void drawBackground(@Nonnull MatrixStack matrixStack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        this.getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        this.drawTexture(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
    }

}
