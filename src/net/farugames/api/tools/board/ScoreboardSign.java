package net.farugames.api.tools.board;

import net.minecraft.server.v1_9_R2.*;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zyuiop Updated by MrZalTy
 */
public class ScoreboardSign {
	private boolean created = false;
	private final VirtualTeam[] lines = new VirtualTeam[15];
	private final Player player;
	private String objectiveName;

	public ScoreboardSign(Player player, String objectiveName) {
		this.player = player;
		this.objectiveName = objectiveName;
	}

	public void create() {
		if (created)
			return;

		PlayerConnection player = getPlayer();
		player.sendPacket(createObjectivePacket(0, objectiveName));
		player.sendPacket(setObjectiveSlot());
		int i = 0;
		while (i < lines.length)
			sendLine(i++);

		created = true;
	}

	public void destroy() {
		if (!created)
			return;

		getPlayer().sendPacket(createObjectivePacket(1, null));
		for (VirtualTeam team : lines)
			if (team != null)
				getPlayer().sendPacket(team.removeTeam());

		created = false;
	}

	public void setObjectiveName(String name) {
		this.objectiveName = name;
		if (created)
			getPlayer().sendPacket(createObjectivePacket(2, name));
	}

	public void setLine(int line, String value) {
		VirtualTeam team = getOrCreateTeam(line);
		String old = team.getCurrentPlayer();

		if (old != null && created)
			getPlayer().sendPacket(removeLine(old));

		team.setValue(value);
		sendLine(line);
	}

	public void removeLine(int line) {
		VirtualTeam team = getOrCreateTeam(line);
		String old = team.getCurrentPlayer();

		if (old != null && created) {
			getPlayer().sendPacket(removeLine(old));
			getPlayer().sendPacket(team.removeTeam());
		}

		lines[line] = null;
	}

	public String getLine(int line) {
		if (line > 14)
			return null;
		if (line < 0)
			return null;
		return getOrCreateTeam(line).getValue();
	}

	public List<VirtualTeam> getLines() {
		List<VirtualTeam> list = new ArrayList<VirtualTeam>();
		for(Integer i = 0; i <= 15; i++) {
			if(lines[i].name != null) list.add(lines[i]);
		}
		return list;
	}

	public VirtualTeam getTeam(int line) {
		if (line > 14)
			return null;
		if (line < 0)
			return null;
		return getOrCreateTeam(line);
	}

	private PlayerConnection getPlayer() {
		return ((CraftPlayer) player).getHandle().playerConnection;
	}

	@SuppressWarnings("rawtypes")
	private void sendLine(int line) {
		if (line > 14)
			return;
		if (line < 0)
			return;
		if (!created)
			return;

		int score = (15 - line);
		VirtualTeam val = getOrCreateTeam(line);
		for (Packet packet : val.sendLine())
			getPlayer().sendPacket(packet);
		getPlayer().sendPacket(sendScore(val.getCurrentPlayer(), score));
		val.reset();
	}

	private VirtualTeam getOrCreateTeam(int line) {
		if (lines[line] == null)
			lines[line] = new VirtualTeam("__fakeScore" + line);

		return lines[line];
	}

	private PacketPlayOutScoreboardObjective createObjectivePacket(int mode, String displayName) {
		PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective();
		setField(packet, "a", player.getName());

		setField(packet, "d", mode);

		if (mode == 0 || mode == 2) {
			setField(packet, "b", displayName);
			setField(packet, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
		}

		return packet;
	}

	private PacketPlayOutScoreboardDisplayObjective setObjectiveSlot() {
		PacketPlayOutScoreboardDisplayObjective packet = new PacketPlayOutScoreboardDisplayObjective();
		setField(packet, "a", 1);
		setField(packet, "b", player.getName());

		return packet;
	}

	private PacketPlayOutScoreboardScore sendScore(String line, int score) {
		PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(line);
		setField(packet, "b", player.getName());
		setField(packet, "c", score);
		setField(packet, "d", PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE);

		return packet;
	}

	private PacketPlayOutScoreboardScore removeLine(String line) {
		return new PacketPlayOutScoreboardScore(line);
	}

	public class VirtualTeam {
		private final String name;
		private String prefix;
		private String suffix;
		private String currentPlayer;
		private String oldPlayer;

		private boolean prefixChanged, suffixChanged, playerChanged = false;
		private boolean first = true;

		private VirtualTeam(String name, String prefix, String suffix) {
			this.name = name;
			this.prefix = prefix;
			this.suffix = suffix;
		}

		private VirtualTeam(String name) {
			this(name, "", "");
		}

		public String getName() {
			return name;
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			if (this.prefix == null || !this.prefix.equals(prefix))
				this.prefixChanged = true;
			this.prefix = prefix;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			if (this.suffix == null || !this.suffix.equals(prefix))
				this.suffixChanged = true;
			this.suffix = suffix;
		}

		private PacketPlayOutScoreboardTeam createPacket(int mode) {
			PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam();
			setField(packet, "a", name);
			setField(packet, "b", "");
			setField(packet, "c", prefix);
			setField(packet, "d", suffix);
			setField(packet, "i", 0);
			setField(packet, "e", "always");
			setField(packet, "g", 0);
			setField(packet, "i", mode);

			return packet;
		}

		public PacketPlayOutScoreboardTeam createTeam() {
			return createPacket(0);
		}

		public PacketPlayOutScoreboardTeam updateTeam() {
			return createPacket(2);
		}

		public PacketPlayOutScoreboardTeam removeTeam() {
			PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam();
			setField(packet, "a", name);
			setField(packet, "i", 1);
			first = true;
			return packet;
		}

		public void setPlayer(String name) {
			if (this.currentPlayer == null || !this.currentPlayer.equals(name))
				this.playerChanged = true;
			this.oldPlayer = this.currentPlayer;
			this.currentPlayer = name;
		}

		public Iterable<PacketPlayOutScoreboardTeam> sendLine() {
			List<PacketPlayOutScoreboardTeam> packets = new ArrayList<>();

			if (first) {
				packets.add(createTeam());
			} else if (prefixChanged || suffixChanged) {
				packets.add(updateTeam());
			}

			if (first || playerChanged) {
				if (oldPlayer != null)
					packets.add(addOrRemovePlayer(4, oldPlayer));
				packets.add(changePlayer());
			}

			if (first)
				first = false;

			return packets;
		}

		public void reset() {
			prefixChanged = false;
			suffixChanged = false;
			playerChanged = false;
			oldPlayer = null;
		}

		public PacketPlayOutScoreboardTeam changePlayer() {
			return addOrRemovePlayer(3, currentPlayer);
		}

		@SuppressWarnings("unchecked")
		public PacketPlayOutScoreboardTeam addOrRemovePlayer(int mode, String playerName) {
			PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam();
			setField(packet, "a", name);
			setField(packet, "i", mode);

			try {
				Field f = packet.getClass().getDeclaredField("h");
				f.setAccessible(true);
				((List<String>) f.get(packet)).add(playerName);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}

			return packet;
		}

		public String getCurrentPlayer() {
			return currentPlayer;
		}

		public String getValue() {
			return getPrefix() + getCurrentPlayer() + getSuffix();
		}

		public void setValue(String value) {
			if (value.length() <= 16) {
				setPrefix("");
				setSuffix("");
				setPlayer(value);
			} else if (value.length() <= 32) {
				setPrefix(value.substring(0, 16));
				setPlayer(value.substring(16));
				setSuffix("");
			} else if (value.length() <= 48) {
				setPrefix(value.substring(0, 16));
				setPlayer(value.substring(16, 32));
				setSuffix(value.substring(32));
			} else {
				throw new IllegalArgumentException(
						"Too long value ! Max 48 characters, value was " + value.length() + " !");
			}
		}
	}

	private static void setField(Object edit, String fieldName, Object value) {
		try {
			Field field = edit.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(edit, value);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
