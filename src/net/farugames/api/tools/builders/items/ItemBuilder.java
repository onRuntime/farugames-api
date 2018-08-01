package net.farugames.api.tools.builders.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemBuilder {
	
	private int amount;
    private String name;
    private Material material;
    private Byte data;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private List<ItemFlag> flags;
    
    public ItemBuilder() {
        this.amount = 1;
        this.name = "§r";
        this.material = Material.STONE;
        this.data = Byte.valueOf((byte) 0);
        this.lore = new ArrayList<String>();
        this.enchantments = new HashMap<Enchantment, Integer>();
        this.flags = new ArrayList<ItemFlag>();
    }
    
    public ItemBuilder withAmount(final int amount) {
        this.amount = amount;
        return this;
    }
    
    public ItemBuilder withName(final String name) {
        this.name = name;
        return this;
    }
    
    public ItemBuilder withMaterial(final Material material) {
    	this.material = material;
    	return this;
    }
    
    public ItemBuilder withData(final Byte data) {
    	this.data = data;
    	return this;
    }
    
    public ItemBuilder withLore(final List<String> lore) {
        this.lore = lore;
        return this;
    }
    
    public ItemBuilder withLore(final String... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }
    
    public ItemBuilder withEnchantments(final Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }
    
    public ItemBuilder addEnchantment(final Enchantment enchantment, final int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }
    
    public ItemBuilder withFlags(final List<ItemFlag> flags) {
        this.flags = flags;
        return this;
    }
    
    public ItemBuilder withFlags(final ItemFlag... flags) {
        this.flags = Arrays.asList(flags);
        return this;
    }
    
    public ItemBuilder addFlag(final ItemFlag flag) {
        this.flags.add(flag);
        return this;
    }
    
	public ItemStack build() {
    	ItemStack itemStack = new ItemStack(this.material, this.amount, this.data);
		ItemMeta itemMeta;
        if ((this.name != null) || (this.lore != null)) {
			itemMeta = itemStack.getItemMeta();
			if (this.name != null) {
				itemMeta.setDisplayName(this.name);
			}
			if (this.lore != null) {
				itemMeta.setLore(this.lore);
			}
			itemStack.setItemMeta(itemMeta);
		}
		if (this.enchantments != null) {
			for (Enchantment ench : this.enchantments.keySet()) {
				int level = ((Integer) this.enchantments.get(ench)).intValue();
				itemStack.addUnsafeEnchantment(ench, level);
			}
		}
        return itemStack;
    }
}
