package org.aslstd.eh.core.config;

import org.aslstd.api.bukkit.yaml.EJConf;
import org.aslstd.eh.core.EH;

public class GeneralConfig extends EJConf {

	public static boolean disableHealthRegain,lockHunger, instantEating;
	public static int lockHungerAmount, maxHunger;

	public GeneralConfig() {
		super(EH.inst().getDataFolder() + "/config.yml", EH.inst());
	}

	@Override
	public void loadConfig() {
		disableHealthRegain = getBoolean("general.disable-health-regain", false, true);
		instantEating = getBoolean("general.disable-eat-animation", false, true);
		lockHunger = getBoolean("general.lock-hunger-bar", false, true);
		lockHungerAmount = getInt("general.lock-hunger-amount", 20, true);

		maxHunger = getInt("hunger.max-value", 20, true);
	}

}
