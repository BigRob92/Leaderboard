package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Leaderboard extends JPanel implements MouseListener {
    private java.util.List<LeaderboardObserver> observers;
    
	private class DataPoint {
		public String id;
		public float grade;
		public DataPoint(String id, float grade) { this.id = id; this.grade = grade; }
		
		// If I make this comparable, then I can sort a collection of these things automatically.
		
	}
	
	public class studentData {
		public String id;
		public String name;
		public studentData(String id, String name){ this.id = id; this.name = name; }
	}
    
    private final Color SELECTED_COLOR = Color.GREEN;
    private final Color DEFAULT_COLOR = Color.BLUE;
    private String selected;
	private float selected2;
	private String nameId;
	public String nameSelected;
	private String emailSelected;
	private java.util.List<DataPoint> data;
	public java.util.List<studentData> nameData;

    public Leaderboard() {
        observers = new ArrayList<>();
        selected = "";
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
    }

	public void setData(Map<String, Float> data) {
		this.data = new ArrayList<>();
		for(String id : data.keySet()) {
			DataPoint p = new DataPoint(id, data.get(id));
			this.data.add(p);
		}		
	}
	
	public void setStudentName(Map<String,String> nameData){
		this.nameData = new ArrayList<>();
		for(String id : nameData.keySet()) {
			studentData s = new studentData (id, nameData.get(id));
			this.nameData.add(s);
		}
	}
    
    public void addLeaderboardObserver(LeaderboardObserver observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }
    public void removeLeaderboardObserver(LeaderboardObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        LeaderboardEvent event = new LeaderboardEvent(selected, selected2, nameId, nameSelected);
        for(LeaderboardObserver obs : observers) {
            obs.leaderboardChanged(event);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        Shape[] shape = getShapes();
        g2d.setColor(Color.black);
        
		for(int i = 0; i < shape.length;i++){
			g2d.draw(shape[i]);
			if(data.get(i).id.equals(selected)) {
				g2d.setColor(SELECTED_COLOR);
				g2d.fill(shape[i]);
			}
			else {
				g2d.setColor(DEFAULT_COLOR);
				g2d.fill(shape[i]);            
			}
		}
    }

    public void mouseClicked(MouseEvent event) {
        Shape [] shape = getShapes();
		for(int i = 0; i < shape.length; i++) {
			if(shape[i].contains(event.getX(), event.getY())) {
				selected = data.get(i).id;
				selected2 = data.get(i).grade;
				//nameId = nameData.get(i).id;
				//nameSelected = nameData.get(i).name;
				notifyObservers();
				repaint();
			}
		}		
	}

			
		
    
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}
    
    public Shape[] getShapes() {
		// Sort the grades in data in order to make this accurate.
		int length = data.size();
		Shape[] shapes = new Shape[length];
		for(int i = 0; i < length;i++){
			shapes[i] = new Rectangle(0, 20 * i, Math.round(data.get(i).grade), 15);
		}
        return shapes;
    }
    public String getSelected() { return data.get(0).id; }
	
	public float getSelected2() { return data.get(0).grade; }
	
	public String getNameSelected() {return nameData.get(0).name; }
}
