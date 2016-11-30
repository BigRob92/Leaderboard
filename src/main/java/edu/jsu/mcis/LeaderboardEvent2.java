package edu.jsu.mcis;

public class LeaderboardEvent2 {
	private String nameSelected;
	private String nameId;
    public LeaderboardEvent2(String nameId, String name) {

		nameId = nameId;
		nameSelected = name;
    }
	
	public String getSelectedName() {return nameSelected; }
	
	public String getNameId(){ return nameId; }
	
}