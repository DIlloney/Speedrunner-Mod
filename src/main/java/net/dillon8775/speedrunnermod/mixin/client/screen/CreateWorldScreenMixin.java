package net.dillon8775.speedrunnermod.mixin.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    @Shadow
    private CreateWorldScreen.Mode currentMode;
    @Shadow
    private boolean cheatsEnabled;
    @Shadow
    private Difficulty currentDifficulty;
    @Shadow
    abstract void createLevel();

    /**
     * Reworks how the create world button works, and allows the {@code fast world creation} feature to work accordingly.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (options().client.fastWorldCreation) {

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

            CreateWorldScreen.Mode gameMode = null;
            switch (options().client.gameMode) {
                case SURVIVAL:
                    gameMode = CreateWorldScreen.Mode.SURVIVAL;
                    break;
                case CREATIVE:
                    gameMode = CreateWorldScreen.Mode.CREATIVE;
                    break;
                case HARDCORE:
                    gameMode = CreateWorldScreen.Mode.HARDCORE;
                    break;
                case SPECTATOR:
                    gameMode = CreateWorldScreen.Mode.DEBUG;
                    break;
            }

            assert gameMode != null;
            assert difficulty != null;
            currentMode = gameMode;
            currentDifficulty = difficulty;
            cheatsEnabled = options().client.allowCheats;
            createLevel();
        }
    }
}