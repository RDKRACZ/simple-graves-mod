package com.speechrezz.simple_gravestones.registry;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class GravestoneBlockEntity extends BlockEntity implements ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(50, ItemStack.EMPTY);
    private int experience = 0;
    private String playerName = "null";

    public GravestoneBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.GRAVE_BLOCK_ENTITY, pos, state);
    }

    //From the ImplementedInventory Interface

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
        this.experience = nbt.getInt("Experience");
        this.playerName = nbt.getString("PlayerName");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putInt("Experience", this.experience);
        nbt.putString("PlayerName", this.playerName);
        //nbt.putUuid("PlayerUUID", this.playerUUID);
        return nbt;
    }

    public void setExperience(int exp){
        this.experience = exp;
        //System.out.println("DEBUG - GravestoneBlockEntity's Exp: "+ experience);
    }

    public int getExperience(){
        return this.experience;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
