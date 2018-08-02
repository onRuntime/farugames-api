package net.farugames.api.tools.builders.items;

import java.util.ArrayList;
import java.util.Arrays;
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

	private ItemType itemType;
	
	private Material material;
	private Byte data;
	private String head;
	private GameProfile profile;
	
	private Base64 base = new Base64();
	
	private Integer amount;
    private String name;
    
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private List<ItemFlag> flags;
	
	public ItemBuilder() {
		this.itemType = ItemType.BLOCK;
		this.material = Material.STONE;
		this.data = (byte) 0;
		this.head = null;
		this.profile = null;
		
		this.amount = 1;
		this.name = "Unknow name";
		
		this.lore = new ArrayList<String>();
	}
	
	public ItemBuilder withType(final ItemType itemType) {
		this.itemType = itemType;
		return this;
	}
	
	public ItemBuilder withMaterial(final Material material) {
		if(this.itemType != ItemType.BLOCK) return null;
		this.material = material;
		this.itemType = ItemType.BLOCK;
		return this;
	}
	
	public ItemBuilder withData(final Byte data) {
		if(this.itemType != ItemType.BLOCK) return null;
		this.data = data;
		return this;
	}
	
	public ItemBuilder withHead(final String head) {
		if(this.itemType != ItemType.PLAYER_HEAD) return null;
		this.material = Material.SKULL_ITEM;
		this.head = head;
		this.itemType = ItemType.PLAYER_HEAD;
		return this;
	}
	
	public ItemBuilder withTexture(final String texture) {
		if(this.itemType != ItemType.CUSTOM_HEAD) return null;
		this.material = Material.SKULL_ITEM;
		this.profile = new GameProfile(UUID.randomUUID(), null);
    	PropertyMap propertyMap = profile.getProperties();
    	if(propertyMap == null) {
    		throw new IllegalStateException("Unknow PropertyMap.");
    	}
    	byte[] encodedData = base.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { texture }).getBytes());
    	propertyMap.put("textures", new Property("textures", new String(encodedData)));
    	this.itemType = ItemType.CUSTOM_HEAD;
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
    	SkullMeta skullMeta;
    	if(this.itemType != ItemType.BLOCK) {
    		skullMeta = (SkullMeta) itemStack.getItemMeta();
            if(this.head == null) {
            	Reflections.getField(skullMeta.getClass(), "profile", GameProfile.class).set(skullMeta, this.profile);
            } else {
            	skullMeta.setOwner(this.head);
            }
    	}
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
	
	public enum ItemType {
		BLOCK, PLAYER_HEAD, CUSTOM_HEAD;
	}
}
