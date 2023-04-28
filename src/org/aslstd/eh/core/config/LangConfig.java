package org.aslstd.eh.core.config;

import org.aslstd.api.bukkit.yaml.EJConf;
import org.aslstd.eh.core.EH;

public class LangConfig extends EJConf {

	public static String
	PLAYER_NOT_FOUND,
	SUCCESFULL_HUNGER_LOCK;

	public LangConfig(){
		super(EH.inst().getDataFolder() + "/lang.yml", EH.inst());
	}

	@Override
	public void loadConfig() {
		PLAYER_NOT_FOUND = getString("error.player-not-found", "&4Incorrect player name", true);
		SUCCESFULL_HUNGER_LOCK = getString("message.success-hunger-lock", "&2Hunger was successfully locked for %player%", true);
	}

}
