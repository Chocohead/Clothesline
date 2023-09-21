package com.jamieswhiteshirt.clothesline.client.audio;

import com.jamieswhiteshirt.clothesline.api.NetworkState;
import com.jamieswhiteshirt.clothesline.api.Path;
import com.jamieswhiteshirt.clothesline.common.sound.ClotheslineSoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class ClotheslineRopeSoundInstance extends AbstractSoundInstance implements TickableSoundInstance {
    private final NetworkState state;
    private final Path.Node node;

    public ClotheslineRopeSoundInstance(NetworkState state, Path.Node node) {
        super(ClotheslineSoundEvents.BLOCK_CLOTHESLINE_ANCHOR_ROPE, SoundCategory.BLOCKS, Random.create());
        this.state = state;
        this.node = node;

        this.repeat = true;
        this.x = node.getPos().getX() + 0.5F;
        this.y = node.getPos().getY() + 0.5F;
        this.z = node.getPos().getZ() + 0.5F;

        // tick();
    }

    @Override
    public void tick() {
        float momentum = Math.abs(state.getMomentum()) / 30.0F;
        this.volume = (2 + node.getEdges().size()) * momentum * 0.2F;
        this.pitch = 0.25F + momentum * 0.75F;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
