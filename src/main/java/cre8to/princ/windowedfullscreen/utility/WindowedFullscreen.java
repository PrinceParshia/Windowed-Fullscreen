package cre8to.princ.windowedfullscreen.utility;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class WindowedFullscreen {
    private static final long getWindowHandle = MinecraftClient.getInstance().getWindow().getHandle();
    private static boolean isWindowMaximized;
    public static boolean isEnabled;

    private static void toggle() {
        isWindowMaximized = GLFW.glfwGetWindowAttrib(getWindowHandle, GLFW.GLFW_MAXIMIZED) == GLFW.GLFW_TRUE;

        GLFW.glfwSetWindowAttrib(getWindowHandle, GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
        GLFW.glfwMaximizeWindow(getWindowHandle);

        isEnabled = true;
    }

    private static void restore() {
        GLFW.glfwSetWindowAttrib(getWindowHandle, GLFW.GLFW_DECORATED, GLFW.GLFW_TRUE);

        if (!isWindowMaximized) {
            GLFW.glfwRestoreWindow(getWindowHandle);
        }

        isEnabled = false;
    }

    public static void adjust() {
        if (GLFW.glfwGetWindowAttrib(getWindowHandle, GLFW.GLFW_DECORATED) == GLFW.GLFW_TRUE) {
            toggle();
        } else if (GLFW.glfwGetWindowAttrib(getWindowHandle, GLFW.GLFW_DECORATED) == GLFW.GLFW_FALSE) {
            restore();
        }
    }
}
