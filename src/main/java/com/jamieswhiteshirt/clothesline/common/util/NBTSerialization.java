package com.jamieswhiteshirt.clothesline.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

public class NBTSerialization {
    public static NbtList writePersistentNetworks(WrapperLookup registries, List<BasicPersistentNetwork> networks) {
        NbtList nbt = new NbtList();
        for (BasicPersistentNetwork network : networks) {
            nbt.add(writePersistentNetwork(registries, network));
        }
        return nbt;
    }

    public static List<BasicPersistentNetwork> readPersistentNetworks(WrapperLookup registries, NbtList nbt) {
        BasicPersistentNetwork[] networks = new BasicPersistentNetwork[nbt.size()];
        for (int i = 0; i < nbt.size(); i++) {
            networks[i] = readPersistentNetwork(registries, nbt.getCompound(i));
        }
        return Arrays.asList(networks);
    }

    public static NbtCompound writePersistentNetwork(WrapperLookup registries, BasicPersistentNetwork network) {
        NbtCompound nbt = new NbtCompound();
        nbt.putUuid("Uuid", network.getUuid());
        nbt.put("State", writeNetworkState(registries, network.getState()));
        return nbt;
    }

    public static BasicPersistentNetwork readPersistentNetwork(WrapperLookup registries, NbtCompound compound) {
        return new BasicPersistentNetwork(
            compound.getUuid("Uuid"),
            readNetworkState(registries, compound.getCompound("State"))
        );
    }

    public static NbtCompound writeNetworkState(WrapperLookup registries, BasicNetworkState state) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("Shift", state.getShift());
        nbt.putInt("Momentum", state.getMomentum());
        nbt.put("Tree", writeBasicTree(state.getTree()));
        nbt.put("Attachments", writeAttachments(registries, state.getAttachments()));
        return nbt;
    }

    public static BasicNetworkState readNetworkState(WrapperLookup registries, NbtCompound nbt) {
        return new BasicNetworkState(
            nbt.getInt("Shift"),
            nbt.getInt("Momentum"),
            readBasicTree(nbt.getCompound("Tree")),
            readAttachments(registries, nbt.getList("Attachments", NbtElement.COMPOUND_TYPE))
        );
    }

    public static NbtCompound writeBasicTree(BasicTree tree) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("x", tree.getPos().getX());
        nbt.putInt("y", tree.getPos().getY());
        nbt.putInt("z", tree.getPos().getZ());
        nbt.put("Children", writeBasicTreeEdges(tree.getEdges()));
        nbt.putInt("BaseRotation", tree.getBaseRotation());
        return nbt;
    }

    public static BasicTree readBasicTree(NbtCompound nbt) {
        return new BasicTree(
            new BlockPos(
                nbt.getInt("x"),
                nbt.getInt("y"),
                nbt.getInt("z")
            ),
            readBasicTreeEdges(nbt.getList("Children", NbtElement.COMPOUND_TYPE)),
            nbt.getInt("BaseRotation")
        );
    }

    public static NbtList writeBasicTreeEdges(List<BasicTree.Edge> edges) {
        NbtList nbt = new NbtList();
        for (BasicTree.Edge edge : edges) {
            nbt.add(writeBasicTreeEdge(edge));
        }
        return nbt;
    }

    public static List<BasicTree.Edge> readBasicTreeEdges(NbtList nbt) {
        BasicTree.Edge[] edges = new BasicTree.Edge[nbt.size()];
        for (int i = 0; i < nbt.size(); i++) {
            edges[i] = readBasicTreeEdge(nbt.getCompound(i));
        }
        return Arrays.asList(edges);
    }

    public static NbtCompound writeBasicTreeEdge(BasicTree.Edge edge) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("Length", edge.getLength());
        nbt.put("Tree", writeBasicTree(edge.getTree()));
        return nbt;
    }

    public static BasicTree.Edge readBasicTreeEdge(NbtCompound nbt) {
        return new BasicTree.Edge(
            nbt.getInt("Length"),
            readBasicTree(nbt.getCompound("Tree"))
        );
    }

    public static NbtList writeAttachments(WrapperLookup registries, List<BasicAttachment> attachments) {
        NbtList nbt = new NbtList();
        for (BasicAttachment attachment : attachments) {
            nbt.add(writeAttachment(registries, attachment));
        }
        return nbt;
    }

    public static List<BasicAttachment> readAttachments(WrapperLookup registries, NbtList nbt) {
        BasicAttachment[] attachments = new BasicAttachment[nbt.size()];
        for (int i = 0; i < nbt.size(); i++) {
            attachments[i] = readAttachment(registries, nbt.getCompound(i));
        }
        return Arrays.asList(attachments);
    }

    public static NbtCompound writeAttachment(WrapperLookup registries, BasicAttachment attachment) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("Offset", attachment.getKey());
        nbt.put("Stack", attachment.getStack().encodeAllowEmpty(registries));
        return nbt;
    }

    public static BasicAttachment readAttachment(WrapperLookup registries, NbtCompound nbt) {
        return new BasicAttachment(
            nbt.getInt("Offset"),
            ItemStack.fromNbtOrEmpty(registries, nbt.getCompound("Stack"))
        );
    }
}
