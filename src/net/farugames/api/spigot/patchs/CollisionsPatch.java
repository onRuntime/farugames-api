package net.farugames.api.spigot.patchs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;
import java.util.UUID;

public class CollisionsPatch {

	@SuppressWarnings("deprecation")
    public static void patch(UUID uuid) {
		Player player = Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid) : null;
		if(player == null) return;
        Scoreboard sb = player.getScoreboard();
        Set<Team> teams = sb.getTeams();
        if (teams.size() == 0) {
            sb.registerNewTeam("nocollision").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            sb.getTeam("nocollision").addPlayer(player);
        } else {
            for (Team t : teams) {
                t.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            }
        }
    }
}
