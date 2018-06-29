package net.faru.api.tools.builders.title;

import java.lang.reflect.Field;
import java.util.Collection;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_9_R2.PlayerConnection;

public class TablistBuilder {

	private String header;
	private String footer;
	private String headerKey;
	private String footerKey;

	public String getHeader() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	public String getHeaderKey() {
		return headerKey;
	}

	public String getFooterKey() {
		return footerKey;
	}

	public TablistBuilder withHeaderKey(String key) {
		this.headerKey = key;
		return this;
	}

	public TablistBuilder withFooterKey(String key) {
		this.footerKey = key;
		return this;
	}

	public TablistBuilder withHeader(String header) {
		this.header = header;
		return this;
	}

	public TablistBuilder withFooter(String footer) {
		this.footer = footer;
		return this;
	}

	private IChatBaseComponent buildChatComponent(String msg) {
		return IChatBaseComponent.ChatSerializer.a("{'text':'" + msg + "'}");
	}

	public void sendToPlayers(Collection<Player> players) {
		players.forEach(this::sendTo);
	}

	public void sendToOnlinePlayers(Collection<Player> players) {
		players.forEach(this::sendTo);
	}

	public void sendTo(Player player) {
		packet(player, header, footer);
	}

	private void packet(Player player, String header, String footer) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

		try {
			if (header != null) {
				Field hfield = packet.getClass().getDeclaredField("a");
				hfield.setAccessible(true);
				hfield.set(packet, buildChatComponent(header));
				hfield.setAccessible(false);
			}
			if (footer != null) {
				Field ffield = packet.getClass().getDeclaredField("b");
				ffield.setAccessible(true);
				ffield.set(packet, buildChatComponent(footer));
				ffield.setAccessible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connection.sendPacket(packet);
	}

}
