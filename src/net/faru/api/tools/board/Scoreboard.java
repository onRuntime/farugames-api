package net.faru.api.tools.board;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.faru.api.tools.reflection.Reflection;
import net.minecraft.server.v1_9_R2.IScoreboardCriteria;
import net.minecraft.server.v1_9_R2.PacketPlayOutScoreboardScore;

public class Scoreboard
{
    /**
     * Factories
     */
    private static final Class OBJECTIVE_PACKET_CLASS = Reflection.getClass("{nms}.PacketPlayOutScoreboardObjective");
    private static final Field O_NAME = Reflection.getField(OBJECTIVE_PACKET_CLASS, "a");
    private static final Field O_VALUE = Reflection.getField(OBJECTIVE_PACKET_CLASS, "b");
    private static final Field O_TYPE = Reflection.getField(OBJECTIVE_PACKET_CLASS, "c");
    private static final Field O_MODE = Reflection.getField(OBJECTIVE_PACKET_CLASS, "d");

    private static final Class DISPLAY_OBJECTIVE_PACKET_CLASS = Reflection.getClass("{nms}.PacketPlayOutScoreboardDisplayObjective");
    private static final Field DO_SLOT = Reflection.getField(DISPLAY_OBJECTIVE_PACKET_CLASS, "a");
    private static final Field DO_OBJ_NAME = Reflection.getField(DISPLAY_OBJECTIVE_PACKET_CLASS, "b");

    private static final Class SCORE_PACKET_CLASS = Reflection.getClass("{nms}.PacketPlayOutScoreboardScore");
    private static final Field S_SCORE_NAME = Reflection.getField(SCORE_PACKET_CLASS, "a");
    private static final Field S_OBJ_NAME = Reflection.getField(SCORE_PACKET_CLASS, "b");
    private static final Field S_SCORE_INT = Reflection.getField(SCORE_PACKET_CLASS, "c");
    private static final Field S_ACTION = Reflection.getField(SCORE_PACKET_CLASS, "d");

    private final Team[] lines = new Team[15];
    private UUID playerUUID;
    private String objectiveName;
    private ScoreboardSlot slot;
    private boolean created = false;

    /**
     * Create a scoreboard for a given player and using a specific objective name
     *
     * @param playerUUID    the player's uuid viewing the scoreboard sign
     * @param objectiveName the name of the scoreboard sign (displayed at the top of the scoreboard)
     * @param slot          the slot where the scoreboard will be displayed
     */
    public Scoreboard(UUID playerUUID, String objectiveName, ScoreboardSlot slot)
    {
        this.playerUUID = playerUUID;
        this.objectiveName = objectiveName;
        this.slot = slot;
    }

    /**
     * Send the initial creation packets for this scoreboard. Must be called at least once.
     */
    public void create()
    {
        if (created)
            return;
        // Send the scoreboard
        Reflection.sendPacket(getPlayer(), createObjectivePacket(ScoreboardMode.CREATE, objectiveName));
        // Send the slot
        Reflection.sendPacket(getPlayer(), setObjectiveSlot(slot));
        // Send lines
        int i = 0;
        while (i < lines.length)
            sendLine(i++);
        created = true;
    }

    /**
     * Send the packets to update this scoreboard. A scoreboard must be created using {@link Scoreboard#create()} in order
     * to be used
     */
    public void update()
    {
        if (!created)
            return;

        // Send the scoreboard's update
        Reflection.sendPacket(getPlayer(), createObjectivePacket(ScoreboardMode.UPDATE, objectiveName));
    }


    /**
     * Send the packets to remove this scoreboard. A destroyed scoreboard sign must be recreated using {@link Scoreboard#create()} in order
     * to be used again
     */
    public void destroy()
    {
        if (!created)
            return;

        // Remove the scoreboard
        Reflection.sendPacket(getPlayer(), createObjectivePacket(ScoreboardMode.REMOVE, null));

        // Remove teams
        for (Team team : lines)
            if (team != null && team.isCreated())
                team.remove();
        created = false;
    }
    
    public void setObjectiveName(String objectiveName)
    {
    	this.objectiveName = objectiveName;
    	
    	this.update();
    }

    /**
     * Change a scoreboard line and send the packets to the player. Can be called async.
     *
     * @param line  the number of the line (0 <= line < 15)
     * @param value the new value for the scoreboard line
     */
    public void setLine(int line, String value)
    {
        Team team = getOrCreateTeam(line);
        String old = getValue(team);

        if (old != null && created)
            removeLine(team, old);

        setValue(team, value);
        sendLine(line);
    }

    /**
     * Remove a given scoreboard line
     *
     * @param line the line to remove
     */
    public void removeLine(int line)
    {
        Team team = getOrCreateTeam(line);
        String old = getValue(team);
        if (!created)
            return;
        if (old != null)
            removeLine(team, old);

    }

    /**
     * Get the current value for a line
     *
     * @param line the line
     * @return the content of the line
     */
    public String getLine(int line)
    {
        if (line > 14 || line < 0)
            return "";
        return getValue(getOrCreateTeam(line));
    }

    private void sendLine(int line)
    {
        if (line > 14 || line < 0)
            return;
        if (!created)
            return;

        int score = (15 - line);
        Team team = getOrCreateTeam(line);
        String lineValue = getValue(team);

        Reflection.sendPacket(getPlayer(), sendScore(lineValue, score));
    }

    private void removeLine(Team team, String scoreName)
    {
        try
        {
            team.removePlayer(team.getPlayers().iterator().next());
            Reflection.sendPacket(getPlayer(), SCORE_PACKET_CLASS.getDeclaredConstructor(String.class).newInstance(scoreName));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Team getOrCreateTeam(int line)
    {
        if (lines[line] == null)
        {
            lines[line] = new Team("__fakeScore" + line, Collections.singletonList(getPlayer()));
            lines[line].create();
        }
        return lines[line];
    }

    private Player getPlayer()
    {
        final Player player = Bukkit.getPlayer(playerUUID);
        if (player == null)
            throw new NullPointerException("The player of uuid " + playerUUID.toString() + " isn't online !");
        return player;
    }

    private void setValue(Team team, String value)
    {
        final int length = value.length();
        if (length <= 16)
        {
            team.setPrefix("");
            team.addPlayer(value);
            team.setSuffix("");
        } else if (value.length() <= 32)
        {
            team.setPrefix(value.substring(0, 16));
            team.addPlayer(value.substring(16));
            team.setSuffix("");
        } else if (value.length() <= 48)
        {
            team.setPrefix(value.substring(0, 16));
            team.addPlayer(value.substring(16, 32));
            team.setSuffix(value.substring(32));
        } else
        {
            throw new IllegalArgumentException("Too long value ! Max 48 characters, value was " + length + " !");
        }
    }

    private String getValue(Team team)
    {
        if (!team.getPlayers().iterator().hasNext())
            return null;
        return team.getPrefix() + team.getPlayers().iterator().next() + team.getSuffix();
    }

    private Object createObjectivePacket(ScoreboardMode mode, String displayName)
    {
        try
        {
            Object packet = OBJECTIVE_PACKET_CLASS.newInstance();
            // Nom de l'objectif
            Reflection.setFieldValue(packet, O_NAME, getPlayer().getName());

            // Mode
            // 0 : créer
            // 1 : Supprimer
            // 2 : Mettre à jour
            Reflection.setFieldValue(packet, O_MODE, mode.getMode());

            if (mode != ScoreboardMode.REMOVE)
            {
                Reflection.setFieldValue(packet, O_VALUE, displayName);
                // TODO: 24/02/17 Use Reflection
                Reflection.setFieldValue(packet, O_TYPE, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
            }

            return packet;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    private Object setObjectiveSlot(ScoreboardSlot slot)
    {
        try
        {
            Object packet = DISPLAY_OBJECTIVE_PACKET_CLASS.newInstance();

            // Slot
            Reflection.setFieldValue(packet, DO_SLOT, slot.getMode());
            Reflection.setFieldValue(packet, DO_OBJ_NAME, getPlayer().getName());
            return packet;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private Object sendScore(String line, int score)
    {
        try
        {
            Object packet = SCORE_PACKET_CLASS.getDeclaredConstructor(String.class).newInstance(line);
            Reflection.setFieldValue(packet, S_OBJ_NAME, getPlayer().getName());
            Reflection.setFieldValue(packet, S_SCORE_INT, score);
            // TODO: 24/02/17 Use Reflection
            Reflection.setFieldValue(packet, S_ACTION, PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE);
            return packet;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * The mode of the scoreboard (when the client receive the packet, what action it have to do)
     */
    public enum ScoreboardMode
    {

        CREATE(0),
        REMOVE(1),
        UPDATE(2);

        private final int mode;

        ScoreboardMode(final int mode)
        {
            this.mode = mode;
        }

        public final int getMode()
        {
            return this.mode;
        }

    }

    /**
     * The slot where the scoreboard will be displayed
     */
    public enum ScoreboardSlot
    {

        LIST(0),
        SIDEBAR(1),
        BELOW_NAME(2);

        private final int mode;

        ScoreboardSlot(final int mode)
        {
            this.mode = mode;
        }

        public final int getMode()
        {
            return this.mode;
        }

    }

    /**
     * The mode of the score
     */
    public enum ScoreMode
    {

        CREATE_OR_UPDATE(0),
        REMOVE(1);

        private final int mode;

        ScoreMode(final int mode)
        {
            this.mode = mode;
        }

        public final int getMode()
        {
            return this.mode;
        }

    }
}
