package net.dillon8775.speedrunnermod.mixin.main.world;

import net.dillon8775.speedrunnermod.world.biome.ModBiomes;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BuiltinBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BuiltinBiomes.class)
public class BuiltinBiomesMixin {

    /**
     * Fixes an error where the speedrunner's wasteland biome is registered twice.
     */
    @Inject(method = "getDefaultBiome", at = @At("HEAD"))
    private static void addSpeedrunnersWasteland(Registry<Biome> registry, CallbackInfoReturnable<RegistryEntry<Biome>> cir) {
        BuiltinRegistries.add(registry, ModBiomes.SPEEDRUNNERS_WASTELAND_KEY, ModBiomes.createSpeedrunnersWasteland());
    }
}