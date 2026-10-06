package net.sn0wix_.keepthatinventoryopen.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow
    @Final
    protected Minecraft minecraft;

    @Inject(method = "closeContainer", at = @At(value = "HEAD"), cancellable = true)
    public void injectClose(CallbackInfo ci) {
        //inventory has syncId of 0
        if (Settings.enabled.get() && minecraft.player != null && minecraft.player.containerMenu.containerId == 0) {
            ci.cancel();
        }
    }
}
