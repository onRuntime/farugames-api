package net.farugames.api.spigot.commands;

import net.farugames.api.core.nick.NickManager;
import net.farugames.api.core.nick.NickPseudoEnum;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.spigot.FaruPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class NickCommand implements CommandExecutor {
    public void cmdNickHelp(Player p, String label) {
        p.sendMessage("");
        p.sendMessage("  §f§l» §6§lNick §f❙ §eHelp");
        p.sendMessage("");
        p.sendMessage("    §8■ §e/" + label + " help" + " §f» §bView help.");
        p.sendMessage("    §8■ §e/" + label + " <nick>" + " §f» §bChange nick.");
        p.sendMessage("    §8■ §e/" + label + " reset" + " §f» §bReset nick.");
        p.sendMessage("");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            FaruPlayer faruPlayer = FaruPlayer.getPlayer(player.getUniqueId());
            if(faruPlayer.getPermissionLevel() >= Rank.YOUTUBER.getPower()){
                if (args.length == 0 || ((args.length ==1) && (args[1].equalsIgnoreCase("random")))) {
                    String rdmNick = NickPseudoEnum.getRandomPseudo();
                    faruPlayer.setNick(true);
                    if(faruPlayer.isNick())
                        NickManager.enableNick(faruPlayer.getUUID(), NickManager.getRealName(faruPlayer.getUUID()), rdmNick,
                                rdmNick);
                    else
                        NickManager.enableNick(faruPlayer.getUUID(), player.getName(), rdmNick,
                                rdmNick);
                    faruPlayer.updateNickAndSkin();
                    faruPlayer.sendCenteredMessage("");
                    faruPlayer.sendCenteredMessage("§f§l» §6§lNick §f§l«");
                    faruPlayer.sendCenteredMessage("You are now nicked "+ rdmNick+".");
                    faruPlayer.sendCenteredMessage("");
                } else {
                    if(args.length >= 2){
                        Rank rank = Rank.PLAYER;
                        if((faruPlayer.getPermissionLevel() >= Rank.ADMIN.getPower()) && (args.length == 3))
                            faruPlayer = FaruPlayer.getPlayer(Bukkit.getPlayer(args[2]).getUniqueId());
                        if(faruPlayer.getPermissionLevel() >= Rank.ADMIN.getPower())
                            rank = Rank.getRankByIdName(args[1]) != null ? Rank.getRankByIdName(args[1]) : Rank.PLAYER;
                        faruPlayer.setNick(true);
                        if(faruPlayer.isNick())
                            NickManager.enableNick(player.getUniqueId(), NickManager.getRealName(faruPlayer.getUUID()),args[0],player.getName());
                        else
                            NickManager.enableNick(player.getUniqueId(), player.getName(),args[0],player.getName());
                        faruPlayer.updateNickAndSkin();
                        faruPlayer.sendCenteredMessage("");
                        faruPlayer.sendCenteredMessage("§f§l» §6§lNick §f§l«");
                        faruPlayer.sendCenteredMessage("You are now nicked "+ args[0]+".");
                        faruPlayer.sendCenteredMessage("");
                    }
                    if(args[0].equalsIgnoreCase("reset")){
                        if((faruPlayer.getPermissionLevel() >= Rank.ADMIN.getPower()) && (args.length == 3))
                            player = Bukkit.getPlayer(args[2]);
                        faruPlayer.setNick(false);
                        NickManager.removeNick(player.getUniqueId());
                        faruPlayer.updateNickAndSkin();
                        faruPlayer.sendCenteredMessage("");
                        faruPlayer.sendCenteredMessage("§f§l» §6§lNick §f§l«");
                        faruPlayer.sendCenteredMessage("You are no more nicked. "+ args[0]);
                        faruPlayer.sendCenteredMessage("");
                    }
                }
            } else {
                //error msg
            }
        }
        return false;
    }
}
