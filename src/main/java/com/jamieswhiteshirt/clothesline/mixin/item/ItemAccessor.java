package com.jamieswhiteshirt.clothesline.mixin.item;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * "Why does this interface exist? Why aren't you using the recipe remainder in Item.Settings?"
 *
 * There is currently no convenient way to make an Item its own recipe remainder. This is caused by a circular
 * dependency - the Item requires the Item.Settings which requires the Item which requires the Item.Settings...
 *
 * This method is reserved for that case only.
 */
@Mixin(Item.class)
public interface ItemAccessor {
    @Accessor("recipeRemainder")
    @Mutable
    void clothesline$setRecipeRemainder(Item item);
}
