package org.aslstd.eh.core.config;

import org.aslstd.api.bukkit.yaml.EJConf;
import org.aslstd.eh.core.EH;

public class GeneralConfig extends EJConf {

	public GeneralConfig() {
		super(EH.inst().getDataFolder() + "/config.yml", EH.inst());
	}

	@Override
	public void loadConfig() {
	}

}
