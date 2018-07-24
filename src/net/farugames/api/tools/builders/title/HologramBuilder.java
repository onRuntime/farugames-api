package net.farugames.api.tools.builders.title;

import net.farugames.api.spigot.SpigotFaruAPI;
import net.minecraft.server.v1_9_R2.EntityArmorStand;
import net.minecraft.server.v1_9_R2.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_9_R2.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HologramBuilder {

	private String message;
	private int stay;
	private Location location;
	private EntityArmorStand stand;
	private List<Player> playersList;
	private Player player_;

	public String getMessage() {
		return message;
	}

	public int getStay() {
		return stay;
	}

	public Location getLocation() {
		return location;
	}

	public HologramBuilder editMessage(String message) {
		this.message = message;
		return this;
	}

	public HologramBuilder editLocation(Location location) {
		this.location = location;
		return this;
	}

	public HologramBuilder editStay(int stay) {
		this.stay = stay;
		return this;
	}

	public HologramBuilder() {
	}

	public HologramBuilder withMessage(String message) {
		this.message = message;
		return this;
	}

	public HologramBuilder withStay(int stay) {
		this.stay = stay;
		return this;
	}

	public HologramBuilder withLocation(Location location) {
		this.location = location;
		return this;
	}

	public void sendToPlayer(Player player) {
		stand = getArmorStand();
		this.player_ = player;
		PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		if (stay != 0) {
			SpigotFaruAPI.getInstance().getServer().getScheduler().runTaskLater(SpigotFaruAPI.getInstance(), () -> {
				PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());
				((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroy);
			}, stay);
		}
	}

	public void sendToPlayers(Collection<? extends Player> players) {
		stand = getArmorStand();
		this.playersList = new ArrayList<Player>();
		PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);
		for (Player playerCollection : players) {
			this.playersList.add(playerCollection);
			((CraftPlayer) playerCollection).getHandle().playerConnection.sendPacket(packet);
		}
		if (stay != 0) {
			SpigotFaruAPI.getInstance().getServer().getScheduler().runTaskLater(SpigotFaruAPI.getInstance(), () -> {
				PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());
				for (Player playerCollection : players)
					((CraftPlayer) playerCollection).getHandle().playerConnection.sendPacket(destroy);
			}, stay);
		}
	}

	private EntityArmorStand getArmorStand() {
		EntityArmorStand stand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle());
		stand.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
		stand.setCustomName(message);
		stand.setCustomNameVisible(true);
		stand.setGravity(false);
		stand.setInvisible(true);
		return stand;
	}

	public void destroy() {
		if (this.player_ != null) {
			PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());
			((CraftPlayer) player_).getHandle().playerConnection.sendPacket(destroy);
		}
	}

	public void destroyAllPlayers() {
		PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());
		for (Player playerCollection : playersList) {
			if (playerCollection != null) {
				((CraftPlayer) playerCollection).getHandle().playerConnection.sendPacket(destroy);
			}
		}
	}
}