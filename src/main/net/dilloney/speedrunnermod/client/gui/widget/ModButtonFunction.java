package net.dilloney.speedrunnermod.client.gui.widget;

import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.world.CreateWorldScreen.class)
public abstract class ModButtonFunction {

    @Shadow
    public boolean hardcore;

    @Shadow
    private Difficulty field_24289;

    @Shadow
    private Difficulty field_24290;

    @Shadow
    abstract void createLevel();

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (SpeedrunnerModClient.clOptions().autoCreateWorld) {
            Difficulty difficulty = null;

            switch (SpeedrunnerModClient.clOptions().worldDifficulty) {
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
                case HARDCORE:
                    difficulty = Difficulty.HARD;
                    hardcore = true;
                    break;
            }

            assert difficulty != null;
            field_24289 = difficulty;
            field_24290 = difficulty;
            createLevel();
        }
    }
}