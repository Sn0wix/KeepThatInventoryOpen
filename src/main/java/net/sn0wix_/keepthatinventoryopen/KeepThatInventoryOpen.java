package net.sn0wix_.keepthatinventoryopen;

import net.fabricmc.api.ClientModInitializer;
import net.sn0wix_.keepthatinventoryopen.config.Config;
import net.sn0wix_.keepthatinventoryopen.config.ConfigFile;
import net.sn0wix_.keepthatinventoryopen.config.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeepThatInventoryOpen implements ClientModInitializer {
    public static final String MOD_ID = "keepthatinventoryopen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Config CONFIG = ConfigFile.initConfig();

    @Override
    public void onInitializeClient() {
        Settings.init();
        LoggerFactory.getLogger(MOD_ID).info(MOD_ID + " initialized!");
    }
}
