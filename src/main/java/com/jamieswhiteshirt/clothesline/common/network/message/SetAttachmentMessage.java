package com.jamieswhiteshirt.clothesline.common.network.message;

import com.jamieswhiteshirt.clothesline.common.util.BasicAttachment;
import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class SetAttachmentMessage implements CustomPayload {
    public static final Id<SetAttachmentMessage> ID = new Id<>(Identifier.of("clothesline", "set_attachment"));
    public final int networkId;
    public final BasicAttachment attachment;

    public SetAttachmentMessage(int networkId, BasicAttachment attachment) {
        this.networkId = networkId;
        this.attachment = attachment;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(RegistryByteBuf buf) {
        PacketByteBufSerialization.writeNetworkId(buf, networkId);
        PacketByteBufSerialization.writeAttachment(buf, attachment);
    }

    public static SetAttachmentMessage deserialize(RegistryByteBuf buf) {
        return new SetAttachmentMessage(
            PacketByteBufSerialization.readNetworkId(buf),
            PacketByteBufSerialization.readAttachment(buf)
        );
    }
}
