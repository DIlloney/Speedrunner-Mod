package net.dillon.speedrunnermod.option;

import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

public enum ItemMessages implements TranslatableOption {
    CHAT(0, "speedrunnermod.options.item_messages.chat"),
    ACTIONBAR(1, "speedrunnermod.options.item_messages.actionbar");

    private static final ItemMessages[] VALUES = Arrays.stream(ItemMessages.values()).sorted(Comparator.comparingInt(ItemMessages::getId)).toArray(ItemMessages[]::new);
    private final int id;
    private final String translateKey;

    ItemMessages(int id, String translationKey) {
        this.id = id;
        this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
    }

    /**
     * Returns the {@code id value} of the {@code Item Messages} option.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the {@code translation key} of the {@code Item Messages} option.
     */
    @Override
    public String getTranslationKey() {
        return this.translateKey;
    }

    /**
     * Not sure what this does to be honest, but it's used in {@link ModListOptions}.
     */
    public static ItemMessages byId(int id) {
        return VALUES[MathHelper.floorMod(id, VALUES.length)];
    }

    /**
     * Returns true if the {@code Item Messages} option is safe to run.
     */
    public boolean isSafe() {
        return options().client.itemMessages.equals(ACTIONBAR) ||
                options().client.itemMessages.equals(CHAT);
    }

    /**
     * Returns true if the {@code Item Messages} option is set to actionbar.
     */
    public boolean isActionbar() {
        return options().client.itemMessages.equals(ACTIONBAR);
    }
}