package edu.jsu.mcis;



public class ShapeEvent {

    private boolean rectangleOneSelected;

	private boolean rectangleTwoSelected;

	

    public ShapeEvent() {

        this(false, false);

    }

    public ShapeEvent(boolean rectangleOneSelectedSelected, boolean rectangleTwoSelected) {

		this.rectangleOneSelected = rectangleOneSelected;

		this.rectangleTwoSelected = rectangleTwoSelected;

    }

    public boolean rectOneIsSelected(){

		return rectangleOneSelected;

	}

	

	public boolean rectTwoIsSelected(){

		return rectangleTwoSelected;

	}

}