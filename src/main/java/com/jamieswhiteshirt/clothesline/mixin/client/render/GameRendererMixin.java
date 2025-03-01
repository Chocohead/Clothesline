package com.jamieswhiteshirt.clothesline.mixin.client.render;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import com.jamieswhiteshirt.clothesline.api.NetworkManager;
import com.jamieswhiteshirt.clothesline.api.NetworkManagerProvider;
import com.jamieswhiteshirt.clothesline.client.raycast.NetworkRaycastHit;
import com.jamieswhiteshirt.clothesline.client.raycast.NetworkRaycastHitEntity;
import com.jamieswhiteshirt.clothesline.client.raycast.Ray;
import com.jamieswhiteshirt.clothesline.client.raycast.Raycasting;
import com.jamieswhiteshirt.clothesline.client.render.ClotheslineShader;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Shader;
import net.minecraft.entity.Entity;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/profiler/Profiler;pop()V"
        ),
        method = "updateTargetedEntity(F)V"
    )
    private void updateTargetedEntity(float tickDelta, CallbackInfo ci) {
        World world = client.world;
        Entity cameraEntity = client.getCameraEntity();
        HitResult hitResult = client.crosshairTarget;
        if (hitResult != null) {
            NetworkManager manager = ((NetworkManagerProvider) world).getNetworkManager();
            Vec3d rayFrom = cameraEntity.getCameraPosVec(tickDelta);
            Vec3d rayTo = hitResult.getPos();

            Ray ray = new Ray(rayFrom, rayTo);

            NetworkRaycastHit hit = Raycasting.raycastNetworks(manager, ray, ray.lengthSq, tickDelta);
            if (hit != null) {
                NetworkRaycastHitEntity hitResultEntity = new NetworkRaycastHitEntity(world, hit);
                client.crosshairTarget = new EntityHitResult(hitResultEntity);
                client.targetedEntity = hitResultEntity;
            }
        }
    }

	@Inject(
		method = "loadShaders",
		at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", remap = false, ordinal = 0),
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void registerShaders(ResourceManager manager, CallbackInfo call, List<?> shaderStages, List<Pair<Shader, Consumer<Shader>>> programs) throws IOException {
		ClotheslineShader shader = new ClotheslineShader(manager);
		programs.add(Pair.of(shader, shader));
	}
}
