package dev.quarris.gamestageconditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
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
        Entity testEntity;
        if (ctx.has(LootParameters.KILLER_ENTITY)) {
            testEntity = ctx.get(LootParameters.KILLER_ENTITY);
        } else {
            testEntity = ctx.get(LootParameters.THIS_ENTITY);
        }

        if (!(testEntity instanceof PlayerEntity))
            return false;

        return GameStageHelper.hasStage((PlayerEntity) testEntity, this.stage);
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
