package net.faru.api.tools.locations;

import org.bukkit.Location;
import org.bukkit.World;

public enum Locations {

	HUB(-44.5, 14 + 2, -1229.5, 90, 0);
	
	private double x, y, z;
	private float yaw, pitch;
	
	private Locations(double x, double y, double z, float yaw, float pitch) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public float getYaw() {
		return this.yaw;
	}
	
	public float getPitch() {
		return this.pitch;
	}
	
	public static Location getLocation(Locations location, World world) {
		return new Location(world, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}
}
