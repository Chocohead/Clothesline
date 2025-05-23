package com.jamieswhiteshirt.clothesline.common.network;

import com.jamieswhiteshirt.clothesline.common.network.message.*;

public class MessageChannels {
    public static final ClientboundMessageChannel<AddNetworkMessage> ADD_NETWORK = new ClientboundMessageChannel<>(
        AddNetworkMessage.ID, AddNetworkMessage::serialize, AddNetworkMessage::deserialize
    );
    public static final ServerboundMessageChannel<HitAttachmentMessage> HIT_ATTACHMENT = new ServerboundMessageChannel<>(
        HitAttachmentMessage.ID, HitAttachmentMessage::serialize, HitAttachmentMessage::deserialize
    );
    public static final ServerboundMessageChannel<HitNetworkMessage> HIT_NETWORK = new ServerboundMessageChannel<>(
        HitNetworkMessage.ID, HitNetworkMessage::serialize, HitNetworkMessage::deserialize
    );
    public static final ClientboundMessageChannel<RemoveAttachmentMessage> REMOVE_ATTACHMENT = new ClientboundMessageChannel<>(
        RemoveAttachmentMessage.ID, RemoveAttachmentMessage::serialize, RemoveAttachmentMessage::deserialize
    );
    public static final ClientboundMessageChannel<RemoveNetworkMessage> REMOVE_NETWORK = new ClientboundMessageChannel<>(
        RemoveNetworkMessage.ID, RemoveNetworkMessage::serialize, RemoveNetworkMessage::deserialize
    );
    public static final ClientboundMessageChannel<ResetConnectorStateMessage> RESET_CONNECTOR_STATE = new ClientboundMessageChannel<>(
        ResetConnectorStateMessage.ID, ResetConnectorStateMessage::serialize, ResetConnectorStateMessage::deserialize
    );
    public static final ClientboundMessageChannel<SetAttachmentMessage> SET_ATTACHMENT = new ClientboundMessageChannel<>(
        SetAttachmentMessage.ID, SetAttachmentMessage::serialize, SetAttachmentMessage::deserialize
    );
    public static final ClientboundMessageChannel<SetConnectorStateMessage> SET_CONNECTOR_STATE = new ClientboundMessageChannel<>(
        SetConnectorStateMessage.ID, SetConnectorStateMessage::serialize, SetConnectorStateMessage::deserialize
    );
    public static final ServerboundMessageChannel<StopUsingItemOnMessage> STOP_USING_ITEM_ON = new ServerboundMessageChannel<>(
        StopUsingItemOnMessage.ID, StopUsingItemOnMessage::serialize, StopUsingItemOnMessage::deserialize
    );
    public static final ServerboundMessageChannel<TryUseItemOnNetworkMessage> TRY_USE_ITEM_ON_NETWORK = new ServerboundMessageChannel<>(
        TryUseItemOnNetworkMessage.ID, TryUseItemOnNetworkMessage::serialize, TryUseItemOnNetworkMessage::deserialize
    );
    public static final ClientboundMessageChannel<UpdateNetworkMessage> UPDATE_NETWORK = new ClientboundMessageChannel<>(
        UpdateNetworkMessage.ID, UpdateNetworkMessage::serialize, UpdateNetworkMessage::deserialize
    );
}
