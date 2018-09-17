package me.dodocarlos.vip.utils;

import java.util.HashMap;

import org.bukkit.permissions.PermissionAttachment;

import me.dodocarlos.vip.Main;

public class Vars {
	
	public static HashMap<String, PermissionAttachment> playerAttachments = new HashMap<>();
	
	public static Main main;
	
	public static String tag;
	public static String infoColor = "§f";
	public static String defaultColor = "§a";
	public static String server;
	public static String db;
	public static String user;
	public static String pass;
	
	public static void setup(){
		infoColor = Methods.toColoredString(main.getConfig().getString("InfoColor"));
		defaultColor = Methods.toColoredString(main.getConfig().getString("DefaultColor"));
		tag = Methods.toColoredString(main.getConfig().getString("Tag"));
		
		server = main.getConfig().getString("MySQL.host");
		db = main.getConfig().getString("MySQL.db");
		user = main.getConfig().getString("MySQL.user");
		pass = main.getConfig().getString("MySQL.pass");
	}
	
}
