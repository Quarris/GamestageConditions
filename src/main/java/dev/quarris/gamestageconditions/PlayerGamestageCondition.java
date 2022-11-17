package dev.quarris.gamestageconditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class PlayerGamestageCondition implements LootItemCondition {

    public static final LootItemConditionType GAMESTAGE_CONDITION_TYPE = new LootItemConditionType(new ConditionSerializer());

    private final String stage;

    public PlayerGamestageCondition(String stage) {
        this.stage = stage;
    }

    @Override
    public LootItemConditionType getType() {
        return GAMESTAGE_CONDITION_TYPE;
    }

    @Override
    public boolean test(LootContext ctx) {
        return this.validEntity(ctx, LootContextParams.KILLER_ENTITY) ||
               this.validEntity(ctx, LootContextParams.THIS_ENTITY);
    }

    private boolean validEntity(LootContext ctx, LootContextParam<Entity> param) {
        if (ctx.hasParam(param)) {
            Entity e = ctx.getParam(param);
            if (e instanceof Player) {
                return GameStageHelper.hasStage((Player) e, this.stage);
            }
        }

        return false;
    }

    public static PlayerGamestageCondition.Builder builder(String stage) {
        return new PlayerGamestageCondition.Builder(stage);
    }

    public static class Builder implements LootItemCondition.Builder {
        private final String stage;

        public Builder(String stage) {
            if (stage == null)
                throw new IllegalArgumentException("Gamestage must not be null");
            this.stage = stage;
        }

        @Override
        public LootItemCondition build() {
            return new PlayerGamestageCondition(this.stage);
        }
    }

    public static class ConditionSerializer implements Serializer<PlayerGamestageCondition> {
        @Override
        public void serialize(JsonObject object, PlayerGamestageCondition instance, JsonSerializationContext ctx) {
            object.addProperty("stage", instance.stage);
        }

        @Override
        public PlayerGamestageCondition deserialize(JsonObject object, JsonDeserializationContext ctx) {
            return new PlayerGamestageCondition(object.get("stage").getAsString());
        }
    }
}
