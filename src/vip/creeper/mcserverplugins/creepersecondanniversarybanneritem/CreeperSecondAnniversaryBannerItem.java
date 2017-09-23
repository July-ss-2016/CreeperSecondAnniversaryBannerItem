package vip.creeper.mcserverplugins.creepersecondanniversarybanneritem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.creepersecondanniversarybanneritem.listeners.PlayerListener;

/**
 * Created by July_ on 2017/9/23.
 */
public class CreeperSecondAnniversaryBannerItem extends JavaPlugin {
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        getLogger().info("插件初始化完毕.");
    }
}
