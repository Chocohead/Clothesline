package com.jamieswhiteshirt.clothesline.client.network.messagehandler;

import com.jamieswhiteshirt.clothesline.api.Network;
import com.jamieswhiteshirt.clothesline.api.NetworkManager;
import com.jamieswhiteshirt.clothesline.api.NetworkManagerProvider;
import com.jamieswhiteshirt.clothesline.common.network.message.RemoveAttachmentMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;

import net.minecraft.item.ItemStack;

import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class RemoveAttachmentMessageHandler implements BiConsumer<Context, RemoveAttachmentMessage> {
    @Override
    public void accept(Context ctx, RemoveAttachmentMessage msg) {
        NetworkManager manager = ((NetworkManagerProvider) ctx.player().getWorld()).getNetworkManager();
        Network network = manager.getNetworks().getById(msg.networkId);
        if (network != null) {
            network.setAttachment(msg.attachmentKey, ItemStack.EMPTY);
        }
    }
}
