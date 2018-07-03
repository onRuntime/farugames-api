package net.farugames.api.tools.builders.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.farugames.api.spigot.Main;

public class InventoryBuilder implements Listener {

	private HashMap<String, ItemStack> itemsValues = new HashMap<>();
	public Map<Player, Inventory> inventory = new HashMap<Player, Inventory>();
	private List<String[]> lines = new ArrayList<>();
	private String inventoryName;

	public InventoryBuilder(String inventoryName) {
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
		this.inventoryName = inventoryName;
		itemsValues.put("", new ItemStack(Material.AIR));
	}

	public String getInventoryName() {
		return inventoryName;
	}

	private ItemStack getItem(String index) {
		return itemsValues.get(index);
	}

	public InventoryBuilder setItem(String index, ItemStack value) {
		if (itemsValues.get(index) != null)
			itemsValues.remove(index);
		itemsValues.put(index, value);
		return this;
	}

	public InventoryBuilder addLine(String[] lineContent) {
		if (lineContent == null) {
			lines.add(new String[] { "", "", "", "", "", "", "", "", "" });
			return this;
		}
		if (lineContent.length != 9)
			return this;
		lines.add(lineContent);
		return this;
	}

	public Inventory build(Player player) {
		Inventory inventory = Bukkit.createInventory(null, lines.size() * 9, this.inventoryName);
		for (int i = 0; i < lines.size(); i++) {
			String[] lineContent = lines.get(i);
			for (int slot = 0; slot < 9; slot++)
				inventory.setItem(i * 9 + slot, getItem(lineContent[slot]));
		}
		this.inventory.put(player, inventory);
		return inventory;
	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent event) {
		if ((event.getCurrentItem() != null) && (event.getClickedInventory().getName().equalsIgnoreCase(inventoryName))
				&& ((event.getCurrentItem().getType() != Material.AIR) || (event.getCurrentItem().getType() != null))) {
			Player player = (Player) event.getWhoClicked();
			if (!(event.getInventory().equals(inventory.get(player))))
				return;
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onInventoryClose(InventoryCloseEvent event) {
		if (event.getInventory().getName().equalsIgnoreCase(inventoryName)) {
			inventory.remove(event.getPlayer());
		}
	}
}