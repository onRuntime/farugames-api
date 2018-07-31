package net.farugames.api.core.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.MessageFormat;

import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

/**
 * Created by Rémi on 14/01/2016.
 */
public class I18n {

    /**
     * Permet d'ajouter le support d'une langue à un plugin.
     * @param proxyFaruGamesAPI Plugin
     * @param lang Lang
     */
    public static void supportProxyTranslate(Lang lang) {
    	if (!ProxyFaruGamesAPI.getInstance().getDataFolder().exists())
    		ProxyFaruGamesAPI.getInstance().getDataFolder().mkdir();

        File file = new File(ProxyFaruGamesAPI.getInstance().getDataFolder(), "lang."+ lang.getCode().toLowerCase() +".yml");

     
        if (!file.exists()) {
            try (InputStream in = ProxyFaruGamesAPI.getInstance().getResourceAsStream("lang."+ lang.getCode().toLowerCase() +".yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		try {
			Configuration configuration = ConfigurationProvider.getProvider(net.md_5.bungee.config.YamlConfiguration.class).load(new File(ProxyFaruGamesAPI.getInstance().getDataFolder(), "lang."+ lang.getCode().toLowerCase() +".yml"));
			for(String l : configuration.getKeys()) {
	            lang.getWords().put(l, configuration.getString(l));
	            System.out.println(lang.getWords());
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
    public static void supportSpigotTranslate(org.bukkit.plugin.Plugin plugin, Lang lang) {
        InputStream langIn = plugin.getResource("lang."+ lang.getCode().toLowerCase() +".yml");
        if(langIn == null) {
            plugin.getLogger().warning("Le fichier lang."+ lang.getCode().toLowerCase() +".yml n'existe pas !");
            return;
        }
        org.bukkit.configuration.file.FileConfiguration configuration = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(langIn);
        for(String l : configuration.getRoot().getKeys(true)) {
            lang.getWords().put(l, configuration.getString(l));
        }
    }

    /**
     * Retourne la valeur, si la clef n'est pas trouvé elle renvoie la clef.
     * @param key String
     * @param values String...
     * @return String
     */
    public static String tl(Lang lang, String key, String... values) {
    	
        if(lang.getWords().containsKey(key)) {
            String text = lang.getWords().get(key);
            MessageFormat messageFormat = new MessageFormat(text);
            text = text.replaceAll("\\{(\\D*?)\\}", "\\[$1\\]");
            return messageFormat.format(values);
        }
        return key;
    }
}

