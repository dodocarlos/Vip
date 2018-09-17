package me.dodocarlos.vip.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.entity.Player;

public class DB {
	
	public Connection conn;
	public Statement stm;
	
	public DB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		    String url = "jdbc:mysql://" + Vars.server + "/" + Vars.db + "?autoReconnect=true";
		    
		    conn = DriverManager.getConnection(url, Vars.user, Vars.pass);

			stm = conn.createStatement();

			stm.executeUpdate("CREATE TABLE IF NOT EXISTS vipstoactive(id int(11) NOT NULL AUTO_INCREMENT, nick varchar(500), type varchar(500), time varchar(500), PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
			stm.executeUpdate("CREATE TABLE IF NOT EXISTS vipsactived(id int(11) NOT NULL AUTO_INCREMENT, uuid varchar(500), type varchar(500), time varchar(500), activeddate varchar(500), PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getVipsUUID(){
		ArrayList<String> vips = new ArrayList<> ();
		try{
			ResultSet rs = stm.executeQuery("SELECT uuid from vipsactived");			
			while(rs.next()){
				vips.add(rs.getString(1));
			}
		}catch(SQLException e){
			return null;
		}
		return vips;
	}
	
	public String getVipType(Player p){
		String uuid = p.getUniqueId().toString();
		String type = null;
		try{
			ResultSet rs = stm.executeQuery("SELECT type from vipsactived where uuid = '" + uuid + "'");			
			while(rs.next()){
				type = rs.getString(1);
			}
		}catch(SQLException e){
			return null;
		}
		return type;
	}
	
	public String getVipTime(Player p){
		String uuid = p.getUniqueId().toString();
		String time = null;
		try{
			ResultSet rs = stm.executeQuery("SELECT time from vipsactived where uuid = '" + uuid + "'");			
			while(rs.next()){
				time = rs.getString(1);
			}
		}catch(SQLException e){
			return null;
		}
		return time;
	}
	
	public String getVipActivedDate(Player p){
		String uuid = p.getUniqueId().toString();
		String time = null;
		try{
			ResultSet rs = stm.executeQuery("SELECT activeddate from vipsactived where uuid = '" + uuid + "'");			
			while(rs.next()){
				time = rs.getString(1);
			}
		}catch(SQLException e){
			return null;
		}
		return time;
	}
	
	public void activeVip(Player p, String type, String time){
		String uuid = p.getUniqueId().toString();
		try{
			stm.executeUpdate("INSERT INTO vipsactived(uuid, type, time, activeddate) values('" + uuid + "', '" + type + "', '" + time + "', '" + new Date().getTime() + "')");			
			stm.executeUpdate("DELETE FROM vipstoactive WHERE nick = '" + p.getName() + "'");			
		}catch(SQLException e){
		}
	}
	
	public void removeVip(Player p){
		String uuid = p.getUniqueId().toString();
		try{
			stm.executeUpdate("DELETE FROM vipsactived WHERE uuid = '" + uuid + "'");			
		}catch(SQLException e){
		}
	}
	
	public String getVipToActiveType(Player p){
		String nick = p.getName();
		String type = null;
		try{
			ResultSet rs = stm.executeQuery("SELECT type from vipstoactive where nick = '" + nick + "'");			
			while(rs.next()){
				type = rs.getString(1);
			}
		}catch(SQLException e){
			return null;
		}
		return type;
	}
	
	public String getVipToActiveTime(Player p){
		String nick = p.getName();
		String time = null;
		try{
			ResultSet rs = stm.executeQuery("SELECT time from vipstoactive where nick = '" + nick + "'");			
			while(rs.next()){
				time = rs.getString(1);
			}
		}catch(SQLException e){
			return null;
		}
		return time;
	}
	
	public boolean hasVipToActive(Player p){
		String nick = p.getName();
		boolean has = false;
		try{
			ResultSet rs = stm.executeQuery("SELECT type from vipstoactive where nick = '" + nick + "'");			
			while(rs.next()){
				has = true;
			}
		}catch(SQLException e){
			return false;
		}
		return has;
	}
	
	public boolean isVip(Player p){
		String uuid = p.getUniqueId().toString();
		boolean is = false;
		try{
			ResultSet rs = stm.executeQuery("SELECT type from vipsactived where uuid = '" + uuid + "'");			
			while(rs.next()){
				is = true;
			}
		}catch(SQLException e){
			return false;
		}
		return is;
	}
	
	public void takeOneDay(Player p){
		String uuid = p.getUniqueId().toString();
		try{
			stm.executeQuery("UPDATE vipsactived SET time = time-1 where uuid = '" + uuid + "'");			
		}catch(SQLException e){
		}
	}
	
}
