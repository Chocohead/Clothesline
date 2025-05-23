package com.jamieswhiteshirt.clothesline.mixin.server.world;

import com.jamieswhiteshirt.clothesline.common.event.ChunkWatchCallback;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkLoadingManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.storage.VersionedChunkStorage;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerChunkLoadingManager.class)
public abstract class ServerChunkLoadingManagerMixin extends VersionedChunkStorage {
    public ServerChunkLoadingManagerMixin() {
        super(null, null, null, false);
    }

    @Inject(
        at = @At("RETURN"),
        method = "track(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/world/chunk/WorldChunk;)V"
    )
    private static void sendChunkDataPackets(ServerPlayerEntity player, WorldChunk chunk, CallbackInfo ci) {
        ChunkWatchCallback.WATCH.invoker().accept(player.getWorld(), chunk.getPos(), player);
    }

    @Inject(
        at = @At("RETURN"),
        method = "untrack"
    )
    private static void sendWatchPackets(ServerPlayerEntity player, ChunkPos pos, CallbackInfo ci) {
        ChunkWatchCallback.UNWATCH.invoker().accept(player.getWorld(), pos, player);
    }
}
