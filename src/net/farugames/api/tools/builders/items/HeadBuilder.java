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

public class HeadBuilder {
	
    private int amount;
    private String name;
    private String head;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private List<ItemFlag> flags;
    private boolean unbreakable;
    
    private GameProfile profile;
    
    private static final Base64 base = new Base64();
    
    public HeadBuilder() {
        this.amount = 1;
        this.name = "§r";
        this.head = null;
        this.lore = new ArrayList<String>();
        this.enchantments = new HashMap<Enchantment, Integer>();
        this.flags = new ArrayList<ItemFlag>();
        this.unbreakable = false;
    }
    
    public HeadBuilder withAmount(final int amount) {
        this.amount = amount;
        return this;
    }
    
    public HeadBuilder withName(final String name) {
        this.name = name;
        return this;
    }
    
    public HeadBuilder withCustom(final String texture) {
    	this.profile = new GameProfile(UUID.randomUUID(), null);
    	PropertyMap propertyMap = profile.getProperties();
    	if(propertyMap == null) {
    		throw new IllegalStateException("Unknew PropertyMap.");
    	}
    	byte[] encodedData = base.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] {texture}).getBytes());
    	propertyMap.put("textures", new Property("textures", new String(encodedData)));
    	return this;
    }
    
    public HeadBuilder withHead(final String head) {
        this.head = head;
        return this;
    }
    
    public HeadBuilder withLore(final List<String> lore) {
        this.lore = lore;
        return this;
    }
    
    public HeadBuilder withLore(final String... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }
    
    public HeadBuilder withEnchantments(final Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }
    
    public HeadBuilder addEnchantment(final Enchantment enchantment, final int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }
    
    public HeadBuilder withFlags(final List<ItemFlag> flags) {
        this.flags = flags;
        return this;
    }
    
    public HeadBuilder withFlags(final ItemFlag... flags) {
        this.flags = Arrays.asList(flags);
        return this;
    }
    
    public HeadBuilder addFlag(final ItemFlag flag) {
        this.flags.add(flag);
        return this;
    }
    
    public HeadBuilder withUnbreakable(final boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ItemStack build() {
    	
        final ItemStack item = new ItemStack(Material.SKULL_ITEM, this.amount);
        item.setDurability((short) 3);
        final SkullMeta meta = (SkullMeta) item.getItemMeta();
        if(this.head == null) {
        	Reflections.getField(meta.getClass(), "profile", GameProfile.class).set(meta, this.profile);
        } else {
        	meta.setOwner(this.head);
        }
        meta.setDisplayName(this.name);
        meta.setLore((List) this.lore);
        this.enchantments.entrySet().forEach(entry -> meta.addEnchant((Enchantment) entry.getKey(), (int) entry.getValue(), true));
        this.flags.forEach(entry -> meta.addItemFlags(new ItemFlag[] { entry }));
        meta.spigot().setUnbreakable(this.unbreakable);
        item.setItemMeta((ItemMeta) meta);
        return item;
    }
}
