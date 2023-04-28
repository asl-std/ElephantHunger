package org.aslstd.eh.core.listener;

import org.aslstd.api.bukkit.yaml.YAML;
import org.aslstd.api.ejcore.plugin.BukkitListener;
import org.aslstd.api.ejcore.plugin.Named;
import org.aslstd.eh.core.EH;
import org.aslstd.eh.core.config.GeneralConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

@Named(key = "eh-eat")
public class PlayerListener implements BukkitListener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		final YAML pf = EH.getDb().getPlayerFile(e.getPlayer());

		if (pf.getBoolean("hunger-lock", GeneralConfig.lockHunger, false)) {
			e.getPlayer().setFoodLevel(pf.getInt("hunger-lock-amount", GeneralConfig.lockHungerAmount, false));
			e.getPlayer().setSaturation(0);
		}

	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onHungerChange(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof final Player p) {
			final YAML pf = EH.getDb().getPlayerFile(p);

			if (pf.getBoolean("hunger-lock", GeneralConfig.lockHunger, false)) {
				p.setFoodLevel(pf.getInt("hunger-lock-amount", GeneralConfig.lockHungerAmount, false));
				p.setSaturation(0);
				if (p.getPotionEffect(PotionEffectType.HUNGER) != null)
					p.removePotionEffect(PotionEffectType.HUNGER);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onHealthRegain(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof final Player p)
			e.setCancelled(GeneralConfig.disableHealthRegain);
	}

}
