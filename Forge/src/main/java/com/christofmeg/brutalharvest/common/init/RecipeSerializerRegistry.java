package com.christofmeg.brutalharvest.common.init;

import com.christofmeg.brutalharvest.CommonConstants;
import com.christofmeg.brutalharvest.common.recipe.ShapelessRecipeDamageTool;
import com.christofmeg.brutalharvest.common.recipe.ShapelessRecipeWithRemainder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class RecipeSerializerRegistry {

    public static void init(@Nonnull IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
    }

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CommonConstants.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> SHAPELESS_RECIPE_WITH_REMAINDER = register("shapeless_with_remainder", ShapelessRecipeWithRemainder.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> SHAPELESS_DAMAGE_TOOL = register("shapeless_damage_tool", ShapelessRecipeDamageTool.Serializer::new);

    private static RegistryObject<RecipeSerializer<?>> register(String name, Supplier<RecipeSerializer<?>> serializer) {
        return RECIPE_SERIALIZERS.register(name, serializer);
    }

}
