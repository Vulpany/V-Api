package com.pany.mods.vapi;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V_Api implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("V-API");
    @Override
    public void onInitialize() {
        LOGGER.info("Loaded!");
    }
}
