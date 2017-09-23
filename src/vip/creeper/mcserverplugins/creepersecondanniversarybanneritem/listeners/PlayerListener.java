package vip.creeper.mcserverplugins.creepersecondanniversarybanneritem.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import vip.creeper.mcserverplugins.creepersecondanniversarybanneritem.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by July_ on 2017/9/23.
 */
public class PlayerListener implements Listener {
    private List<String> bannerHeldStates = new ArrayList<>();
    private List<Integer> entityIds = new ArrayList<>();

    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        ItemStack newItem = player.getInventory().getItem(event.getNewSlot());

        if (Util.isCreeperSecondAnninversaryBannerItem(newItem)) {
            bannerHeldStates.add(playerName);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
            Util.sendMsg(player, "&d你已获得 &e速度II &d加成!");
        } else if (bannerHeldStates.contains(playerName)) {
            bannerHeldStates.remove(playerName);
            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof  Player) {
            Player player = (Player) entity;
            Location loc = player.getLocation();

            if (Util.isCreeperSecondAnninversaryBannerItem(player.getItemInHand())) {
                loc.getWorld().playEffect(loc, Effect.WITCH_MAGIC, 100);

                Creeper creeper = (Creeper) loc.getWorld().spawnEntity(loc, EntityType.CREEPER);

                entityIds.add(creeper.getEntityId());
                creeper.setPowered(true);
                creeper.eject();
            }
        }
    }

    // 爆炸伤害增加, 阻止Creeper爆炸伤害除了player以外的生物
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (entityIds.contains(event.getDamager().getEntityId())) {

            if (event.getEntity() instanceof Animals) {
                event.setDamage(0D);
                event.setCancelled(true);
                return;
            }

            event.setDamage(13D);
        }
    }
}
