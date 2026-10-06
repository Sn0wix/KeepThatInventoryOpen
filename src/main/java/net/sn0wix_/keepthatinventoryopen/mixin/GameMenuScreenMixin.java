package net.sn0wix_.keepthatinventoryopen.mixin;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public class GameMenuScreenMixin {
    //this.exitButton = adder.add(ButtonWidget.builder(text, button -> { <--HERE
    @Inject(method = "lambda$createPauseMenu$10", at = @At("HEAD"), cancellable = true)
    private void injectOnDisconnect(Button button, CallbackInfo ci) {
        try {
            if (Settings.enabled.get()) {
                Minecraft client = Minecraft.getInstance();


                if (Settings.displayWarning.get() &&
                        !(client.player.inventoryMenu.getCraftSlots().getItems().stream().allMatch(ItemStack::isEmpty) &&
                        client.player.inventoryMenu.getCarried().isEmpty())) {

                    ConfirmScreen screen = getConfirmScreen(client);
                    client.setScreenAndShow(screen);
                    ci.cancel();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Unique
    private @NonNull ConfirmScreen getConfirmScreen(Minecraft client) {
        BooleanConsumer callback = b -> {
            if (b) {
                client.getReportingContext().draftReportHandled(client, ((PauseScreen) (Object) this), () -> client.disconnectFromWorld(ClientLevel.DEFAULT_QUIT_MESSAGE), true);
            } else {
                client.setScreenAndShow(null);
            }
        };

        return new ConfirmScreen(callback,
                Component.translatable("screen.keepthatinventoryopen.disconnect.warning"),
                Component.translatable("text.keepthatinventoryopen.disconnect.warning"));
    }
}
