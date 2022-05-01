package dev.quarris.gamestageconditions;

import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.ResourceLocation;

public class GamestageTrigger extends AbstractCriterionTrigger<GamestageTrigger.Instance> {
    private final ResourceLocation id;
    public GamestageTrigger(ResourceLocation id) {
        this.id = id;
    }

    public void trigger(ServerPlayerEntity player, String stage) {
        this.triggerListeners(player, (instance) -> instance.test(stage));
    }

    @Override
    protected Instance deserializeTrigger(JsonObject json, EntityPredicate.AndPredicate playerPredicate, ConditionArrayParser conditionsParser) {
        return new Instance(this.getId(), playerPredicate, json.get("stage").getAsString());
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    public static class Instance extends CriterionInstance {
        private final String stage;

        public Instance(ResourceLocation id, EntityPredicate.AndPredicate player, String stage) {
            super(id, player);
            this.stage = stage;
        }

        public boolean test(String stage) {
            return this.stage.equals(stage);
        }

        public JsonObject serialize(ConditionArraySerializer conditions) {
            JsonObject jsonobject = super.serialize(conditions);
            jsonobject.addProperty("stage", this.stage);
            return jsonobject;
        }
    }
}
