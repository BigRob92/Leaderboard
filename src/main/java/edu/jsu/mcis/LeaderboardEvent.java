package edu.jsu.mcis;

public class LeaderboardEvent {
    private String selected;
    public LeaderboardEvent(String id) {
        selected = id;
    }
    public String getSelected() { return selected; }
}