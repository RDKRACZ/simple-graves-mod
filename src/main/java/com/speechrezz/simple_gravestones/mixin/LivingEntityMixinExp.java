package com.speechrezz.simple_gravestones.mixin;

import com.speechrezz.simple_gravestones.registry.GravestoneBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinExp{

    @Inject(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;dropXp()V", shift = At.Shift.BEFORE), cancellable = true)
    private void dropXp(DamageSource source, CallbackInfo info) {
        //BlockPos blockPos = new BlockPos(this.getPos());
        if (((Object) this) instanceof ServerPlayerEntity player) {

            System.out.println("DEBUG - Exp: " + player.totalExperience);
            ((GravestoneBlockEntity) player.getEntityWorld().getBlockEntity(player.getBlockPos())).setExperience(player.totalExperience);
            //((GravestoneBlockEntity) player.getEntityWorld().getBlockEntity(player.getBlockPos())).setExperience(player.totalExperience);

            player.setExperienceLevel(0);
            player.setExperiencePoints(0);
        }
    }
}