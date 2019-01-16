package com.jamieswhiteshirt.clotheslinefabric.common.block;

import com.jamieswhiteshirt.clotheslinefabric.api.*;
import com.jamieswhiteshirt.clotheslinefabric.api.util.MutableSortedIntMap;
import com.jamieswhiteshirt.clotheslinefabric.common.item.ClotheslineItems;
import com.jamieswhiteshirt.clotheslinefabric.common.sound.ClotheslineSoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.class_3954;
import net.minecraft.class_3965;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ClotheslineAnchorBlock extends WallMountedBlock implements class_3954 {
    private static final VoxelShape DOWN  = Block.createCubeShape(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);
    private static final VoxelShape UP    = Block.createCubeShape(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private static final VoxelShape NORTH = Block.createCubeShape(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 16.0D);
    private static final VoxelShape SOUTH = Block.createCubeShape(6.0D, 0.0D, 0.0D, 10.0D, 12.0D, 10.0D);
    private static final VoxelShape WEST  = Block.createCubeShape(6.0D, 0.0D, 6.0D, 16.0D, 12.0D, 10.0D);
    private static final VoxelShape EAST  = Block.createCubeShape(0.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);

    public static final Property<Boolean> CRANK = BooleanProperty.create("crank");

    public ClotheslineAnchorBlock(Settings settings) {
        super(settings);
        setDefaultState(stateFactory.getDefaultState().with(field_11007, WallMountLocation.WALL).with(field_11177, Direction.NORTH).with(Properties.WATERLOGGED, false).with(CRANK, false));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
        builder.with(field_11007, field_11177, Properties.WATERLOGGED, CRANK);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape canCollideWith(BlockState state, BlockView view, BlockPos pos, VerticalEntityPosition verticalEntityPosition) {
        switch (state.get(field_11007)) {
            case FLOOR:
                return DOWN;
            case WALL:
                switch (state.get(field_11177)) {
                    case NORTH:
                        return NORTH;
                    case SOUTH:
                        return SOUTH;
                    case WEST:
                        return WEST;
                    case EAST:
                    default:
                        return EAST;
                }
            case CEILING:
            default:
                return UP;
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState) {
        if (oldState.getBlock() != this) {
            NetworkManager manager = ((NetworkManagerProvider) world).getNetworkManager();
            manager.createNode(pos);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean var5) {
        if (newState.getBlock() != this) {
            NetworkManager manager = ((NetworkManagerProvider) world).getNetworkManager();
            manager.breakNode(null, pos);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (state.get(CRANK)) {
            world.setBlockState(pos, state.with(CRANK, false));
            if (!world.isClient && !player.isCreative()) {
                dropStack(world, pos, new ItemStack(ClotheslineItems.CRANK));
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        NetworkManager manager = ((NetworkManagerProvider) world).getNetworkManager();
        manager.breakNode(player, pos);
        super.onBreak(world, pos, state, player);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, class_3965 hitResult) {
        if (player.getStackInHand(hand).getItem() == ClotheslineItems.CLOTHESLINE) return false;

        if (state.get(CRANK)) {
            NetworkNode node = getNode(world, pos);
            if (node != null) {
                int momentumDelta = getCrankMultiplier(pos, hitResult.method_17784().x, hitResult.method_17784().z, player) * 5;
                NetworkState networkState = node.getNetwork().getState();
                networkState.setMomentum(networkState.getMomentum() + momentumDelta);
            }
            return true;
        }
        return super.activate(state, world, pos, player, hand, hitResult);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        NetworkNode node = getNode(world, pos);
        if (node != null) {
            int momentum = Math.abs(node.getNetwork().getState().getMomentum());
            float pitch = 0.2F + 0.6F * ((float)momentum / NetworkState.MAX_MOMENTUM) + random.nextFloat() * 0.1F;
            if (random.nextInt(12 * NetworkState.MAX_MOMENTUM) < momentum) {
                world.playSound(MinecraftClient.getInstance().player, pos, ClotheslineSoundEvents.BLOCK_CLOTHESLINE_ANCHOR_SQUEAK, SoundCategory.BLOCK, 0.1F, pitch);
            }
        }
    }

    @Nullable
    private static NetworkNode getNode(World world, BlockPos pos) {
        return ((NetworkManagerProvider) world).getNetworkManager().getNetworks().getNodes().get(pos);
    }

    public static int getCrankMultiplier(BlockPos pos, double hitX, double hitZ, PlayerEntity player) {
        // Distance vector from the player to the center of the block
        double dxCenter = 0.5D + pos.getX() - player.x;
        double dzCenter = 0.5D + pos.getZ() - player.z;
        // Distance vector from the player to the hit
        double dxHit = hitX - player.x;
        double dzHit = hitZ - player.z;
        // Y component the cross product of the two vectors
        // The sign of the Y component indicates which "side" of the anchor is hit, which determines which way to crank
        double y = dzCenter * dxHit - dxCenter * dzHit;
        return (int) Math.signum(y);
    }

    @Override
    public SidedInventory method_17680(BlockState state, IWorld world, BlockPos pos) {
        return new ClotheslineAnchorInventory(((NetworkManagerProvider) world).getNetworkManager(), pos);
    }

    private class ClotheslineAnchorInventory implements SidedInventory {
        private final NetworkManager manager;
        private final BlockPos pos;

        private ClotheslineAnchorInventory(NetworkManager manager, BlockPos pos) {
            this.manager = manager;
            this.pos = pos;
        }

        @Nullable
        public NetworkNode getNetworkNode() {
            return manager.getNetworks().getNodes().get(pos);
        }

        @Override
        public int[] getInvAvailableSlots(Direction direction) {
            NetworkNode node = getNetworkNode();
            if (node != null && !node.getNetwork().getState().getPath().isEmpty()) {
                int attachmentKey = node.getNetwork().getState().offsetToAttachmentKey(node.getPathNode().getOffsetForDelta(direction.getVector()));
                List<MutableSortedIntMap.Entry<ItemStack>> entries = node.getNetwork().getState().getAttachmentsInRange(
                    attachmentKey - AttachmentUnit.UNITS_PER_BLOCK / 2,
                    attachmentKey + AttachmentUnit.UNITS_PER_BLOCK / 2
                );

                if (entries.isEmpty()) {
                    return new int[] { attachmentKey };
                } else {
                    return new int[] { entries.get(entries.size() / 2).getKey() };
                }
            }
            return new int[0];
        }

        @Override
        public boolean canInsertInvStack(int slot, ItemStack stack, @Nullable Direction direction) {
            NetworkNode node = getNetworkNode();
            if (node != null) {
                return node.getNetwork().insertItem(slot, stack, true).isEmpty();
            }
            return false;
        }

        @Override
        public boolean canExtractInvStack(int slot, ItemStack stack, Direction direction) {
            return getNetworkNode() != null;
        }

        @Override
        public int getInvSize() {
            NetworkNode node = getNetworkNode();
            if (node != null) {
                return node.getNetwork().getState().getPathLength();
            }
            return 0;
        }

        @Override
        public boolean isInvEmpty() {
            NetworkNode node = getNetworkNode();
            if (node != null) {
                return node.getNetwork().getState().getAttachments().size() > 0;
            }
            return false;
        }

        @Override
        public ItemStack getInvStack(int slot) {
            NetworkNode node = getNetworkNode();
            if (node != null) {
                return node.getNetwork().getAttachment(slot);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack takeInvStack(int slot, int amount) {
            return removeInvStack(slot);
        }

        @Override
        public ItemStack removeInvStack(int slot) {
            NetworkNode node = getNetworkNode();
            if (node != null) {
                return node.getNetwork().extractItem(slot, false);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public void setInvStack(int slot, ItemStack stack) {
            NetworkNode node = getNetworkNode();
            if (node != null) {
                node.getNetwork().insertItem(slot, stack, false);
            }
        }

        @Override
        public void markDirty() { }

        @Override
        public boolean canPlayerUseInv(PlayerEntity var1) {
            return false;
        }

        @Override
        public void clearInv() {
            manager.removeNode(pos);
        }

        @Override
        public int getInvMaxStackAmount() {
            return 1;
        }
    }
}
