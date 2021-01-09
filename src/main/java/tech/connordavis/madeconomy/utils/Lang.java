package tech.connordavis.madeconomy.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import tech.connordavis.madeconomy.MadEconomy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Lang {
    public static TranslationTextComponent translate(String key, Object... args) {
        return createTranslationTextComponent(key, args);
    }

    public static TranslationTextComponent createTranslationTextComponent(String key, Object... args) {
        return new TranslationTextComponent(MadEconomy.ID + "." + key, args);
    }

    public static void sendStatus(PlayerEntity player, String key, Object... args) {
        player.sendStatusMessage(createTranslationTextComponent(key, args), true);
    }

    public static List<ITextComponent> translatedOptions(String prefix, String... keys) {
        List<ITextComponent> result = new ArrayList<>(keys.length);
        for (String key : keys) {
            result.add(translate(prefix + "." + key));
        }
        return result;
    }

    public static String asId(String name) {
        return name.toLowerCase(Locale.ENGLISH);
    }
}
