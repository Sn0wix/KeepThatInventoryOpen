package net.sn0wix_.keepthatinventoryopen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow
    @Final
    protected MinecraftClient client;

    @Inject(method = "closeHandledScreen", at = @At(value = "HEAD"), cancellable = true)
    public void injectClose(CallbackInfo ci) {
        //inventory has syncId of 0
        if (Settings.enabled.getValue() && client.player != null && client.player.currentScreenHandler.syncId == 0) {
            ci.cancel();
        }
    }
}
