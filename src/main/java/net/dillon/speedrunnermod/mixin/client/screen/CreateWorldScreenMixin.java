package net.dillon.speedrunnermod.mixin.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    @Shadow @Final
    WorldCreator worldCreator;
    @Shadow
    protected abstract void createLevel();

    /**
     * Reworks how the create world button works, and allows the {@code fast world creation} feature to work accordingly.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (options().client.fastWorldCreation.getCurrentValue()) {

            Difficulty difficulty = null;
            switch (options().client.difficulty) {
                case PEACEFUL:
                    difficulty = Difficulty.PEACEFUL;
                    break;
                case EASY:
                    difficulty = Difficulty.EASY;
                    break;
                case NORMAL:
                    difficulty = Difficulty.NORMAL;
                    break;
                case HARD:
                    difficulty = Difficulty.HARD;
                    break;
            }

            WorldCreator.Mode gameMode = null;
            switch (options().client.gameMode) {
                case SURVIVAL:
                    gameMode = WorldCreator.Mode.SURVIVAL;
                    break;
                case CREATIVE:
                    gameMode = WorldCreator.Mode.CREATIVE;
                    break;
                case HARDCORE:
                    gameMode = WorldCreator.Mode.HARDCORE;
                    break;
                case SPECTATOR:
                    gameMode = WorldCreator.Mode.DEBUG;
                    break;
            }

            assert gameMode != null;
            assert difficulty != null;
            this.worldCreator.setGameMode(gameMode);
            this.worldCreator.setDifficulty(difficulty);
            this.worldCreator.setCheatsEnabled(options().client.allowCheats.getCurrentValue());
            createLevel();
        }
    }
}