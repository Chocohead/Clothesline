package com.jamieswhiteshirt.clothesline.common.network.message;

import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class HitAttachmentMessage implements CustomPayload {
    public static final Id<HitAttachmentMessage> ID = new Id<>(Identifier.of("clothesline", "hit_attachment"));
    public final int networkId;
    public final int attachmentKey;

    public HitAttachmentMessage(int networkId, int attachmentKey) {
        this.networkId = networkId;
        this.attachmentKey = attachmentKey;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(PacketByteBuf buf) {
        PacketByteBufSerialization.writeNetworkId(buf, networkId);
        buf.writeInt(attachmentKey);
    }

    public static HitAttachmentMessage deserialize(PacketByteBuf buf) {
        return new HitAttachmentMessage(
            PacketByteBufSerialization.readNetworkId(buf),
            buf.readInt()
        );
    }
}
