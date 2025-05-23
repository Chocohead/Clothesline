package com.jamieswhiteshirt.clothesline.client.network;

import com.jamieswhiteshirt.clothesline.client.network.messagehandler.*;
import com.jamieswhiteshirt.clothesline.common.network.MessageChannels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClientMessageHandling {
    public static void init() {
        MessageChannels.ADD_NETWORK.registerHandler(new AddNetworkMessageHandler());
        MessageChannels.REMOVE_ATTACHMENT.registerHandler(new RemoveAttachmentMessageHandler());
        MessageChannels.REMOVE_NETWORK.registerHandler(new RemoveNetworkMessageHandler());
        MessageChannels.RESET_CONNECTOR_STATE.registerHandler(new ResetConnectorStateMessageHandler());
        MessageChannels.SET_ATTACHMENT.registerHandler(new SetAttachmentMessageHandler());
        MessageChannels.SET_CONNECTOR_STATE.registerHandler(new SetConnectorStateMessageHandler());
        MessageChannels.UPDATE_NETWORK.registerHandler(new UpdateNetworkMessageHandler());
    }
}
