package dev.quarris.gamestageconditions;

import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModRef.ID)
public class Events {

    @SubscribeEvent
    public static void on(GameStageEvent.Added event) {
        if (event.getEntity() instanceof ServerPlayer) {
            GamestageConditions.GAMESTAGE_ADDED_TRIGGER.trigger((ServerPlayer) event.getEntity(), event.getStageName());
        }
    }

    @SubscribeEvent
    public static void on(GameStageEvent.Removed event) {
        if (event.getEntity() instanceof ServerPlayer) {
            GamestageConditions.GAMESTAGE_REMOVED_TRIGGER.trigger((ServerPlayer) event.getEntity(), event.getStageName());
        }
    }
}
