package princ.windfullsc;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class WindowedFullscreenHandler {
    private static final long getWindow = Minecraft.getInstance().getWindow().getWindow();
    private static boolean isWindowMaximized;
    public static boolean isEnabled;

    public static void toggle() {
        isWindowMaximized = GLFW.glfwGetWindowAttrib(getWindow, GLFW.GLFW_MAXIMIZED) == GLFW.GLFW_TRUE;

        if (isWindowMaximized) {
            GLFW.glfwSetWindowAttrib(getWindow, GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
        } else {
            GLFW.glfwSetWindowAttrib(getWindow, GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
            GLFW.glfwMaximizeWindow(getWindow);
        }

        isEnabled = true;
    }

    public static void restore() {
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