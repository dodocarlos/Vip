package me.dodocarlos.vip.utils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.dodocarlos.vip.Main;

public class Methods {
	
	public static String toColoredString(String s){
		return s.replaceAll("&", "§");
	}
	
	public static void checkActivation(Player p){
		if(Main.db.hasVipToActive(p)){
			String type = Main.db.getVipToActiveType(p);
			String time = Main.db.getVipToActiveTime(p);
			Main.db.activeVip(p, type, time);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "perm user " + p.getName() + " group set " + type);
			p.sendMessage(toColoredString(Vars.tag + Vars.defaultColor + "Seu rank " + Vars.infoColor + type.toUpperCase() + Vars.defaultColor 
					+ " foi ativado com sucesso. Expira em " + Vars.infoColor + time + Vars.defaultColor + " dias"));
		}else{
			if(Main.db.isVip(p)){
				Calendar ativo = Calendar.getInstance();
				ativo.setTimeInMillis(Long.valueOf(Main.db.getVipActivedDate(p)));
				
				Calendar expire = Calendar.getInstance();
				expire.setTimeInMillis(Long.valueOf(Main.db.getVipActivedDate(p)));
				expire.add(Calendar.DAY_OF_MONTH, Integer.valueOf(Main.db.getVipTime(p)));
				
				p.sendMessage(toColoredString(Vars.infoColor + "VIP INFO\n"
						+ Vars.infoColor + "Tipo: " + Vars.defaultColor + (Main.db.getVipType(p)).toUpperCase() + "\n"						
						+ Vars.infoColor + "Ativo em: " + Vars.defaultColor + ativo.get(Calendar.DAY_OF_MONTH) + "/" + ativo.get(Calendar.MONTH) + "/" + ativo.get(Calendar.YEAR) + "\n"	
						+ Vars.infoColor + "Expira em: " + Vars.defaultColor + expire.get(Calendar.DAY_OF_MONTH) + "/" + expire.get(Calendar.MONTH) + "/" + expire.get(Calendar.YEAR)));
			}else{
				p.sendMessage(toColoredString(Vars.tag + Vars.defaultColor + "Voce nao e VIP. Que tal adquirir em nossa loja em? ;P"));
			}
		}
		
	}
	
	public static void checkExpiration(Player p){
		if(Main.db.isVip(p)){
			if(Long.valueOf(Main.db.getVipActivedDate(p)) - Calendar.getInstance().getTimeInMillis() >= TimeUnit.MILLISECONDS.convert(Long.valueOf(Main.db.getVipTime(p)), TimeUnit.DAYS)){
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "perm user " + p.getName() + " group set default");
				Main.db.removeVip(p);
				p.sendMessage(toColoredString(Vars.tag + "&cSeu Rank expirou. Adquira novamente em nosso site ;P"));
			}
		}
	}
	
}
