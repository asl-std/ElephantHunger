package org.aslstd.eh.core;

import org.aslstd.api.bukkit.command.BasicCommand;
import org.aslstd.api.bukkit.command.BasicCommandHandler;
import org.aslstd.api.bukkit.entity.util.EntityUtil;
import org.aslstd.api.bukkit.yaml.player.PlayerDatabase;
import org.aslstd.api.ejcore.plugin.EJPlugin;
import org.aslstd.core.Core;
import org.aslstd.core.service.ListenerRegistrator;
import org.aslstd.eh.core.config.GeneralConfig;
import org.aslstd.eh.core.config.LangConfig;
import org.aslstd.eh.core.listener.PlayerListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.OfflinePlayer;

import lombok.Getter;

public class EH extends EJPlugin {

	private static EH instance;
	public static EH inst() { return instance; }
	private BasicCommandHandler handler;

	@Getter private static GeneralConfig cfg;
	@Getter private static LangConfig lang;
	@Getter private static PlayerDatabase db;

	@Override
	public void init() {
		instance = this;
		resourceId = 35169;
		new Metrics(this, 2139);
		cfg = new GeneralConfig();
		lang = new LangConfig();

		registerCommands();
		db = Core.getPlayerDatabase();

		ListenerRegistrator.add(new PlayerListener(), this);

	}

	@Override
	public void reloadPlugin() {
		cfg.reload();

		ListenerRegistrator.add(new PlayerListener(), this);
	}

	private void registerCommands() {
		handler = new BasicCommandHandler(this, "eh");

		handler.registerDefaultHelp()
		.registerDefaultReload()
		.registerCommand(new BasicCommand(handler, "lock", (s,args) -> {
			if (args.length < 0)
				return LangConfig.PLAYER_NOT_FOUND;

			final OfflinePlayer p = EntityUtil.getPlayer(args[0]);
			if (p == null)
				return LangConfig.PLAYER_NOT_FOUND;

			db.getPlayerFile(p).set("hunger-lock", !db.getPlayerFile(p).getBoolean("hunger-lock", false, false));

			return LangConfig.SUCCESFULL_HUNGER_LOCK;
		}))
		.registerHandler();
	}


}
