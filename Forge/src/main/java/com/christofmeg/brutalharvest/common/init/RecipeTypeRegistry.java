package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.recipe.ShapelessRecipeWithRemainder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public class RecipeTypeRegistry {

    public static void init(@Nonnull IEventBus modEventBus) {
        RECIPE_TYPES.register(modEventBus);
    }

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, CommonConstants.MOD_ID);
    public static final RegistryObject<RecipeType<ShapelessRecipeWithRemainder>> CUSTOM_SHAPELESS_RECIPE;

    static {
        CUSTOM_SHAPELESS_RECIPE = RECIPE_TYPES.register("custom_shapeless",
                () -> new RecipeType<>() {
                    @Override
                    public String toString() {
                        return new ResourceLocation(CommonConstants.MOD_ID, "custom_shapeless").toString();
                    }
                });
    }

}
