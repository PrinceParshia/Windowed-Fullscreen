package cre8to.princ.windowedfullscreen;

import cre8to.princ.windowedfullscreen.option.KeyBindings;
import net.fabricmc.api.ClientModInitializer;

public class WindowedFullscreen implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		KeyBindings.register();
	}
}
