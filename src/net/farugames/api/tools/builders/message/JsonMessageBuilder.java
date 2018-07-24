package net.farugames.api.tools.builders.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonMessageBuilder
{
    public List<JComp> jsonComp;
    
    public JsonMessageBuilder() {
        this.jsonComp = new ArrayList<JComp>();
    }
    
    public JComp newJComp(final String text) {
        final JComp jc = new JComp();
        jc.addText(text);
        return jc;
    }
    
    public void addComponent(final JComp... c) {
        this.jsonComp.addAll(Arrays.asList(c));
    }
    
    public void send() {
        Bukkit.getOnlinePlayers().forEach(this::send);
    }
    
    public void send(final Player... player) {
        for (final Player p : player) {
            this.send(p);
        }
    }
    
    public void send(final List<Player> player) {
        player.forEach(this::send);
    }
    
    private void send(final Player p) {
        final List<JComp> instanceJsonComp = this.jsonComp;
        final StringBuilder temp = new StringBuilder();
        for (final JComp jComp : instanceJsonComp) {
            temp.append((CharSequence)jComp.component);
        }
        temp.deleteCharAt(temp.length() - 1);
        Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("rcjjp_u\u001e", 2) + p.getName() + OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(" ", 0) + OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("Y  *", 2) + (Object)temp + OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("]", 0));
    }
    
    public static String OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(final String sc, final Integer va) {
        final char[] cj = sc.toCharArray();
        String ex = "";
        char[] array;
        for (int length = (array = cj).length, i = 0; i < length; ++i) {
            int ci = array[i] - '0' + Math.round(sc.getBytes()[0] / 200) * 1;
            ci += va;
            final char ch = (char)(ci + 48);
            ex = String.valueOf(ex) + ch;
        }
        return ex;
    }
    
    public static class JComp
    {
        private StringBuilder component;
        public JsonMessageBuilder jsonMessageBuilder;
        
        public JComp() {
            this.component = new StringBuilder();
        }
        
        public JComp addText(final String message) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001al]pl\u001a2\u001a", 8)).append(message).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("!+", 1));
            return this;
        }
        
        public JComp addCommandExecutor(final String command) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u0019Zc`Zb<m\\ek\u00191r\u0017\u0019XZk`fe\u00191\u0019ileVZfddXe[\u0019#\u0017\u0019mXcl\\\u00191\u0019", 9)).append(command).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(" \u001e{*", 2));
            return this;
        }
        
        public JComp addCommandSuggestion(final String command) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u0019Zc`Zb<m\\ek\u00191r\u0017\u0019XZk`fe\u00191\u0019jl^^\\jkVZfddXe[\u0019#\u0017\u0019mXcl\\\u00191\u0019", 9)).append(command).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(" \u001e{*", 2));
            return this;
        }
        
        public JComp addURL(final String link) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u0019Zc`Zb<m\\ek\u00191r\u0017\u0019XZk`fe\u00191\u0019fg\\eVlic\u0019#\u0017\u0019mXcl\\\u00191\u0019", 9)).append(link).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("!\u001f|+", 1));
            return this;
        }
        
        public JComp addChatSuggestion(final String text) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001cchm_lncih\u001c4\u001c", 6)).append(text).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001fz)", 3));
            return this;
        }
        
        public JComp setBold(final boolean bold) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001f!ankc!9\u001f", 1)).append(bold).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(",", 0));
            return this;
        }
        
        public JComp setItalic(final boolean italic) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001e gr_jga 8\u001e", 2)).append(italic).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(",", 0));
            return this;
        }
        
        public JComp setUnderlined(final boolean underlined) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001f!tmcdqkhmdc!9\u001f", 1)).append(underlined).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(",", 0));
            return this;
        }
        
        public JComp setStrikethrough(final boolean strikethrough) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001a\u001cmnlce_nblioab\u001c4\u001a", 6)).append(strikethrough).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(",", 0));
            return this;
        }
        
        public JComp setObfuscated(final boolean obfuscated) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u0017\u0019fY]ljZXk\\[\u00191\u0017", 9)).append(obfuscated).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(",", 0));
            return this;
        }
        
        public JComp setColor(final ChatColor cc) {
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001d^jgjm\u001d5\u001b\u001d", 5)).append(cc.name().toLowerCase()).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("!+", 1));
            return this;
        }
        
        public JComp addHoverText(final String... text) {
            String txt = "";
            for (int i = 0; i < text.length; ++i) {
                if (i + 1 == text.length) {
                    txt = String.valueOf(txt) + text[i];
                }
                else {
                    txt = String.valueOf(txt) + text[i] + OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("Zs..._", 2);
                }
            }
            this.component.append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u0019_fm\\i<m\\ek\u00191r\u0017\u0019XZk`fe\u00191\u0019j_fnVk\\ok\u0019#\u0017\u0019mXcl\\\u00191\u0019", 9)).append(txt).append(OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("\u001f\u001dz)", 3));
            return this;
        }
        
        public void build(final JsonMessageBuilder jsonMessageBuilder) {
            this.component.deleteCharAt(this.component.length() - 1);
            final String finalComp = OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("{", 0) + (Object)this.component + OBFUSCATET_WITH_TAPTSTRINGFUSCATOR("|+", 1);
            this.component = new StringBuilder(finalComp);
            jsonMessageBuilder.jsonComp.add(this);
        }
        
        public static String OBFUSCATET_WITH_TAPTSTRINGFUSCATOR(final String sc, final Integer va) {
            final char[] cj = sc.toCharArray();
            String ex = "";
            char[] array;
            for (int length = (array = cj).length, i = 0; i < length; ++i) {
                int ci = array[i] - '0' + Math.round(sc.getBytes()[0] / 200) * 1;
                ci += va;
                final char ch = (char)(ci + 48);
                ex = String.valueOf(ex) + ch;
            }
            return ex;
        }
    }
}
