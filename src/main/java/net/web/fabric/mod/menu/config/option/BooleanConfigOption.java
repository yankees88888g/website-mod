package net.web.fabric.mod.menu.config.option;

import net.web.fabric.mod.menu.TranslationUtil;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class BooleanConfigOption{
	private final String key, translationKey;
	private final boolean defaultValue;
	private final Text enabledText;
	private final Text disabledText;

	public BooleanConfigOption(String key, boolean defaultValue, String enabledKey, String disabledKey) {
		ConfigOptionStorage.setBoolean(key, defaultValue);
		this.key = key;
		this.translationKey = TranslationUtil.translationKeyOf("option", key);
		this.defaultValue = defaultValue;
		this.enabledText = Text.translatable(translationKey + "." + enabledKey);
		this.disabledText = Text.translatable(translationKey + "." + disabledKey);
	}

	public BooleanConfigOption(String key, boolean defaultValue) {
		this(key, defaultValue, "true", "false");
	}

	public String getKey() {
		return key;
	}

	public boolean getValue() {
		return ConfigOptionStorage.getBoolean(key);
	}

	public void setValue(boolean value) {
		ConfigOptionStorage.setBoolean(key, value);
	}

	public void toggleValue() {
		ConfigOptionStorage.toggleBoolean(key);
	}

	public boolean getDefaultValue() {
		return defaultValue;
	}

	public Text getButtonText() {
		return ScreenTexts.composeGenericOptionText(Text.translatable(translationKey), getValue() ? enabledText : disabledText);
	}
}
