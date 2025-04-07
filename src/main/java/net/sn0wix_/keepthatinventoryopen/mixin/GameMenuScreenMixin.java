package net.sn0wix_.keepthatinventoryopen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {
    //this.exitButton = adder.add(ButtonWidget.builder(text, button -> { <--HERE
    @Inject(method = "method_19836", at = @At("HEAD"))
    private void injectOnDisconnect(ButtonWidget button, CallbackInfo ci) {
        try {
            if (Settings.onDisconnect.getValue() && Settings.enabled.getValue()) {
                MinecraftClient.getInstance().getNetworkHandler().sendPacket(new CloseHandledScreenC2SPacket(0));
                KeepThatInventoryOpen.LOGGER.info("Stimulated close inventory packet upon disconnecting");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
