package net.farugames.api.tools.builders.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import net.farugames.api.tools.reflection.Reflections;

public class ItemBuilder {

	private ItemType itemType = ItemType.BLOCK;
	
	private Material material = Material.STONE;
	private Byte data = (byte) 0;
	private String head = null;
	private GameProfile profile = null;
	
	private Base64 base = new Base64();
	
	private Integer amount = 1;
    private String name = "Unknow name";
    
    private List<String> lore = new ArrayList<String>();
    private Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
    private List<ItemFlag> flags = new ArrayList<ItemFlag>();
	
	public ItemBuilder(final ItemType itemType, final Object type) {
		if(itemType == ItemType.BLOCK && type instanceof Material) {
			this.itemType = itemType;
			this.material = Material.valueOf(String.valueOf(type));
			
		} else if(itemType == ItemType.PLAYER_HEAD && type instanceof String) {
			this.itemType = itemType;
			this.head = String.valueOf(type);
			this.material = Material.SKULL_ITEM;
			this.data = (byte) 3;
			
		} else if(itemType == ItemType.CUSTOM_HEAD && type instanceof String) {
			this.itemType = itemType;
			this.profile = new GameProfile(UUID.randomUUID(), null);
	    	PropertyMap propertyMap = profile.getProperties();
	    	if(propertyMap == null) {
	    		throw new IllegalStateException("Unknow PropertyMap.");
	    	}
	    	byte[] encodedData = base.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { String.valueOf(type) }).getBytes());
	    	propertyMap.put("textures", new Property("textures", new String(encodedData)));
	    	this.material = Material.SKULL_ITEM;
	    	this.data = (byte) 3;
		}
	}
	
	public ItemBuilder withData(final Byte data) {
		if(this.itemType != ItemType.BLOCK) return null;
		this.data = data;
		return this;
	}
	
	public ItemBuilder withAmount(final Integer amount) {
		this.amount = amount;
		return this;
	}
	
	public ItemBuilder withName(final String name) {
		this.name = name;
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
	
	public ItemType getType() {
		return this.itemType;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public Byte getData() {
		return this.data;
	}
	
	public String getHead() {
		return this.head;
	}
	
	public GameProfile getTexture() {
		return this.profile;
	}
	
	public Integer getAmount() {
		return this.amount;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<String> getLore() {
		return this.lore;
	}
	
	public Map<Enchantment, Integer> getEnchantments() {
		return this.enchantments;
	}
	
	public List<ItemFlag> getFlags() {
		return this.flags;
	}
	
	public ItemStack build() {
    	ItemStack itemStack = new ItemStack(this.material, this.amount, this.data);
    	ItemMeta itemMeta = itemStack.getItemMeta();
    	
        if(this.profile != null) Reflections.getField(((SkullMeta) itemMeta).getClass(), "profile", GameProfile.class).set(((SkullMeta) itemMeta), this.profile);
        if(this.head != null) ((SkullMeta) itemMeta).setOwner(this.head);
		if (this.name != null) itemMeta.setDisplayName(this.name);
		if (this.lore != null) itemMeta.setLore(this.lore);
		if(this.enchantments != null) {
			for(Enchantment ench : this.enchantments.keySet()) {
				int level = ((Integer) this.enchantments.get(ench)).intValue();
				itemStack.addUnsafeEnchantment(ench, level);
			}
		}
		if(this.flags != null) {
			for(ItemFlag flag : this.flags) {
				itemMeta.addItemFlags(flag);
			}
		}
		itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
	
	public static enum ItemType {
		BLOCK, PLAYER_HEAD, CUSTOM_HEAD;
	}
}
