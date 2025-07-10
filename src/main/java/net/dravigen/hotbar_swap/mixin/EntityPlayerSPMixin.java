package net.dravigen.hotbar_swap.mixin;

import net.dravigen.hotbar_swap.HotbarSwapAddon;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin extends AbstractClientPlayer {

    @Shadow protected Minecraft mc;
    @Unique
    private static boolean keyPressed = false;

    public EntityPlayerSPMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Inject(method = "onLivingUpdate",at = @At("HEAD"))
    private void update(CallbackInfo ci){
        if (!Keyboard.isKeyDown(Keyboard.KEY_F3) && HotbarSwapAddon.swap_key.isPressed()) {
            if (!keyPressed) {
                int windowId = this.inventoryContainer.windowId;
                PlayerControllerMP controllerMP = this.mc.playerController;
                if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
                    for (int column = 0; column <= 8; column++) {
                        int hotbarSlot = 9 + column;
                        int row1Slot = 18 + column;
                        int row2Slot = 27 + column;
                        int row3Slot = 36 + column;
                        controllerMP.windowClick(windowId, row1Slot, 0, 0, this);
                        controllerMP.windowClick(windowId, row2Slot, 0, 0, this);
                        controllerMP.windowClick(windowId, row3Slot, 0, 0, this);
                        controllerMP.windowClick(windowId, hotbarSlot, 0, 0, this);
                        controllerMP.windowClick(windowId, row1Slot, 0, 0, this);
                    }
                } else {
                    int current = this.inventory.currentItem;
                    int hotbarSlot = 9 + current;
                    int row1Slot = 18 + current;
                    int row2Slot = 27 + current;
                    int row3Slot = 36 + current;
                    controllerMP.windowClick(windowId, row1Slot, 0, 0, this);
                    controllerMP.windowClick(windowId, row2Slot, 0, 0, this);
                    controllerMP.windowClick(windowId, row3Slot, 0, 0, this);
                    controllerMP.windowClick(windowId, hotbarSlot, 0, 0, this);
                    controllerMP.windowClick(windowId, row1Slot, 0, 0, this);
                }
                keyPressed = true;
            }
        } else keyPressed = false;

    }
}
