package dev.quarris.gamestageconditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;

public class PlayerGamestageCondition implements ILootCondition {

    public static final LootConditionType GAMESTAGE_CONDITION_TYPE = new LootConditionType(new Serializer());

    private final String stage;

    public PlayerGamestageCondition(String stage) {
        this.stage = stage;
    }

    @Override
    public LootConditionType getConditionType() {
        return GAMESTAGE_CONDITION_TYPE;
    }

    @Override
    public boolean test(LootContext ctx) {
        return this.validEntity(ctx, LootParameters.KILLER_ENTITY) ||
            this.validEntity(ctx, LootParameters.THIS_ENTITY);
    }

    private boolean validEntity(LootContext ctx, LootParameter<Entity> param) {
        if (ctx.has(param)) {
            Entity e = ctx.get(param);
            if (e instanceof PlayerEntity) {
                return GameStageHelper.hasStage((PlayerEntity) e, this.stage);
            }
        }

        return false;
    }

    public static PlayerGamestageCondition.Builder builder(String stage) {
        return new PlayerGamestageCondition.Builder(stage);
    }

    public static class Builder implements ILootCondition.IBuilder {
        private final String stage;

        public Builder(String stage) {
            if (stage == null)
                throw new IllegalArgumentException("Gamestage must not be null");
            this.stage = stage;
        }

        @Override
        public ILootCondition build() {
            return new PlayerGamestageCondition(this.stage);
        }
    }

    public static class Serializer implements ILootSerializer<PlayerGamestageCondition> {
        @Override
        public void serialize(JsonObject object, PlayerGamestageCondition instance, JsonSerializationContext ctx) {
            object.addProperty("stage", instance.stage);
        }

        @Override
        public PlayerGamestageCondition deserialize(JsonObject object, JsonDeserializationContext ctx) {
            return new PlayerGamestageCondition(JSONUtils.getString(object, "stage"));
        }
    }
}
