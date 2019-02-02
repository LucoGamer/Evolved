package com.fmbdev.Evolved;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Main instance;
	
	public void log(String string) {
		System.out.println(string);
	}
	public static Main getInstance() {
		return instance;
	}
	public void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
	}

	//onEnable
	@Override
	public void onEnable() {
		getLogger().info("has been enabled without errors.");
		instance = this;
		registerListeners();
	}

	//onDisable
	@Override
	public void onDisable() {
		getLogger().info("has been disabled without errors.");
		instance = null;
	}
}