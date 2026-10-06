package net.sn0wix_.keepthatinventoryopen.config;

import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;

public class Settings {
    public static OptionInstance<Boolean> enabled;
    public static OptionInstance<Boolean> onDisconnect;
    public static OptionInstance<Boolean> displayWarning;

    public static void init() {
        enabled = OptionInstance.createBoolean("options." + KeepThatInventoryOpen.MOD_ID + ".enabled", OptionInstance.noTooltip(), (optionText, value) -> Component.nullToEmpty(value.toString()), true, aBoolean -> KeepThatInventoryOpen.CONFIG.enabled = aBoolean);
        enabled.set(KeepThatInventoryOpen.CONFIG.enabled);

        onDisconnect = OptionInstance.createBoolean("options." + KeepThatInventoryOpen.MOD_ID + ".onDisconnect", OptionInstance.cachedConstantTooltip(Component.translatable("tooltip." + KeepThatInventoryOpen.MOD_ID + ".onDisconnect")), (optionText, value) -> Component.translatable("text." + KeepThatInventoryOpen.MOD_ID + ".onDisconnect." + value.toString()), true, aBoolean -> KeepThatInventoryOpen.CONFIG.onDisconnect = aBoolean);
        onDisconnect.set(KeepThatInventoryOpen.CONFIG.onDisconnect);

        displayWarning = OptionInstance.createBoolean("options." + KeepThatInventoryOpen.MOD_ID + ".displayWarning", OptionInstance.cachedConstantTooltip(Component.translatable("tooltip." + KeepThatInventoryOpen.MOD_ID + ".displayWarning")), (optionText, value) -> Component.translatable("text." + KeepThatInventoryOpen.MOD_ID + ".displayWarning." + value.toString()), true, aBoolean -> KeepThatInventoryOpen.CONFIG.onDisconnect = aBoolean);
        displayWarning.set(KeepThatInventoryOpen.CONFIG.displayWarning);
    }
}
