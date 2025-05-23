package com.jamieswhiteshirt.clothesline.common.network.message;

import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class RemoveNetworkMessage implements CustomPayload {
    public static final Id<RemoveNetworkMessage> ID = new Id<>(Identifier.of("clothesline", "remove_network"));
    public final int networkId;

    public RemoveNetworkMessage(int networkId) {
        this.networkId = networkId;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(PacketByteBuf buf) {
        PacketByteBufSerialization.writeNetworkId(buf, networkId);
    }

    public static RemoveNetworkMessage deserialize(PacketByteBuf buf) {
        return new RemoveNetworkMessage(
            PacketByteBufSerialization.readNetworkId(buf)
        );
    }
}
