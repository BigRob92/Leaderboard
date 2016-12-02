package edu.jsu.mcis;

public class LeaderboardEvent {
    private String selectedId;
	private float selectedGrade;

    public LeaderboardEvent(String id, float grade) {
        selectedId = id;
		selectedGrade = grade;
    }

    public String getSelectedId() { return selectedId; }
	
	public float getSelectedGrade() {return selectedGrade; }
	
}
