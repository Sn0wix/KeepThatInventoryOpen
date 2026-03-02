package net.sn0wix_.keepthatinventoryopen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;Z)V", at = @At("HEAD"))
    private void injectDisconnect(Screen disconnectionScreen, boolean transferring, CallbackInfo ci){
        if (Settings.enabled.getValue() && Settings.onDisconnect.getValue() && MinecraftClient.getInstance().getNetworkHandler() != null) {
            MinecraftClient.getInstance().getNetworkHandler().sendPacket(new CloseHandledScreenC2SPacket(0));
            KeepThatInventoryOpen.LOGGER.info("Stimulated close inventory packet upon disconnecting");
        }
    }
}
