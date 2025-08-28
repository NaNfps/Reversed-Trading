package me.nanfps.reversedtrading.util;

import com.google.common.collect.Lists;
import me.nanfps.reversedtrading.Config;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This file was created in 2025.08.28, 22:42
 *
 * @author NaN FPS
 */

public final class Reverser {
    private static final Set<String> SPECIAL_NAMES = Set.of("Dinnerbone", "Grumm");

    private Reverser() {
    }

    public static boolean shouldEnable(MerchantEntity abstractVillager) {
        return (Config.ENABLE_VILLAGERS.get() || !(abstractVillager instanceof VillagerEntity)) && (Config.ENABLE_WANDERING_TRADERS.get() || !(abstractVillager instanceof WanderingTraderEntity));
    }

    public static boolean isNameCorrect(String name) {
        return SPECIAL_NAMES.contains(name);
    }

    public static TradeOffer reverse(TradeOffer it) {
        return it.getSecondBuyItem().isEmpty() ? new TradeOffer(it.getSellItem(), it.getSecondBuyItem(), it.getOriginalFirstBuyItem(), it.getUses(), it.getMaxUses(), it.getMerchantExperience(), it.getPriceMultiplier(), it.getDemandBonus()) : it;
    }

    public static TradeOfferList reverse(TradeOfferList original, MerchantEntity abstractVillager) {
        return abstractVillager != null && shouldEnable(abstractVillager) && isNameCorrect(abstractVillager.getName().getString())
                       ? original.stream().map(Reverser::reverse)
                                 .collect(Collectors.toCollection(TradeOfferList::new))
                       : original;
    }
}
