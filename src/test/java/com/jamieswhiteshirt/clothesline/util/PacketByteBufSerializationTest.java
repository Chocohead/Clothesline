package com.jamieswhiteshirt.clothesline.util;

import com.jamieswhiteshirt.clothesline.common.util.BasicNetwork;
import com.jamieswhiteshirt.clothesline.common.util.PacketByteBufSerialization;
import io.netty.buffer.Unpooled;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.DynamicRegistryManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PacketByteBufSerializationTest {
    @Test
    void persistsNetworkEquality() {
        BasicNetwork written = BasicNetwork.fromAbsolute(NetworkTests.ab.network);
        RegistryByteBuf buf = new RegistryByteBuf(Unpooled.buffer(), DynamicRegistryManager.EMPTY);
        PacketByteBufSerialization.writeNetwork(buf, written);
        BasicNetwork read = PacketByteBufSerialization.readNetwork(buf);
        Assertions.assertEquals(written, read);
    }
}
