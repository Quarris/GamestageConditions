package dev.quarris.gamestageconditions;

import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModRef.ID)
public class Events {

    @SubscribeEvent
    public static void on(GameStageEvent.Added event) {
        if (event.getPlayer() instanceof ServerPlayer) {
            GamestageConditions.GAMESTAGE_ADDED_TRIGGER.trigger((ServerPlayer) event.getPlayer(), event.getStageName());
        }
    }

    @SubscribeEvent
    public static void on(GameStageEvent.Removed event) {
        if (event.getPlayer() instanceof ServerPlayer) {
            GamestageConditions.GAMESTAGE_REMOVED_TRIGGER.trigger((ServerPlayer) event.getPlayer(), event.getStageName());
        }
    }
}
