package net.farugames.api.tools.builders.title;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.farugames.api.spigot.Main;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.Packet;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import net.minecraft.server.v1_9_R2.PlayerConnection;

public class ActionBar {
	private String message;
	private int stay;

	public String getMessage() {
		return this.message;
	}

	public int getStay() {
		return this.stay;
	}

	public void ActionBarBuilder(final String message) {
		this.message = message;
	}

	public ActionBar withStay(final int stay) {
		this.stay = stay;
		return this;
	}

	private IChatBaseComponent buildChatComponent(final String msg) {
		return IChatBaseComponent.ChatSerializer.a("{'text':'" + msg + "'}");
	}

	public void sendTo(final Collection<Player> players) {
		players.forEach(this::sendTo);
	}

	@SuppressWarnings({ "null", "rawtypes" })
	public void sendTo(final Player player) {
		final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		final PacketPlayOutChat packet = new PacketPlayOutChat(this.buildChatComponent(this.message), (byte) 2);
		connection.sendPacket((Packet) packet);
		if (this.stay != 0) {
			final PlayerConnection playerConnection = null;
			Bukkit.getScheduler().runTaskLater((Plugin) Main.getInstance(), () -> {
				final PacketPlayOutChat clear = new PacketPlayOutChat(this.buildChatComponent(""), (byte) 2);
				playerConnection.sendPacket((Packet) clear);
			}, this.stay * 20L);
		}
	}

	public void sendToServer() {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			this.sendTo(player);
		}
	}
}
