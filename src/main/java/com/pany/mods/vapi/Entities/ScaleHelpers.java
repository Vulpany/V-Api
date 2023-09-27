package com.pany.mods.vapi.Entities;

import net.minecraft.entity.player.PlayerEntity;

public class ScaleHelpers {
    private static double DefaultPlayerWidth = 0.6;
    private static double DefaultPlayerHeight = 1.8;

    public static double GetNumberAdjustedByPlayerSize(PlayerEntity player,double Number) {
        return ((player.getHeight()*player.getWidth()) / (DefaultPlayerHeight * DefaultPlayerWidth)) * Number;
    }

}
