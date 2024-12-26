package net.dillon.speedrunnermod.mixin.main.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.TeleportRandomlyConsumeEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ConsumableComponents.class)
public abstract class ConsumableComponentsMixin {
    @Shadow
    public static final ConsumableComponent CHORUS_FRUIT, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE;

    static {
        CHORUS_FRUIT = ConsumableComponents.food()
                .consumeEffect(new TeleportRandomlyConsumeEffect()
                )
                .consumeEffect(
                        new ApplyEffectsConsumeEffect(
                                new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0)
                        )
                )

                .build();
        ENCHANTED_GOLDEN_APPLE = ConsumableComponents.food()
                .consumeEffect(
                        new ApplyEffectsConsumeEffect(
                                List.of(
                                        new StatusEffectInstance(StatusEffects.REGENERATION, 600, 2),
                                        new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0),
                                        new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0),
                                        new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3)
                                )
                        )
                )
                .build();
        GOLDEN_APPLE = ConsumableComponents.food()
                .consumeEffect(
                        new ApplyEffectsConsumeEffect(
                                List.of(
                                        new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1),
                                        new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0)
                                )
                        )
                )
                .build();
    }
}