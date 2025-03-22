package princ.windfullsc;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class WindowedFullscreen implements ClientModInitializer {
	private boolean wasWindowedFullscreenKeyPressed;

	private final KeyMapping windowedFullscreenKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
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
				WindowedFullscreenHandler.adjust();
			}
			wasWindowedFullscreenKeyPressed = isWindowedFullscreenKeyPressed;
		});
	}
}
