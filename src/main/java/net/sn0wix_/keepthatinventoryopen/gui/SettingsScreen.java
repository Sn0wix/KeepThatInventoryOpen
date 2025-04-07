package net.sn0wix_.keepthatinventoryopen.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;
import net.sn0wix_.keepthatinventoryopen.config.ConfigFile;
import net.sn0wix_.keepthatinventoryopen.config.Settings;

public class SettingsScreen extends GameOptionsScreen {
    public SettingsScreen(Screen parent, GameOptions gameOptions) {
        super(parent, gameOptions, Text.translatable("text." + KeepThatInventoryOpen.MOD_ID + ".settings"));
    }

    @Override
    public void addOptions() {
        this.body.addSingleOptionEntry(Settings.enabled);
        this.body.addSingleOptionEntry(Settings.onDisconnect);
    }

    @Override
    public void close() {
        ConfigFile.writeConfig(KeepThatInventoryOpen.CONFIG);
        super.close();
    }
}
