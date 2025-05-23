package com.jamieswhiteshirt.clothesline.common.network.message;

import com.jamieswhiteshirt.clothesline.common.util.BasicNetwork;
import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class AddNetworkMessage implements CustomPayload {
    public static final Id<AddNetworkMessage> ID = new Id<>(Identifier.of("clothesline", "add_network"));
    public final BasicNetwork network;

    public AddNetworkMessage(BasicNetwork network) {
        this.network = network;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(RegistryByteBuf buf) {
        PacketByteBufSerialization.writeNetwork(buf, network);
    }

    public static AddNetworkMessage deserialize(RegistryByteBuf buf) {
        return new AddNetworkMessage(
            PacketByteBufSerialization.readNetwork(buf)
        );
    }
}
