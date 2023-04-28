package org.aslstd.eh.core;

import org.aslstd.api.bukkit.command.BasicCommandHandler;
import org.aslstd.api.ejcore.plugin.EJPlugin;
import org.aslstd.eh.core.config.GeneralConfig;

import lombok.Getter;

public class EH extends EJPlugin {

	private static EH instance;
	public static EH inst() { return instance; }
	private BasicCommandHandler handler;

	@Getter private static GeneralConfig cfg;

	@Override
	public void init() {
		instance = this;
		handler = new BasicCommandHandler(this, "eh").registerDefaultHelp().registerDefaultReload().registerHandler();

		cfg = new GeneralConfig();
	}

	@Override
	public void reloadPlugin() {

	}


}
