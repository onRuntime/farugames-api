package net.farugames.api.spigot.menus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import net.farugames.api.spigot.Main;
import net.farugames.api.tools.builders.items.InventoryBuilder;

public class ExampleMenu implements Listener {

	public String invName = "Titre du Menu";
	public Map<Player, Inventory> menuInventoryMap = new HashMap<Player, Inventory>();

	public ExampleMenu(Player player) {
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
		Inventory inventory = new InventoryBuilder(invName)
				
				.addLine(new String[] { "", "", "", "", "", "", "", "", "" })
				.addLine(new String[] { "", "", "", "", "", "", "", "", "" })
				.addLine(new String[] { "", "", "", "", "", "", "", "", "" })
				.addLine(new String[] { "", "", "", "", "", "", "", "", "" })
				.addLine(new String[] { "", "", "", "", "", "", "", "", "" })
				.addLine(new String[] { "", "", "", "", "", "", "", "", "" })
				
				.build(player);
		this.menuInventoryMap.put(player, inventory);
		player.openInventory(menuInventoryMap.get(player));
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		if (event.getInventory() == null)
			return;
		if (menuInventoryMap.get(player) == null)
			return;
		if (menuInventoryMap.get(player).equals(event.getInventory()))
			menuInventoryMap.remove(player);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (event.getInventory() == null)
			return;
		if (event.getCurrentItem() == null)
			return;
		if (event.getCurrentItem().getType().equals(Material.AIR))
			return;
		if (!(event.getInventory().getName().equalsIgnoreCase(invName)))
			return;
		if (!(event.getInventory().equals(menuInventoryMap.get(player))))
			return;
		event.setCancelled(true);
		switch (event.getCurrentItem().getType()) {
		case COMMAND:
			break;
		default:
			break;
		}
	}
}