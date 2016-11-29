package edu.jsu.mcis;

public class LeaderboardEvent {
    private String selected;
	private float selected2;
    public LeaderboardEvent(String id, float grade) {
        selected = id;
		selected2 = grade;
    }
    public String getSelected() { return selected; }
	
	public float getSelectedGrade(){return selected2; }
}