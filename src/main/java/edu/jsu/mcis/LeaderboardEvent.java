package edu.jsu.mcis;

public class LeaderboardEvent {
    private String selectedId;
	private float selectedGrade;
	//private String nameSelected;
	//private String nameId;
	private String emailSelected;
    public LeaderboardEvent(String id, float grade) {
        selectedId = id;
		selectedGrade = grade;
		//nameId = nameId;
		//nameSelected = nameSelected;
    }
    public String getSelectedId() { return selectedId; }
	
	public float getSelectedGrade() {return selectedGrade; }
	
}