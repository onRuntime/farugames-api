package net.farugames.api.bungee.commands.general;

import net.farugames.api.bungee.Methods;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {

	public HubCommand() {
		super("hub", "", "lobby");
	}

	
	// Je sais c'est pas opti mais c'est pour qu'on arrete de me dire "fait la commande /hub"
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.getServer().getInfo().getName().contains("hub")) {
				//error msg
			} else {
				ServerInfo target = ProxyServer.getInstance().getServerInfo("hub1");
				p.sendMessage("");
				p.sendMessage(Methods.getPrefixChat() + "§aRetour au hub...");
				p.sendMessage("");
				p.connect(target);
			}
		}
	}

}
