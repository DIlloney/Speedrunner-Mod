package net.dillon.speedrunnermod.mixin.plugin;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@ChatGPT(Credit.FULL_CREDIT)
public class ConditionalMixinPlugin implements IMixinConfigPlugin {

    /**
     * Disables the {@link net.dillon.speedrunnermod.mixin.client.Fog} mixin from loading if the {@code apply fog mixin} advanced option is disabled.
     */
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!options().advanced.applyFogMixin && mixinClassName.equals("net.dillon.speedrunnermod.mixin.client.Fog")) {
            SpeedrunnerMod.warn("Speedrunner Mod Fog mixin has been disabled.");
            return false;
        } else {
            return true;
        }
    }

    // Other methods...
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}