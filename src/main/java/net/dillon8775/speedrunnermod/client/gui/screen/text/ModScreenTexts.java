package net.dillon8775.speedrunnermod.client.gui.screen.text;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModScreenTexts {
    public static final Text OK = new TranslatableText("speedrunnermod.ok");
    public static final Text NEXT = new TranslatableText("speedrunnermod.next");
    public static final Text PREVIOUS = new TranslatableText("speedrunnermod.previous");
}