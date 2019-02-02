package com.fmbdev.Evolved;

import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener{

	Main plugin;
	public PlayerListener(Main instance) {
		this.plugin = instance;
	}
	
	//When a player joins the server
	@EventHandler
	public void onPJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		String pname = p.getName();
	
		//Setting Bukkit Join Message to NULL
		e.setJoinMessage(null);
		
		//If player has joined before
		if (p.hasPlayedBefore()==false){
			p.sendMessage(ChatColor.GREEN + "Welcome to the server!" +pname+ "Hope you enjoy!" + ChatColor.RESET);
		}else if (p.hasPlayedBefore()==true) {
			p.sendMessage(ChatColor.BLUE + "Welcome back! " +pname+ "!" + ChatColor.RESET);
		}
	}
	
	//When a player leaves the server
	@EventHandler
	public void onPQuit(PlayerQuitEvent e){
		e.setQuitMessage(null);
	}
	
	//When a player dies in the server
	@EventHandler
	public void onPDeath(PlayerDeathEvent e){
		e.setDeathMessage(ChatColor.ITALIC + "You must upgrade yourself!");		
	}

	//When a player TRIES to drop an item/block
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	//When a players item(s) TRY to drop on death
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        List <ItemStack> list = e.getDrops();
        Iterator<ItemStack> i = list.iterator();
        while(i.hasNext()){
			@SuppressWarnings("unused")
			ItemStack item = i.next();
                i.remove();
               
        }
	}
	
    //When a player crouches apply effect
	@EventHandler
	public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
	      Player player = event.getPlayer();
	      if(!player.isSneaking()) {
				player.sendMessage(ChatColor.GREEN + "To the skies!");
				player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2000, 1));
		}else ;{
			if(player.isSneaking()) {
				player.removePotionEffect(PotionEffectType.LEVITATION);	
			}
		}
	}
}
