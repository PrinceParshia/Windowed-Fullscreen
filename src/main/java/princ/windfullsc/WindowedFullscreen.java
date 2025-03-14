package princ.windfullsc;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class WindowedFullscreen implements ClientModInitializer {
	private static final long getWindow = Minecraft.getInstance().getWindow().getWindow();
	private static boolean wasWindowedFullscreenKeyPressed;
	private static boolean isWindowMaximized;
	public static boolean isEnabled;

	private static final KeyMapping windowedFullscreenKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
			"key.windowed.fullscreen",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_F12,
			KeyMapping.CATEGORY_MISC
	));

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			boolean isWindowedFullscreenKeyPressed = InputConstants.isKeyDown(client.getWindow().getWindow(), InputConstants.getKey(windowedFullscreenKey.saveString()).getValue());
			if (isWindowedFullscreenKeyPressed && !wasWindowedFullscreenKeyPressed) {
				adjust();
			}
			wasWindowedFullscreenKeyPressed = isWindowedFullscreenKeyPressed;
		});
	}

	private static void toggle() {
		isWindowMaximized = GLFW.glfwGetWindowAttrib(getWindow, GLFW.GLFW_MAXIMIZED) == GLFW.GLFW_TRUE;

		if (isWindowMaximized) {
			GLFW.glfwSetWindowAttrib(getWindow, GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
		} else {
			GLFW.glfwSetWindowAttrib(getWindow, GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
			GLFW.glfwMaximizeWindow(getWindow);
		}

		isEnabled = true;
	}

	private static void restore() {
		GLFW.glfwSetWindowAttrib(getWindow, GLFW.GLFW_DECORATED, GLFW.GLFW_TRUE);

		if (!isWindowMaximized) {
			GLFW.glfwRestoreWindow(getWindow);
		}

		isEnabled = false;
	}

	public static void adjust() {
		if (GLFW.glfwGetWindowAttrib(getWindow, GLFW.GLFW_DECORATED) == GLFW.GLFW_TRUE) {
			toggle();
		} else if (GLFW.glfwGetWindowAttrib(getWindow, GLFW.GLFW_DECORATED) == GLFW.GLFW_FALSE) {
			restore();
		}
	}
}
