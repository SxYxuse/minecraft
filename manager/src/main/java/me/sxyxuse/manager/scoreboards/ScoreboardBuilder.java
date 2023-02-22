package me.sxyxuse.manager.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreboardBuilder {
    private final ScoreboardManager scoreboardManager;
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final List<String> entries;

    public ScoreboardBuilder(String title) {
        this.scoreboardManager = Bukkit.getScoreboardManager();
        this.scoreboard = Objects.requireNonNull(scoreboardManager).getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("lobby", Criteria.DUMMY, "lobby");
        this.entries = new ArrayList<>();

        this.objective.setDisplayName(ChatColor.GREEN + title);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void addScore(String entry) {
        this.entries.add(entry);
    }

    public Scoreboard build() {
        for (int i = 0; i < this.entries.size(); i++) {
            Score score = this.objective.getScore(this.entries.get(i));
            score.setScore(this.entries.size() - i);
        }
        
        return this.scoreboard;
    }
}


