package com.jamieswhiteshirt.clothesline.client.network.messagehandler;

import com.jamieswhiteshirt.clothesline.api.NetworkManager;
import com.jamieswhiteshirt.clothesline.api.NetworkManagerProvider;
import com.jamieswhiteshirt.clothesline.common.network.message.AddNetworkMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;

import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class AddNetworkMessageHandler implements BiConsumer<Context, AddNetworkMessage> {
    @Override
    public void accept(Context ctx, AddNetworkMessage msg) {
        NetworkManager manager = ((NetworkManagerProvider) ctx.player().getWorld()).getNetworkManager();
        manager.getNetworks().add(msg.network.toAbsolute());
    }
}
