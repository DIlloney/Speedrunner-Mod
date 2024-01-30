package net.dillon8775.speedrunnermod.client.gui.widget;

import net.dillon8775.speedrunnermod.SpeedrunnerModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (SpeedrunnerModClient.clOptions().fastWorldCreation) {

            Difficulty difficulty = null;
            switch (SpeedrunnerModClient.clOptions().difficulty) {
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
            switch (SpeedrunnerModClient.clOptions().gameMode) {
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
            cheatsEnabled = SpeedrunnerModClient.clOptions().allowCheats;
            createLevel();
        }
    }
}