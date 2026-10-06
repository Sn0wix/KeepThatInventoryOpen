package net.sn0wix_.keepthatinventoryopen.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Inject(method = "disconnectFromWorld(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"))
    private void injectDisconnect(Component reasonText, CallbackInfo ci){
        if (Settings.enabled.get() && Settings.onDisconnect.get()) {
            Minecraft.getInstance().getConnection().send(new ServerboundContainerClosePacket(0));
            KeepThatInventoryOpen.LOGGER.info("Stimulated close inventory packet upon disconnecting");
        }
    }
}
