package com.jamieswhiteshirt.clothesline.common.network.message;

import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class UpdateNetworkMessage implements CustomPayload {
    public static final Id<UpdateNetworkMessage> ID = new Id<>(Identifier.of("clothesline", "update_network"));
    public final int networkId;
    public final int shift;
    public final int momentum;

    public UpdateNetworkMessage(int networkId, int shift, int momentum) {
        this.networkId = networkId;
        this.shift = shift;
        this.momentum = momentum;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(PacketByteBuf buf) {
        PacketByteBufSerialization.writeNetworkId(buf, networkId);
        buf.writeInt(shift);
        buf.writeByte(momentum);
    }

    public static UpdateNetworkMessage deserialize(PacketByteBuf buf) {
        return new UpdateNetworkMessage(
            PacketByteBufSerialization.readNetworkId(buf),
            buf.readInt(),
            buf.readByte()
        );
    }
}
