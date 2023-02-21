package net.dilloney.speedrunnermod.client.gui.widget;

import net.dilloney.speedrunnermod.SpeedrunnerMod;
import net.dilloney.speedrunnermod.SpeedrunnerModClient;
import net.dilloney.speedrunnermod.client.gui.screen.ModMenuScreen;
import net.dilloney.speedrunnermod.client.gui.screen.SocialsScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    public GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Shadow @Final
    private boolean showMenu;

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (showMenu) {
            this.addButton(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 48 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.DILLONEY_YOUTUBE_CHANNEL_LINK);
            }));

            ButtonWidget createWorldButton = this.addButton(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 72 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                if (this.client.inGameHud != null) {
                    this.client.inGameHud.getChatHud().clear(false);
                }
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
                this.client.openScreen(CreateWorldScreen.method_31130(this));
            }));
            createWorldButton.active = SpeedrunnerModClient.clOptions().autoCreateWorld;

            this.addButton(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 96 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                this.client.openScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
            }));

            this.addButton(new ButtonWidget(this.width / 2 + 106, this.height / 4 + 48 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.DISCORD_LINK);
            }));
            this.addButton(new ButtonWidget(this.width / 2 + 106, this.height / 4 + 72 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.TWITTER_LINK);
            }));
            this.addButton(new ButtonWidget(this.width / 2 + 106, this.height / 4 + 96 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                Util.getOperatingSystem().open(SocialsScreen.WEBPAGE_LINK);
            }));
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (showMenu) {
            this.client.getTextureManager().bindTexture(new Identifier("speedrunnermod:textures/gui/speedrunner_mod.png"));
            drawTexture(matrices, this.width / 2 - 4 - 58 - 2, this.height / 4 - 26 + 2, 0.0F, 0.0F, 128, 16, 128, 16);

            this.client.getTextureManager().bindTexture(new Identifier("dilloney:textures/dilloney.png"));
            drawTexture(matrices, this.width / 2 - 4 - 119 - 2, this.height / 4 + 47 - 16 + 2, 0.0F, 0.0F, 18, 18, 18, 18);
            this.client.getTextureManager().bindTexture(new Identifier("speedrunnermod:textures/item/speedrunner_boots.png"));
            drawTexture(matrices, this.width / 2 - 4 - 118 - 2, this.height / 4 + 72 - 16 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            this.client.getTextureManager().bindTexture(SpeedrunnerMod.SPEEDRUNNER_MOD_ICON);
            drawTexture(matrices, this.width / 2 - 4 - 119 - 2, this.height / 4 + 95 - 16 + 2, 0.0F, 0.0F, 18, 18, 18, 18);

            this.client.getTextureManager().bindTexture(new Identifier("dilloney:textures/discord.png"));
            drawTexture(matrices, this.width / 2 - 4 + 114 - 2, this.height / 4 + 48 - 16 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            this.client.getTextureManager().bindTexture(new Identifier("dilloney:textures/twitter.png"));
            drawTexture(matrices, this.width / 2 - 4 + 114 - 2, this.height / 4 + 72 - 16 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            this.client.getTextureManager().bindTexture(new Identifier("dilloney:textures/webpage.png"));
            drawTexture(matrices, this.width / 2 - 4 + 114 - 2, this.height / 4 + 96 - 16 + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }
    }
}