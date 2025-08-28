package me.nanfps.reversedtrading.util;

import me.nanfps.reversedtrading.Config;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

/**
 * This file was created in 2025.08.27, 23:56
 *
 * @author NaN FPS
 */

public final class Reverser {
    public static boolean shouldEnable(AbstractVillager abstractVillager) {
        return (Config.ENABLE_VILLAGERS.get() || !(abstractVillager instanceof Villager)) && (Config.ENABLE_WANDERING_TRADERS.get() || !(abstractVillager instanceof WanderingTrader));
    }

    public static boolean isNameCorrect(String name) {
        return name.equals("Dinnerbone") || name.equals("Grumm");
    }

    public static MerchantOffer reverse(MerchantOffer it) {
        return it.costB.isEmpty() ? new MerchantOffer(new ItemCost(it.getResult().getItemHolder(), it.getResult().getCount(), DataComponentPredicate.allOf(it.getResult().getComponents())), it.costB, it.getCostA(), it.getUses(), it.getMaxUses(), it.getXp(), it.getPriceMultiplier(), it.getDemand()) : it;
    }

    public static MerchantOffers reverse(MerchantOffers original, AbstractVillager abstractVillager) {
        if(abstractVillager != null) {
            if(shouldEnable(abstractVillager) && isNameCorrect(abstractVillager.getName().getString())) {
                return new MerchantOffers(original.stream().map(Reverser::reverse).toList());
            }
        }

        return original;
    }
}
