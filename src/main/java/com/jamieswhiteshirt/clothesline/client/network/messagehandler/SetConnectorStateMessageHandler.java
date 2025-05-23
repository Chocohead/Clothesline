package com.jamieswhiteshirt.clothesline.client.network.messagehandler;

import com.jamieswhiteshirt.clothesline.common.network.message.SetConnectorStateMessage;
import com.jamieswhiteshirt.clothesline.internal.ConnectorHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;

import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class SetConnectorStateMessageHandler implements BiConsumer<Context, SetConnectorStateMessage> {
    @Override
    public void accept(Context ctx, SetConnectorStateMessage msg) {
        Entity entity = msg.entityId != -1 ? ctx.player().getWorld().getEntityById(msg.entityId) : null;
        if (entity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entity;
            ConnectorHolder connectorHolder = (ConnectorHolder) entity;
            connectorHolder.clothesline$setFrom(new ItemUsageContext(
                playerEntity,
                msg.hand,
                msg.hitResult
            ));
        }
    }
}
