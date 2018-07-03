package net.farugames.api.tools.player;

import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_9_R2.EntityPlayer;

public class PlayerUtils {
	
	public static void kill(Player player) {
		player.setHealth(0.0D);
	}

	public static void damage(Player player, double damage) {
		player.damage(damage);
	}

	public static void heal(Player player) {
		player.setHealth(20.0D);
	}

	public static void heal(Player player, double value) {
		double d = player.getHealth();
		d += value;
		player.setHealth(d);
	}

	public static void feed(Player player) {
		player.setFoodLevel(20);
	}

	public static void feed(Player player, int value) {
		int d = player.getFoodLevel();
		d += value;
		player.setFoodLevel(d);
	}

	public static void starve(Player player) {
		player.setFoodLevel(0);
	}

	public static void starve(Player player, int value) {
		int d = player.getFoodLevel();
		d -= value;
		player.setFoodLevel(d);
	}

	public static UUID getUUID(Player player) {
		return player.getUniqueId();
	}

	public static void setGamemode(Player player, GameMode gamemode) {
		player.setGameMode(gamemode);
	}

	public static void addPotionEffect(Player player, PotionEffectType potioneffect, int time, int level) {
		player.addPotionEffect(new PotionEffect(potioneffect, time * 20, level));
	}

	public static void removePotionEffect(Player player, PotionEffectType potioneffect) {
		player.removePotionEffect(potioneffect);
	}

	public static void removeAllPotionEffect(Player player) {
		for (PotionEffect pet : player.getActivePotionEffects()) {
			player.removePotionEffect(pet.getType());
		}
	}

	public static void kick(Player player, String message) {
		player.kickPlayer(message);
	}

	public static void teleport(Player player, Object object) {
		if ((object instanceof Player)) {
			Player p = (Player) object;
			player.teleport(p);
		} else if ((object instanceof Location)) {
			Location loc = (Location) object;
			player.teleport(loc);
		} else if ((object instanceof Entity)) {
			Entity ent = (Entity) object;
			player.teleport(ent);
		} else if ((object instanceof LivingEntity)) {
			LivingEntity lent = (LivingEntity) object;
			player.teleport(lent);
		}
	}

	public static void burn(Player player, int ticks) {
		player.setFireTicks(ticks);
	}

	public static void extinguish(Player player) {
		player.setFireTicks(0);
	}

	public static void unlockAward(Player player, Achievement achievement) {
		player.awardAchievement(achievement);
	}

	public static Inventory getInventory(Player player) {
		return player.getInventory();
	}

	public static void addItemToInventory(Player player, ItemStack itemstack) {
		player.getInventory().addItem(new ItemStack[] { itemstack });
	}

	public static Boolean inventoryContain(Player player, Material material) {
		if (player.getInventory().contains(material)) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

	public static Boolean inventoryContain(Player player, Material material, int amount) {
		if (player.getInventory().contains(material, amount)) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

	public String getInventoryName(Player player) {
		return player.getInventory().getName();
	}

	public String getInventoryTitle(Player player) {
		return player.getInventory().getTitle();
	}

	public Boolean inventoryHasEmptySlot(Player player) {
		ItemStack[] arrayOfItemStack;
		int j = (arrayOfItemStack = player.getInventory().getContents()).length;
		for (int i = 0; i < j; i++) {
			ItemStack items = arrayOfItemStack[i];
			if (items == null) {
				return Boolean.valueOf(false);
			}
		}
		return Boolean.valueOf(true);
	}

	public static void clearInventory(Player player) {
		player.getInventory().clear();
	}

	public static Integer getLevel(Player player) {
		return Integer.valueOf(player.getLevel());
	}

	public static void setLevel(Player player, int value) {
		player.setLevel(value);
	}

	public static void setVelocity(Player player, double x, double y, double z) {
		player.setVelocity(new Vector(x, y, z));
	}

	public static Integer getPing(Player player) {
		CraftPlayer cp = (CraftPlayer) player;
		EntityPlayer ep = cp.getHandle();
		return Integer.valueOf(ep.ping);
	}

	public static Boolean isSprinting(Player player) {
		CraftPlayer cp = (CraftPlayer) player;
		EntityPlayer ep = cp.getHandle();
		return Boolean.valueOf(ep.isSprinting());
	}

	public static Boolean isSneaking(Player player) {
		CraftPlayer cp = (CraftPlayer) player;
		EntityPlayer ep = cp.getHandle();
		return Boolean.valueOf(ep.isSneaking());
	}
}
