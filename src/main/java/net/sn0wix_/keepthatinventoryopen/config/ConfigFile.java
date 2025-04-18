package net.sn0wix_.keepthatinventoryopen.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sn0wix_.keepthatinventoryopen.KeepThatInventoryOpen;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigFile {
    private static final File CONFIG_FILE = new File("config" + File.separator + KeepThatInventoryOpen.MOD_ID + File.separator + "config.json");

    public static Config readConfig() {
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return new Gson().fromJson(reader, Config.class);
        } catch (IOException e) {
            KeepThatInventoryOpen.LOGGER.error("Can not read config file!");
            e.printStackTrace();
            return new Config();
        }
    }

    public static void writeConfig(Config config) {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(config, writer);
        } catch (IOException e) {
            KeepThatInventoryOpen.LOGGER.error("Can not write into config file!");
            e.printStackTrace();
        }
    }

    public static boolean checkConfig() {
        if (!CONFIG_FILE.exists()) {
            try {
                new File("config" + File.separator + KeepThatInventoryOpen.MOD_ID).mkdirs();
                if (CONFIG_FILE.createNewFile()) {
                    writeConfig(new Config());
                }

                return false;
            } catch (IOException ioException) {
                KeepThatInventoryOpen.LOGGER.error("Can not create new config file! Check for permissions.");
                ioException.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static Config initConfig() {
        if (ConfigFile.checkConfig()) {
            return ConfigFile.readConfig();
        } else {
            return new Config();
        }
    }
}
