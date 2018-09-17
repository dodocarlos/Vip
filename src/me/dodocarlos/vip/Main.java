package me.dodocarlos.vip;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.dodocarlos.vip.listeners.Join;
import me.dodocarlos.vip.utils.DB;
import me.dodocarlos.vip.utils.Vars;

public class Main extends JavaPlugin{
	
	public static DB db;
	
	@Override
	public void onLoad() {
		Vars.main = this;
		Vars.setup();
		config();
		db = new DB();
		Bukkit.getConsoleSender().sendMessage("Conectado ao banco de dados!");
	}
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new Join(), this);
	}
	
	@Override
	public void onDisable() {

	}
	
	public void config(){
		File configf = new File(getDataFolder(), "config.yml");
		if(!configf.exists()){
			saveDefaultConfig();		
		}	
	}
	
}
