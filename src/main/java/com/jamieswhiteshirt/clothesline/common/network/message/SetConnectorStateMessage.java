package com.jamieswhiteshirt.clothesline.common.network.message;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;

public class SetConnectorStateMessage implements CustomPayload {
    public static final Id<SetConnectorStateMessage> ID = new Id<>(Identifier.of("clothesline", "set_connector_state"));
    public final int entityId;
    public final Hand hand;
    public final BlockHitResult hitResult;

    public SetConnectorStateMessage(int entityId, Hand hand, BlockHitResult hitResult) {
        this.entityId = entityId;
        this.hand = hand;
        this.hitResult = hitResult;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(PacketByteBuf buf) {
        buf.writeInt(entityId);
        buf.writeEnumConstant(hand);
        buf.writeBlockHitResult(hitResult);
    }

    public static SetConnectorStateMessage deserialize(PacketByteBuf buf) {
        return new SetConnectorStateMessage(
            buf.readInt(),
            buf.readEnumConstant(Hand.class),
            buf.readBlockHitResult()
        );
    }
}
