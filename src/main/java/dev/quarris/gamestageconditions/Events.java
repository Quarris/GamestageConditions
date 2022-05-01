package dev.quarris.gamestageconditions;

import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModRef.ID)
public class Events {

    @SubscribeEvent
    public static void on(GameStageEvent.Added event) {
        if (event.getPlayer() instanceof ServerPlayerEntity) {
            GamestageConditions.GAMESTAGE_ADDED_TRIGGER.trigger((ServerPlayerEntity) event.getPlayer(), event.getStageName());
        }
    }

    @SubscribeEvent
    public static void on(GameStageEvent.Removed event) {
        if (event.getPlayer() instanceof ServerPlayerEntity) {
            GamestageConditions.GAMESTAGE_REMOVED_TRIGGER.trigger((ServerPlayerEntity) event.getPlayer(), event.getStageName());
        }
    }
}
