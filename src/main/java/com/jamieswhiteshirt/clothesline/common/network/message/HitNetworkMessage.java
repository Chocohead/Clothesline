package com.jamieswhiteshirt.clothesline.common.network.message;

import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class HitNetworkMessage implements CustomPayload {
    public static final Id<HitNetworkMessage> ID = new Id<>(Identifier.of("clothesline", "hit_network"));
    public final int networkId;
    public final int attachmentKey;
    public final int offset;

    public HitNetworkMessage(int networkId, int attachmentKey, int offset) {
        this.networkId = networkId;
        this.attachmentKey = attachmentKey;
        this.offset = offset;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(PacketByteBuf buf) {
        PacketByteBufSerialization.writeNetworkId(buf, networkId);
        buf.writeInt(attachmentKey);
        buf.writeInt(offset);
    }

    public static HitNetworkMessage deserialize(PacketByteBuf buf) {
        return new HitNetworkMessage(
            PacketByteBufSerialization.readNetworkId(buf),
            buf.readInt(),
            buf.readInt()
        );
    }
}
