package net.sn0wix_.keepthatinventoryopen;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.LoggerFactory;

public class KeepThatInventoryOpen implements ClientModInitializer {
    public static final String MOD_ID = "keepthatinventoryopen";

    @Override
    public void onInitializeClient() {
        LoggerFactory.getLogger(MOD_ID).info(MOD_ID + " initialized!");
    }
}
