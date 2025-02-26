package cre8to.princ.windowedfullscreen;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class WindowedFullscreen implements ClientModInitializer {
	public static final String MOD_ID = "windowed-fullscreen";
	private static final MinecraftClient client = MinecraftClient.getInstance();
	private KeyBinding windowedFullscreenKey;
	private boolean wasPressed = false;

    @Override
	public void onInitializeClient() {
        windowedFullscreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.windowed.fullscreen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F12,
                "key.categories.misc"
        ));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			boolean isWindowedFullscreenPressed = InputUtil.isKeyPressed(client.getWindow().getHandle() ,InputUtil.fromTranslationKey(windowedFullscreenKey.getBoundKeyTranslationKey()).getCode());
			if (isWindowedFullscreenPressed && !wasPressed) {
				toggleWindowedFullscreen();
			}
			wasPressed = isWindowedFullscreenPressed;
		});
	}

	public static void toggleWindowedFullscreen() {
		if (GLFW.glfwGetWindowAttrib(client.getWindow().getHandle(), GLFW.GLFW_DECORATED) == GLFW.GLFW_TRUE) {
			GLFW.glfwSetWindowAttrib(client.getWindow().getHandle(), GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
			GLFW.glfwMaximizeWindow(client.getWindow().getHandle());
		} else if (GLFW.glfwGetWindowAttrib(client.getWindow().getHandle(), GLFW.GLFW_DECORATED) == GLFW.GLFW_FALSE) {
			GLFW.glfwSetWindowAttrib(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_DECORATED, GLFW.GLFW_TRUE);
			GLFW.glfwRestoreWindow(client.getWindow().getHandle());
		}
	}
}