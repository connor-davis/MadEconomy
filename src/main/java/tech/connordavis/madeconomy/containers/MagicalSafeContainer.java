package tech.connordavis.madeconomy.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import tech.connordavis.madeconomy.blocks.ModBlocks;
import tech.connordavis.madeconomy.tileentities.MagicalSafeTileEntity;

import java.util.Objects;

public class MagicalSafeContainer extends Container {
    public final MagicalSafeTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public MagicalSafeContainer(final int windowId, final PlayerInventory playerInventory, final MagicalSafeTileEntity tileEntity) {
        super(ModContainerTypes.MAGICAL_SAFE.get(), windowId);

        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        int startX = 61;
        int startY = 13;
        int slotSize = 18;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                this.addSlot(
                        new Slot(tileEntity, (row * 3) + column, startX + (column * slotSize), startY + (row * slotSize)));
            }
        }

        startX = 7;
        startY = 77;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(
                        new Slot(playerInventory, 9 + (row * 3) + column, startX + (column * slotSize), startY + (row * slotSize)));
            }
        }

        startY = 135;

        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * slotSize), startY));
        }
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
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();

            itemStack = itemStack1.copy();

            if (index < 1) {
                if (!this.mergeItemStack(itemStack1, 1, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.canMergeSlot(itemStack1, slot)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemStack;
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
}
