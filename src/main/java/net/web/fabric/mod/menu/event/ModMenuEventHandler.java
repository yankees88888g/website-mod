package net.web.fabric.mod.menu.event;

import net.web.fabric.mod.menu.ModMenu;
import net.web.fabric.mod.menu.api.ModMenuApi;
import net.web.fabric.mod.menu.config.ModMenuConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModMenuEventHandler {
	private static final Identifier FABRIC_ICON_BUTTON_LOCATION = new Identifier(ModMenu.MOD_ID, "textures/gui/mods_button.png");
	private static KeyBinding MENU_KEY_BIND;
	public static void afterScreenInit(MinecraftClient client, Screen screen, int scaledWidth, int scaledHeight) {
		if (screen instanceof TitleScreen) {
			afterTitleScreenInit(screen);
		} else if (screen instanceof GameMenuScreen) {
			afterGameMenuScreenInit(screen);
		}
	}

	private static void afterTitleScreenInit(Screen screen) {
		final List<ClickableWidget> buttons = Screens.getButtons(screen);
		if (ModMenuConfig.MODIFY_TITLE_SCREEN.getValue()) {
			int modsButtonIndex = -1;
			final int spacing = 24;
			int buttonsY = screen.height / 4 + 48;
			for (int i = 0; i < buttons.size(); i++) {
				ClickableWidget button = buttons.get(i);
				if (ModMenuConfig.MODS_BUTTON_STYLE.getValue() == ModMenuConfig.ModsButtonStyle.CLASSIC) {
					if (button.visible) {
						shiftButtons(button, modsButtonIndex == -1, spacing);
						if (modsButtonIndex == -1) {
							buttonsY = button.y;
						}
					}
				}
				if (buttonHasText(button, "menu.online")) {
				}
			}
		}
	}

	private static void afterGameMenuScreenInit(Screen screen) {
		final List<ClickableWidget> buttons = Screens.getButtons(screen);
		if (ModMenuConfig.MODIFY_GAME_MENU.getValue()) {
			int modsButtonIndex = -1;
			final int spacing = 24;
			int buttonsY = screen.height / 4 + 8;
			ModMenuConfig.ModsButtonStyle style = ModMenuConfig.MODS_BUTTON_STYLE.getValue().forGameMenu();
			for (int i = 0; i < buttons.size(); i++) {
				ClickableWidget button = buttons.get(i);
				if (style == ModMenuConfig.ModsButtonStyle.CLASSIC) {
					if (button.visible) {
						shiftButtons(button, modsButtonIndex == -1, spacing);
						if (modsButtonIndex == -1) {
							buttonsY = button.y;
						}
					}
				}
			}
		}
	}

	private static boolean buttonHasText(ClickableWidget button, String translationKey) {
		Text text = button.getMessage();
		TextContent textContent = text.getContent();
		return textContent instanceof TranslatableTextContent && ((TranslatableTextContent) textContent).getKey().equals(translationKey);
	}

	private static void shiftButtons(ClickableWidget button, boolean shiftUp, int spacing) {
		if (shiftUp) {
			button.y -= spacing / 2;
		} else if (!button.getMessage().equals(TitleScreen.COPYRIGHT)) {
			button.y += spacing - (spacing / 2);
		}
	}
}
