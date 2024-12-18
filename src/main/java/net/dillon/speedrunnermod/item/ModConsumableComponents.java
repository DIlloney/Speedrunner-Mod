package net.dillon.speedrunnermod.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import java.util.List;

import static net.minecraft.component.type.ConsumableComponents.food;

public class ModConsumableComponents {
    public static final ConsumableComponent SPEEDRUNNER_BULK = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0),
                                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1200, 0),
                                    new StatusEffectInstance(StatusEffects.SPEED, 600, 0)
                            )
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HASTE, 500, 1), 0.5F
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.REGENERATION, 200), 0.25F
                    )
            )
            .build();

    public static final ConsumableComponent ROTTEN_SPEEDRUNNER_BULK = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F
                    )
            )
            .consumeEffect(
                        new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0), 0.1F
                    )
            )
            .build();
    public static final ConsumableComponent GOLDEN_CARROT = ConsumableComponents.food()
            .consumeEffect(
                        new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1)
                    )
            )
            .build();
}