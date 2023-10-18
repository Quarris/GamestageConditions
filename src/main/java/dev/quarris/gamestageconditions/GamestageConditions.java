package dev.quarris.gamestageconditions;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod(ModRef.ID)
public class GamestageConditions {

    public static final GamestageTrigger GAMESTAGE_ADDED_TRIGGER = CriteriaTriggers.register(new GamestageTrigger(ModRef.res("gamestage_added")));
    public static final GamestageTrigger GAMESTAGE_REMOVED_TRIGGER = CriteriaTriggers.register(new GamestageTrigger(ModRef.res("gamestage_removed")));

    public GamestageConditions() {

    }

    @Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventHandler {
        @SubscribeEvent
        public static void on(RegisterEvent event) {
            event.register(Registry.LOOT_CONDITION_TYPE.key(),
                    helper -> helper.register(ModRef.res("stage"), PlayerGamestageCondition.GAMESTAGE_CONDITION_TYPE));
        }
    }
}
