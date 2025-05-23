package com.jamieswhiteshirt.clothesline.common.network.message;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class ResetConnectorStateMessage implements CustomPayload {
    public static final Id<ResetConnectorStateMessage> ID = new Id<>(Identifier.of("clothesline", "reset_connector_state"));
    public final int entityId;

    public ResetConnectorStateMessage(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void serialize(PacketByteBuf buf) {
        buf.writeInt(entityId);
    }

    public static ResetConnectorStateMessage deserialize(PacketByteBuf buf) {
        return new ResetConnectorStateMessage(
            buf.readInt()
        );
    }
}
