package net.dillon.speedrunnermod.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.List;

/**
 * THe speedrunners totem death protection component. Applies better effects than a normal totem of undying would.
 */
public record SpeedrunnersDeathProtectionComponent(List<ConsumeEffect> deathEffects) {
    public static final Codec<SpeedrunnersDeathProtectionComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(ConsumeEffect.CODEC.listOf().optionalFieldOf("death_effects", List.of()).forGetter(SpeedrunnersDeathProtectionComponent::deathEffects))
                    .apply(instance, SpeedrunnersDeathProtectionComponent::new)
    );
    public static final PacketCodec<RegistryByteBuf, SpeedrunnersDeathProtectionComponent> PACKET_CODEC = PacketCodec.tuple(
            ConsumeEffect.PACKET_CODEC.collect(PacketCodecs.toList()), SpeedrunnersDeathProtectionComponent::deathEffects, SpeedrunnersDeathProtectionComponent::new
    );
    public static final SpeedrunnersDeathProtectionComponent SPEEDRUNNERS_TOTEM = new SpeedrunnersDeathProtectionComponent(
            List.of(
                    new ClearAllEffectsConsumeEffect(),
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.REGENERATION, TickCalculator.minutes(2), 1),
                                    new StatusEffectInstance(StatusEffects.ABSORPTION, TickCalculator.seconds(30), 2),
                                    new StatusEffectInstance(StatusEffects.STRENGTH, TickCalculator.minutes(1), 1),
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, TickCalculator.minutes(2), 0),
                                    new StatusEffectInstance(StatusEffects.RESISTANCE, TickCalculator.minutes(1), 0),
                                    new StatusEffectInstance(StatusEffects.INVISIBILITY, TickCalculator.minutes(1), 0)
                            )
                    )
            )
    );

    public void applyDeathEffects(ItemStack stack, LivingEntity entity) {
        for (ConsumeEffect consumeEffect : this.deathEffects) {
            consumeEffect.onConsume(entity.getWorld(), stack, entity);
        }
    }
}