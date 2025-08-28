package me.nanfps.reversedtrading.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

/**
 * This file was created in 2025.08.27, 23:59
 *
 * @author NaN FPS
 */

@Mixin(Villager.class)
public abstract class MixinVillager extends AbstractVillager {
    public MixinVillager(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

//    @ModifyExpressionValue(method = "updateTrades", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/Villager;getOffers()Lnet/minecraft/world/item/trading/MerchantOffers;"))
//    private MerchantOffers updateTrades$ReversedTrading(MerchantOffers original) {
//        return Reverser.reverse(original, (AbstractVillager) (Object)this);
//    }
}
