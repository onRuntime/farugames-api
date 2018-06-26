package net.faru.api.tools.locations;

import org.bukkit.Location;
import org.bukkit.World;

public enum Locations {

	HUB((int) -45.5, 16, (int) -1230.5, (float) -90, (float) 0);
	
	private Integer x, y, z;
	private Float yaw, pitch;
	
	private Locations(Integer x, Integer y, Integer z, Float yaw, Float pitch) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public Integer getX() {
		return this.x;
	}
	
	public Integer getY() {
		return this.y;
	}
	
	public Integer getZ() {
		return this.z;
	}
	
	public Float getYaw() {
		return this.yaw;
	}
	
	public Float getPitch() {
		return this.pitch;
	}
	
	public static Location getLocation(Locations location, World world) {
		return new Location(world, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}
}
