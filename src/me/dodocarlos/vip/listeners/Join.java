package me.dodocarlos.vip.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.dodocarlos.vip.utils.Methods;

public class Join implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		Methods.checkActivation(p);
		Methods.checkExpiration(p);
	}
	
}
