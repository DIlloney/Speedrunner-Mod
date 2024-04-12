package net.dillon.speedrunnermod.mixin.main.boat;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModBoatTypes;
import net.dillon.speedrunnermod.util.Author;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Allows for custom boat entity types to be added to the game.
 * <p>Without this, the game crashes because it thinks {@link BoatEntity.Type} is null.</p>
 */
@Author("Anxietie")
@Mixin(BoatEntity.Type.class)
public class BoatEntityTypeMixin {
    @SuppressWarnings("ShadowTarget") @Shadow
    @Mutable
    private static @Final BoatEntity.Type[] field_7724;
    @Unique
    private static final List<BoatEntity.Type> $TYPES = new ArrayList<>();

    @Invoker("<init>")
    private static BoatEntity.Type Type(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError();
    }

    /**
     * Registers the basics for a boat, including the base block, ordinal name, etc.
     */
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addType(CallbackInfo ci) {
        List<BoatEntity.Type> types = new ArrayList<>(Arrays.asList(field_7724));
        int ordinal = types.get(types.size() - 1).ordinal();
        ModBoatTypes.SPEEDRUNNER = initType("SPEEDRUNNER", ++ordinal, ModBlocks.SPEEDRUNNER_PLANKS, "speedrunner");
        ModBoatTypes.CRIMSON = initType("CRIMSON", ++ordinal, Blocks.CRIMSON_PLANKS, "crimson");
        ModBoatTypes.WARPED = initType("WARPED", ++ordinal, Blocks.WARPED_PLANKS, "warped");
        types.addAll($TYPES);
        field_7724 = types.toArray(new BoatEntity.Type[0]);
    }

    @Unique
    private static BoatEntity.Type initType(String internalName, int internalId, Block baseBlock, String name) {
        $TYPES.add(Type(internalName, internalId, baseBlock, name));
        return $TYPES.get($TYPES.size() - 1);
    }

    @Inject(method = "getType(I)Lnet/minecraft/entity/vehicle/BoatEntity$Type;", at = @At("HEAD"), cancellable = true)
    private static void getType(int type, CallbackInfoReturnable<BoatEntity.Type> cir) {
        int n = BoatEntity.Type.values().length - $TYPES.size();
        if (type >= n) cir.setReturnValue($TYPES.get(type - n));
    }
}