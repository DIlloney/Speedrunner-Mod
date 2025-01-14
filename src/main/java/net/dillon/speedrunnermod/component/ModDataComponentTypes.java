package net.dillon.speedrunnermod.component;

import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;

public class ModDataComponentTypes {
    public static final ComponentType<SpeedrunnersDeathProtectionComponent> DEATH_PROTECTION = DataComponentTypes.register(
            "speedrunnermod:speedrunners_death_protection", builder -> builder.codec(SpeedrunnersDeathProtectionComponent.CODEC).packetCodec(SpeedrunnersDeathProtectionComponent.PACKET_CODEC).cache()
    );
}