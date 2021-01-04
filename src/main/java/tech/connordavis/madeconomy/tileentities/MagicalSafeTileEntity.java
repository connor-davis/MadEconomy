package tech.connordavis.madeconomy.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;
import tech.connordavis.madeconomy.items.ModItems;
import tech.connordavis.madeconomy.utils.ModFormatText;
import tech.connordavis.madeconomy.utils.ModProperties;

import javax.annotation.Nullable;

public class MagicalSafeTileEntity extends LockableLootTileEntity {
    private static final int[] SLOTS = new int[]{0};
    protected NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

    public MagicalSafeTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public MagicalSafeTileEntity() {
        this(ModTileEntityTypes.MAGICAL_SAFE.get());
    }

    @Override
    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void markDirty() {
        super.markDirty();

        if (this.world != null) {
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        return
                new StringTextComponent(
                        ModFormatText.format(new TranslationTextComponent("container." + ModProperties.MOD_ID + ".magical_safe").toString()));
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemStack = this.items.get(index);

        boolean flag = !stack.isEmpty()
                && stack.isItemEqual(itemStack)
                && ItemStack.areItemStackTagsEqual(stack, itemStack);

        if (stack.getCount() > this.getSizeInventory()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (!flag) {
            this.markDirty();
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64D;
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return !stack.isItemEqual(ModItems.SILVER_COIN.get().getDefaultInstance());
    }

    @Override
    public void clear() {
        super.clear();
        this.items.clear();
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);

        return new SUpdateTileEntityPacket(this.getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        if (Minecraft.getInstance().world != null) {
            this.read(Minecraft.getInstance().world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
        }
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        return compound;
    }
}
