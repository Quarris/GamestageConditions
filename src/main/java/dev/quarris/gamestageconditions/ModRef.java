package dev.quarris.gamestageconditions;

import net.minecraft.resources.ResourceLocation;

public class ModRef {

    public static final String ID = "gamestageconditions";

    public static ResourceLocation res(String res) {
        return new ResourceLocation(ID, res);
    }
}
