package com.jamieswhiteshirt.clothesline.common.network;

import com.jamieswhiteshirt.clothesline.common.network.messagehandler.HitAttachmentMessageHandler;
import com.jamieswhiteshirt.clothesline.common.network.messagehandler.HitNetworkMessageHandler;
import com.jamieswhiteshirt.clothesline.common.network.messagehandler.StopUsingItemOnMessageHandler;
import com.jamieswhiteshirt.clothesline.common.network.messagehandler.TryUseItemOnNetworkMessageHandler;

public class ServerMessageHandling {
    public static void init() {
        MessageChannels.HIT_ATTACHMENT.registerHandler(new HitAttachmentMessageHandler());
        MessageChannels.HIT_NETWORK.registerHandler(new HitNetworkMessageHandler());
        MessageChannels.STOP_USING_ITEM_ON.registerHandler(new StopUsingItemOnMessageHandler());
        MessageChannels.TRY_USE_ITEM_ON_NETWORK.registerHandler(new TryUseItemOnNetworkMessageHandler());
    }
}
