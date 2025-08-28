package me.nanfps.reversedtrading.mixin;

import com.google.common.collect.Sets;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.nanfps.reversedtrading.util.Reverser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

/**
 * This file was created in 2025.08.28, 22:56
 *
 * @author NaN FPS
 */

@Mixin(MerchantEntity.class)
public abstract class MixinMerchantEntity extends PassiveEntity {
    protected MixinMerchantEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getOffers", at = @At("RETURN"))
    private TradeOfferList getOffers$ReversedTrading(TradeOfferList original) {
        return Reverser.reverse(original, (MerchantEntity)(Object)this);
    }

    @ModifyExpressionValue(method = "writeCustomDataToNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/MerchantEntity;getOffers()Lnet/minecraft/village/TradeOfferList;"))
    private TradeOfferList writeCustomDataToNbt$ReversedTrading(TradeOfferList original) {
        return Reverser.reverse(original, (MerchantEntity)(Object)this);
    }

    @Inject(method = "fillRecipesFromPool", at = @At("HEAD"), cancellable = true)
    private void fillRecipesFromPool(TradeOfferList recipeList, TradeOffers.Factory[] pool, int count, CallbackInfo ci) {
        Set<Integer> set = Sets.newHashSet();
        if (pool.length > count) {
            while(set.size() < count) {
                set.add(this.random.nextInt(pool.length));
            }
        } else {
            for(int i = 0; i < pool.length; ++i) {
                set.add(i);
            }
        }

        for(Integer integer : set) {
            TradeOffer tradeOffer = (pool[integer]).create(this, this.random);
            if (tradeOffer != null) {
                recipeList.add(tradeOffer);
                ((MerchantEntity)(Object)this).offers = recipeList;
            }
        }

        ci.cancel();
    }
}