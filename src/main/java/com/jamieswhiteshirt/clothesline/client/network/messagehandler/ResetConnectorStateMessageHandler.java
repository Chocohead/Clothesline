package com.jamieswhiteshirt.clothesline.client.network.messagehandler;

import com.jamieswhiteshirt.clothesline.common.network.message.ResetConnectorStateMessage;
import com.jamieswhiteshirt.clothesline.internal.ConnectorHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class ResetConnectorStateMessageHandler implements BiConsumer<Context, ResetConnectorStateMessage> {
    @Override
    public void accept(Context ctx, ResetConnectorStateMessage msg) {
        Entity entity = msg.entityId != -1 ? ctx.player().getWorld().getEntityById(msg.entityId) : null;
        if (entity instanceof PlayerEntity) {
            ConnectorHolder connectorHolder = (ConnectorHolder) entity;
            connectorHolder.clothesline$setFrom(null);
        }
    }
}
