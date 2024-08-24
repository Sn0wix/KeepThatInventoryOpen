package net.sn0wix_.keepthatinvopen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Shadow
    @Final
    protected MinecraftClient client;

    @Inject(method = "closeHandledScreen", at = @At("HEAD"), cancellable = true)
    public void injectClose(CallbackInfo ci) {
        //inventory has syncId of 0
        if (client.player != null && client.player.currentScreenHandler.syncId == 0) {
            ci.cancel();
        }
    }
}
