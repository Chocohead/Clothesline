package com.jamieswhiteshirt.clothesline.common.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketDecoder;
import net.minecraft.network.codec.PacketEncoder;
import net.minecraft.network.listener.ClientCommonPacketListener;
import net.minecraft.network.listener.ServerCommonPacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.CustomPayload.Id;
import net.minecraft.network.packet.Packet;

import java.util.function.BiConsumer;

public abstract class MessageChannel<T extends CustomPayload, C> {
    protected final Id<T> id;
    protected final PacketEncoder<RegistryByteBuf, T> serializer;
    protected final PacketDecoder<RegistryByteBuf, T> deserializer;

    public MessageChannel(Id<T> id, PacketEncoder<RegistryByteBuf, T> serializer, PacketDecoder<RegistryByteBuf, T> deserializer) {
        this.id = id;
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public abstract void registerHandler(BiConsumer<C, T> handler);

    public Packet<ClientCommonPacketListener> createClientboundPacket(T msg) {
        return ServerPlayNetworking.createS2CPacket(msg);
    }

    public Packet<ServerCommonPacketListener> createServerboundPacket(T msg) {
        return ClientPlayNetworking.createC2SPacket(msg);
    }
}
