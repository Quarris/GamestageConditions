package dev.quarris.gamestageconditions;

import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(ModRef.ID)
public class GamestageConditions {


    public GamestageConditions() {

    }

    @Mod.EventBusSubscriber(modid = ModRef.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventHandler {
        @SubscribeEvent
        public static void on(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            Registry.register(Registry.LOOT_CONDITION_TYPE, ModRef.res("stage"), PlayerGamestageCondition.GAMESTAGE_CONDITION_TYPE);

        }
    }
}
