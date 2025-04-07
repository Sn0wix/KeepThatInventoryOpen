package net.sn0wix_.keepthatinventoryopen.config;

import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;

public class Settings {
    public static SimpleOption<Boolean> enabled;
    public static SimpleOption<Boolean> onDisconnect;

    public static void init() {
        enabled = SimpleOption.ofBoolean("options." + KeepThatInventoryOpen.MOD_ID + ".enabled", SimpleOption.emptyTooltip(), (optionText, value) -> Text.of(value.toString()), true, aBoolean -> KeepThatInventoryOpen.CONFIG.enabled = aBoolean);
        enabled.setValue(KeepThatInventoryOpen.CONFIG.enabled);

        onDisconnect = SimpleOption.ofBoolean("options." + KeepThatInventoryOpen.MOD_ID + ".onDisconnect", SimpleOption.constantTooltip(Text.translatable("tooltip." + KeepThatInventoryOpen.MOD_ID + ".onDisconnect")), (optionText, value) -> Text.translatable("text." + KeepThatInventoryOpen.MOD_ID + ".onDisconnect." + value.toString()), true, aBoolean -> KeepThatInventoryOpen.CONFIG.onDisconnect = aBoolean);
        onDisconnect.setValue(KeepThatInventoryOpen.CONFIG.onDisconnect);
    }
}
