package com.jamieswhiteshirt.clothesline.util;

import com.jamieswhiteshirt.clothesline.common.util.BasicPersistentNetwork;
import com.jamieswhiteshirt.clothesline.common.util.NBTSerialization;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.DynamicRegistryManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NBTSerializationTest {
    @Test
    void persistsPersistentNetworkEquality() {
        BasicPersistentNetwork written = BasicPersistentNetwork.fromAbsolute(NetworkTests.ab.persistentNetwork);
        NbtCompound nbtTagCompound = NBTSerialization.writePersistentNetwork(DynamicRegistryManager.EMPTY, written);
        BasicPersistentNetwork read = NBTSerialization.readPersistentNetwork(DynamicRegistryManager.EMPTY, nbtTagCompound);
        Assertions.assertEquals(written, read);
    }
}
