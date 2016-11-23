package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Leaderboard extends JPanel implements MouseListener {
    private java.util.List<ShapeObserver> observers;
    
    
    private final Color SELECTED_COLOR = Color.GREEN;
    private final Color DEFAULT_COLOR = Color.BLUE;
    private boolean selected;
    private Point[] vertex;
	private GradeBook gb;

    
    public Leaderboard() {
        observers = new ArrayList<>();
        
        selected = false;
        vertex = new Point[4];
        for(int i = 0; i < vertex.length; i++) { vertex[i] = new Point(); }
        Dimension dim = getPreferredSize();
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
		calculateVertices(dim.width,dim.height);
		gb = new GradeBook("courses/99000.csv", 1);
    }

    
    public void addShapeObserver(ShapeObserver observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }
    public void removeShapeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        ShapeEvent event = new ShapeEvent(selected);
        for(ShapeObserver obs : observers) {
            obs.shapeChanged(event);
        }
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    private void calculateVertices(int width, int height) {
        // Square size should be half of the smallest dimension (width or height).
        int side = Math.min(width, height) / 2;
        Point[] sign = {new Point(-1, -1), new Point(1, -1), new Point(1, 1), new Point(-1, 1)};
        for(int i = 0; i < vertex.length; i++) {
            vertex[i].setLocation(width/2 + sign[i].x * side/2, 
                                  height/2 + sign[i].y * side/2);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        calculateVertices(50, 50);
        Shape [] shape = getShapes();
        g2d.setColor(Color.black);
        
		for(int i = 0; i < shape.length;i++){
			g2d.draw(shape[i]);
			if(selected) {
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
		for(int i = 0; i < shape.length;i++){
			if(shape[i].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
			}
			repaint(shape[i].getBounds());
		}
    }
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}
    
    public Shape [] getShapes() {
        int[] x = new int[vertex.length];
        int[] y = new int[vertex.length];
		int length = Integer.parseInt(gb.getEnrollment());
		Shape [] shapes = new Shape [length];
        for(int i = 0; i < vertex.length; i++) {
            x[i] = vertex[i].x;
            y[i] = vertex[i].y;
        }
		for(int i = 0; i < length;i++){
			shapes[i] = new Rectangle(0, 40 * i, 700/(2+i),25);
		}
        return shapes;
    }
    public boolean isSelected() { return selected; }
}