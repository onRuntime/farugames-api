package net.farugames.api.spigot.listeners;

import org.bukkit.event.Listener;

import net.farugames.api.spigot.listeners.entity.EntityDamageListener;
import net.farugames.api.spigot.listeners.player.FoodLevelChangeListener;
import net.farugames.api.spigot.listeners.player.PlayerArmorStandManipulateListener;
import net.farugames.api.spigot.listeners.player.PlayerJoinListener;
import net.farugames.api.spigot.listeners.player.PlayerQuitListener;
import net.farugames.api.spigot.listeners.world.WeatherChangeListener;

public enum Listeners {
	
	ENTITY_DAMAGE_LISTENER(new EntityDamageListener()),
	
	PLAYER_JOIN_LISTENER(new PlayerJoinListener()),
	PLAYER_QUIT_LISTENER(new PlayerQuitListener()),
	FOOD_LEVEL_CHANGE_LISTENER(new FoodLevelChangeListener()),
	PLAYER_ARMOR_STAND_MANIPULATE_LISTENER(new PlayerArmorStandManipulateListener()),
//	PLAYER_BED_ENTER_LISTENER(new PlayerBedEnterListener()),
//	PLAYER_BUCKET_LISTENER(new PlayerBucketListener()),
//	PLAYER_DROP_ITEM_LISTENER(new PlayerDropItemListener()),
//	PLAYER_EXP_CHANGE_LISTENER(new PlayerExpChangeListener()),
//	PLAYER_FISH_LISTENER(new PlayerFishListener()),
//	PLAYER_GAME_MODE_CHANGE_LISTENER(new PlayerGameModeChangeListener()),
//	PLAYER_INTERACT_ENTITY_LISTENER(new PlayerInteractEntityListener()),
//	PLAYER_INTERACT_LISTENER(new PlayerInteractListener()),
	
	WEATHER_CHANGE_LISTENER(new WeatherChangeListener());
	
	private Listener listener;
	
	Listeners(Listener listener) {
		this.listener = listener;
	}
	
	public Listener getListener() {
		return this.listener;
	}
}
