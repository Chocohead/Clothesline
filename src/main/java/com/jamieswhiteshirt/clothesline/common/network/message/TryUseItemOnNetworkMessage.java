package com.jamieswhiteshirt.clothesline.common.network.message;

import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class TryUseItemOnNetworkMessage implements CustomPayload {
    public static final Id<TryUseItemOnNetworkMessage> ID = new Id<>(Identifier.of("clothesline", "try_use_item_on_network"));
    public final Hand hand;
    public final int networkId;
    public final int attachmentKey;

    public TryUseItemOnNetworkMessage(Hand hand, int networkId, int attachmentKey) {
        this.hand = hand;
        this.networkId = networkId;
        this.attachmentKey = attachmentKey;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(PacketByteBuf buf) {
        buf.writeEnumConstant(hand);
        PacketByteBufSerialization.writeNetworkId(buf, networkId);
        buf.writeInt(attachmentKey);
    }

    public static TryUseItemOnNetworkMessage deserialize(PacketByteBuf buf) {
        return new TryUseItemOnNetworkMessage(
            buf.readEnumConstant(Hand.class),
            PacketByteBufSerialization.readNetworkId(buf),
            buf.readInt()
        );
    }
}
