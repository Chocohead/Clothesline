package com.jamieswhiteshirt.clothesline.common.network;

import java.util.function.BiConsumer;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketDecoder;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.CustomPayload.Id;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;

public final class ServerboundMessageChannel<T extends CustomPayload> extends MessageChannel<T, Context> {
	public ServerboundMessageChannel(Id<T> id, BiConsumer<T, RegistryByteBuf> serializer, PacketDecoder<RegistryByteBuf, T> deserializer) {
		super(id, (RegistryByteBuf buf, T msg) -> serializer.accept(msg, buf), deserializer);

		PayloadTypeRegistry.playC2S().register(this.id, PacketCodec.ofStatic(this.serializer, this.deserializer));
	}

	@Override
	public void registerHandler(BiConsumer<Context, T> handler) {
		ServerPlayNetworking.registerGlobalReceiver(id, (msg, ctx) -> {
			handler.accept(ctx, msg);
		});
	}
}