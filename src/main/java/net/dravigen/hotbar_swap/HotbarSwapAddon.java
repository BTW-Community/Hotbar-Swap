package net.dravigen.hotbar_swap;

import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.StatCollector;
import org.lwjgl.input.Keyboard;

public class HotbarSwapAddon extends BTWAddon {
    private static HotbarSwapAddon instance;
    public static KeyBinding swap_key;

    public HotbarSwapAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        initKeybind();
    }

    public void initKeybind(){
        swap_key = new KeyBinding(StatCollector.translateToLocal("Hotbar Swap"), Keyboard.KEY_H);
    }

}