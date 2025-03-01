package com.jamieswhiteshirt.clothesline.client.network;

import com.jamieswhiteshirt.clothesline.client.network.messagehandler.*;
import com.jamieswhiteshirt.clothesline.common.network.MessageChannels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

@Environment(EnvType.CLIENT)
public class ClientMessageHandling {
    public static void init() {
        // TODO: Migrate to ClientPlayNetworking!
        ClientSidePacketRegistry registry = ClientSidePacketRegistry.INSTANCE;
        MessageChannels.ADD_NETWORK.registerHandler(registry, new AddNetworkMessageHandler());
        MessageChannels.REMOVE_ATTACHMENT.registerHandler(registry, new RemoveAttachmentMessageHandler());
        MessageChannels.REMOVE_NETWORK.registerHandler(registry, new RemoveNetworkMessageHandler());
        MessageChannels.RESET_CONNECTOR_STATE.registerHandler(registry, new ResetConnectorStateMessageHandler());
        MessageChannels.SET_ATTACHMENT.registerHandler(registry, new SetAttachmentMessageHandler());
        MessageChannels.SET_CONNECTOR_STATE.registerHandler(registry, new SetConnectorStateMessageHandler());
        MessageChannels.UPDATE_NETWORK.registerHandler(registry, new UpdateNetworkMessageHandler());
    }
}
