package net.sn0wix_.keepthatinventoryopen.mixin;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {
    //this.exitButton = adder.add(ButtonWidget.builder(text, button -> { <--HERE
    @Inject(method = "method_19836", at = @At("HEAD"), cancellable = true)
    private void injectOnDisconnect(ButtonWidget button, CallbackInfo ci) {
        try {
            if (Settings.enabled.getValue()) {
                MinecraftClient client = MinecraftClient.getInstance();


                if (Settings.displayWarning.getValue() &&
                        !(client.player.playerScreenHandler.getCraftingInput().getHeldStacks().stream().allMatch(ItemStack::isEmpty) &&
                        client.player.playerScreenHandler.getCursorStack().isEmpty())) {

                    ConfirmScreen screen = getConfirmScreen(client);
                    client.setScreen(screen);
                    ci.cancel();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Unique
    private @NonNull ConfirmScreen getConfirmScreen(MinecraftClient client) {
        BooleanConsumer callback = b -> {
            if (b) {
                client.getAbuseReportContext().tryShowDraftScreen(client, ((GameMenuScreen) (Object) this), () -> client.disconnect(ClientWorld.QUITTING_MULTIPLAYER_TEXT), true);
            } else {
                client.setScreen(null);
            }
        };

        return new ConfirmScreen(callback,
                Text.translatable("screen.keepthatinventoryopen.disconnect.warning"),
                Text.translatable("text.keepthatinventoryopen.disconnect.warning"));
    }
}
