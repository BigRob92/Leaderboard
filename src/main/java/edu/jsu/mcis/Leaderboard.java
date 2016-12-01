package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Leaderboard extends JPanel implements MouseListener {
    private java.util.List<LeaderboardObserver> observers;
    
	private class DataPoint implements Comparable<DataPoint> {
		public String id;
		public float grade;
		public String name;
		
		public DataPoint(String id, float grade) { this.grade = grade; this.id = id; }
		
		public int compareTo(DataPoint other) {
			if(grade < other.grade) return 1;
			else if(grade > other.grade) return -1;
			else return 0;
		}

		// If I make this comparable, then I can sort a collection of these things automatically.
		
	}
	
    private final Color SELECTED_COLOR = Color.GREEN;
    private final Color DEFAULT_COLOR = Color.BLUE;
    private String selectedId;
	private float selectedGrade;
	private java.util.List<DataPoint> data;

    public Leaderboard() {
        observers = new ArrayList<>();
        selectedId = "";
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
    }

	public void setData(Map<String, Float> data) {
		this.data = new ArrayList<>();
		for(String id : data.keySet()) {
			DataPoint p = new DataPoint(id, data.get(id));
			this.data.add(p);
		}
		Collections.sort(this.data);
	}
	
    
    public void addLeaderboardObserver(LeaderboardObserver observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }
    public void removeLeaderboardObserver(LeaderboardObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        LeaderboardEvent event = new LeaderboardEvent(selectedId, selectedGrade);
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
			if(data.get(i).id.equals(selectedId)) {
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
        Shape[] shape = getShapes();
		for(int i = 0; i < shape.length; i++) {
			if(shape[i].contains(event.getX(), event.getY())) {
				selectedId = data.get(i).id;
				selectedGrade = data.get(i).grade;
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
		int length = data.size();
		Shape[] shapes = new Shape[length];
		for(int i = 0; i < length;i++){
			shapes[i] = new Rectangle(0, 20 * i, Math.round(data.get(i).grade), 15);
		}
        return shapes;
    }

}
