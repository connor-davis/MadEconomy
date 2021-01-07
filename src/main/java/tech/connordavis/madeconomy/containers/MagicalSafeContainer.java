package tech.connordavis.madeconomy.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.tileentities.MagicalSafeTileEntity;

import java.util.Objects;

public class MagicalSafeContainer extends Container {
    public final MagicalSafeTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public MagicalSafeContainer(final int windowId, final PlayerInventory playerInventory, final MagicalSafeTileEntity tileEntity) {
        super(ModContainerTypes.MAGICAL_SAFE.get(), windowId);

        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
        this.playerEntity = playerInventory.player;
        this.playerInventory = new InvWrapper(playerInventory);

        int startX = 61;
        int startY = 13;
        int slotSize = 18;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                this.addSlot(
                        new Slot(tileEntity, (row * 3) + column, startX + (column * slotSize), startY + (row * slotSize)));
            }
        }

        layoutPlayerInventorySlots(7, 77);
    }

    public MagicalSafeContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(canInteractWithCallable, player, ModBlocks.MAGICAL_SAFE.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 9) {
                if (!this.mergeItemStack(itemstack1, 9, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    public static MagicalSafeTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player inventory can't be null");
        Objects.requireNonNull(data, "PacketBuffer can't be null");

        final TileEntity tileEntityAtPosition = playerInventory.player.world.getTileEntity(data.readBlockPos());

        if (tileEntityAtPosition instanceof MagicalSafeTileEntity) {
            return (MagicalSafeTileEntity) tileEntityAtPosition;
        }

        throw new IllegalStateException("TileEntity is incorrect " + tileEntityAtPosition);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}
