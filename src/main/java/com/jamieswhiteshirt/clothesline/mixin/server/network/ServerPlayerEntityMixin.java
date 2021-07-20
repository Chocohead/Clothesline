package com.jamieswhiteshirt.clothesline.mixin.server.network;

import com.jamieswhiteshirt.clothesline.common.event.TrackEntityCallback;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    // FIXME: Not sure if this mixin works but it seems important
    @Inject(
        at = @At("TAIL"),
        method = "onStartedTrackingBy(Lnet/minecraft/server/network/ServerPlayerEntity;)V"
    )
    private void onStartedTrackingBy(ServerPlayerEntity entity, CallbackInfo ci) {
        TrackEntityCallback.START.invoker().accept((ServerPlayerEntity) (Object) this, entity);
    }
}
