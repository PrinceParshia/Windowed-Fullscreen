package cre8to.princ.windowedfullscreen.option;

import cre8to.princ.windowedfullscreen.utility.WindowedFullscreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    private static KeyBinding windowedFullscreenKey;
    private static boolean wasWindowedFullscreenKeyPressed = false;

    public static void register() {
        windowedFullscreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.windowed.fullscreen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F12,
                "key.categories.misc"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            boolean isWindowedFullscreenKeyPressed = InputUtil.isKeyPressed(client.getWindow().getHandle(), InputUtil.fromTranslationKey(windowedFullscreenKey.getBoundKeyTranslationKey()).getCode());
            if (isWindowedFullscreenKeyPressed && !wasWindowedFullscreenKeyPressed) {
                WindowedFullscreen.adjust();
            }
            wasWindowedFullscreenKeyPressed = isWindowedFullscreenKeyPressed;
        });
    }
}
