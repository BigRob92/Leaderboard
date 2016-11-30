package edu.jsu.mcis;

public class LeaderboardEvent {
    private String selected;
	private float selected2;
	private String nameSelected;
	private String nameId;
	private String emailSelected;
    public LeaderboardEvent(String id, float grade, String nameId, String name) {
        selected = id;
		selected2 = grade;
		nameId = nameId;
		nameSelected = name;
    }
    public String getSelected() { return selected; }
	
	public float getSelectedGrade(){return selected2; }
	
	public String getSelectedName() {return nameSelected; }
	
	public String getNameId(){ return nameId; }
	
}