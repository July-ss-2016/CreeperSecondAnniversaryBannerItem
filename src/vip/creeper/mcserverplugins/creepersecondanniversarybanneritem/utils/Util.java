package vip.creeper.mcserverplugins.creepersecondanniversarybanneritem.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by July_ on 2017/9/23.
 */
public class Util {
    public static final String HEAD_MSG = "§a[CreeperBanner] §b";

    public static boolean isCreeperSecondAnninversaryBannerItem(final ItemStack item) {
        if (item == null) {
            return false;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) {
            return false;
        }

        List<String> lores = itemMeta.getLore();

        if (lores == null || lores.size() == 0) {
            return false;
        }

        return lores.get(0).equalsIgnoreCase("§7- §f代码 §b> §fS_A0");
    }

    public static void sendMsg(final Player player, final String msg) {
        player.sendMessage(HEAD_MSG + ChatColor.translateAlternateColorCodes('&', msg));
    }
}

