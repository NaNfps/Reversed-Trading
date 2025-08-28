package me.nanfps.reversedtrading.mixin;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.nanfps.reversedtrading.util.Reverser;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This file was created in 2025.08.28, 00:12
 * @author NaN FPS
 */

@Mixin(AbstractVillager.class)
public abstract class MixinAbstractVillager extends AgeableMob {
    protected MixinAbstractVillager(EntityType<? extends AgeableMob> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyReturnValue(method = "getOffers", at = @At("RETURN"))
    private MerchantOffers getOffers$ReversedTrading(MerchantOffers original) {
        return Reverser.reverse(original, (AbstractVillager)(Object)this);
    }

    @ModifyExpressionValue(method = "addAdditionalSaveData", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/AbstractVillager;getOffers()Lnet/minecraft/world/item/trading/MerchantOffers;"))
    private MerchantOffers addAdditionalSaveData$ReversedTrading(MerchantOffers original) {
        return Reverser.reverse(original, (AbstractVillager)(Object)this);
    }

    @Inject(method = "addOffersFromItemListings", at = @At("HEAD"), cancellable = true)
    private void addOffersFromItemListing$ReversedTrading(MerchantOffers givenMerchantOffers, VillagerTrades.ItemListing[] newTrades, int maxNumbers, CallbackInfo ci) {
        final var abstractVillager = (AbstractVillager)(Object)this;
        final var arraylist = Lists.newArrayList(newTrades);

        int i = 0;
        while (i < maxNumbers && !arraylist.isEmpty()) {
            final var merchantOffer = arraylist.remove(this.random.nextInt(arraylist.size())).getOffer(this, this.random);
            if(merchantOffer != null) {
                givenMerchantOffers.add(merchantOffer);
                abstractVillager.offers = givenMerchantOffers;
                i++;
            }
        }

        ci.cancel();
    }
}
