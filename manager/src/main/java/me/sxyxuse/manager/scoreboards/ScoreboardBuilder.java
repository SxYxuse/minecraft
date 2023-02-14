package me.sxyxuse.manager.scoreboards;

import me.sxyxuse.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreboardBuilder {
    private final ScoreboardManager scoreboardManager;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final List<String> entries;
    private Score player_online;

    public ScoreboardBuilder(String title) {
        this.scoreboardManager = Bukkit.getScoreboardManager();
        this.scoreboard = Objects.requireNonNull(scoreboardManager).getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("lobby", Criteria.DUMMY, "lobby");
        this.entries = new ArrayList<>();

        this.objective.setDisplayName(ChatColor.GOLD + "  » " + title + " «  ");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public static void update(Player player) {
        Scoreboard scoreboard1 = player.getScoreboard();
        Team x = scoreboard1.getTeam("connected");
        Objects.requireNonNull(x).setPrefix(" §8  En ligne(s) : " + Manager.getNumberOfConnected() + "/" + Bukkit.getServer().getMaxPlayers());
    }

    public void addScore(String entry) {
        this.entries.add(entry);
    }

    public Scoreboard build() {
        for (int i = 0; i < this.entries.size(); i++) {
            Score score = this.objective.getScore(this.entries.get(i));
            score.setScore((this.entries.size() - i) + 1);
        }

        this.objective.getScore("   ").setScore(this.entries.size() - (this.entries.size() - 1));
        Team connected = this.scoreboard.registerNewTeam("connected");
        connected.addEntry("   ");
        connected.setPrefix(" §8  En ligne(s) : " + Manager.getNumberOfConnected() + "/" + Bukkit.getServer().getMaxPlayers());

        return this.scoreboard;
    }
}


