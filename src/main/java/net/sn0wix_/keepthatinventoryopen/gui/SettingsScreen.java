package net.sn0wix_.keepthatinventoryopen.gui;

import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;
import net.sn0wix_.keepthatinventoryopen.config.ConfigFile;
import net.sn0wix_.keepthatinventoryopen.config.Settings;

public class SettingsScreen extends OptionsSubScreen {
    public SettingsScreen(Screen parent, Options gameOptions) {
        super(parent, gameOptions, Component.translatable("text." + KeepThatInventoryOpen.MOD_ID + ".settings"));
    }

    @Override
    public void addOptions() {
        this.list.addBig(Settings.enabled);
        this.list.addBig(Settings.onDisconnect);
        this.list.addBig(Settings.displayWarning);
    }

    @Override
    public void onClose() {
        ConfigFile.writeConfig(KeepThatInventoryOpen.CONFIG);
        super.onClose();
    }
}
