package dev.quarris.gamestageconditions;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class GamestageTrigger extends SimpleCriterionTrigger<GamestageTrigger.TriggerInstance> {
    private final ResourceLocation id;
    public GamestageTrigger(ResourceLocation id) {
        this.id = id;
    }

    public void trigger(ServerPlayer player, String stage) {
        this.trigger(player, (instance) -> instance.test(stage));
    }

    @Override
    protected TriggerInstance createInstance(JsonObject json, EntityPredicate.Composite playerPredicate, DeserializationContext conditionsParser) {
        return new TriggerInstance(this.getId(), playerPredicate, json.get("stage").getAsString());
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final String stage;

        public TriggerInstance(ResourceLocation id, EntityPredicate.Composite player, String stage) {
            super(id, player);
            this.stage = stage;
        }

        public boolean test(String stage) {
            return this.stage.equals(stage);
        }

        public JsonObject serialize(SerializationContext conditions) {
            JsonObject jsonobject = super.serializeToJson(conditions);
            jsonobject.addProperty("stage", this.stage);
            return jsonobject;
        }
    }
}
