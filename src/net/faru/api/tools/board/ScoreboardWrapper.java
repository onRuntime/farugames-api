package net.faru.api.tools.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardWrapper {

	public static final int MAX_LINES = 16;

	private final Scoreboard scoreboard;
	private final Objective objective;

	private final List<String> modifies = new ArrayList<>(MAX_LINES);
	private HashMap<String, String> varNames = new HashMap<>();

	/**
	 * Initiation du scoreboard
	 */
	public ScoreboardWrapper(String title) {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective("dummy", title);
		objective.setDisplayName(title);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	}

	/**
	 * Mettre le titre du scoreboard
	 */
	public void setTitle(String title) {
		objective.setDisplayName(title);
	}

	private String getLineCoded(String line) {
		String result = line;
		while (modifies.contains(result)) {
			result += ChatColor.RESET;
		}
		return result.substring(0, Math.min(40, result.length()));
	}

	/**
	 * Ajoute une ligne au scoreboard et marque une erreur si il y a plus que 16
	 * lignes.
	 */
	public void addLine(String line, String varName) {
		if (modifies.size() > MAX_LINES)
			throw new IndexOutOfBoundsException("You cannot add more than 16 lines.");
		String modified = getLineCoded(line);
		modifies.add(modified);
		objective.getScore(modified).setScore(-(modifies.size()));
		this.varNames.put(varName, modified);
	}

	/**
	 * permet de supprimer une ligne précédement écrite
	 */
	public void removeLine(String varName) {
		if (modifies.size() <= 0) {
			throw new IndexOutOfBoundsException("There is no lines in this scoreboard.");
		}
		scoreboard.resetScores(varNames.get(varName));
		modifies.remove(varNames.get(varName));
	}

	/**
	 * Ajoute un spacer au scoreboard
	 */
	public void addSpacer(String spacerName) {
		addLine(" ", spacerName);
	}

	/**
	 * Ajoute une ligne au scoreboard a la ligne voulue (entre 0 et 15).
	 */
	public void setLine(int index, String line) {
		if (index < 0 || index >= MAX_LINES)
			throw new IndexOutOfBoundsException("The index cannot be negative or higher than 15.");
		String oldModified = modifies.get(index);
		scoreboard.resetScores(oldModified);
		String modified = getLineCoded(line);
		modifies.set(index, modified);
		objective.getScore(modified).setScore(-(index + 1));
	}

	/**
	 * recuperer le scroeboard.
	 */
	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	/**
	 * Just for debug.
	 */
	@Override
	public String toString() {
		String out = "";
		int i = 0;
		for (String string : modifies)
			out += -(i + 1) + ") -> " + string + ";\n";
		return out;
	}
}