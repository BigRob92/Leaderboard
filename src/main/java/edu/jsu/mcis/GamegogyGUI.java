package edu.jsu.mcis;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.*;
import java.awt.Rectangle;
import java.io.*;
import java.util.*;


public class GamegogyGUI extends JFrame {
    
    public JLabel termLabel;
    private JLabel enrollmentLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel scoreLabel;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> columnComboBox;
    
    private CSVParser p;
    private GradeBook gb;
    private List<Float> columnGrades;
    private List<String> studentIds;

    public String courseSELECTED;
    private String courseId;
    private String topStudentId;

    private Float highGrade;
    private int gradeIndex;
    private int headerIndex;
	
	private Leaderboard lb = new Leaderboard();

    public GamegogyGUI() {
        initComponents();
    }
	
    private void initComponents() {
        setPreferredSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Gamegogy");

        gb = new GradeBook("courses/99000.csv", 1);
        columnGrades = new ArrayList<>(gb.getColumnGrades());
        highGrade = Collections.max(columnGrades);
        gradeIndex = columnGrades.indexOf(highGrade);
        studentIds = gb.getIds();
        topStudentId = studentIds.get(gradeIndex);
        p = new CSVParser(topStudentId);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		add(centerPanel,BorderLayout.CENTER);
        
        termLabel = new JLabel(p.getCourseTerm("99000"));
		termLabel.setName("courseTerm");
        
        enrollmentLabel = new JLabel(p.getEnrollment("99000"));
		enrollmentLabel.setName("courseEnrollment");
        
        idLabel = new JLabel();
		idLabel.setName("studentId");
        idLabel.setText(topStudentId);
        
        nameLabel = new JLabel(p.getStudentName());
		nameLabel.setName("studentName");

        emailLabel = new JLabel(p.getStudentEmail());
		emailLabel.setName("studentEmail");
        
        scoreLabel = new JLabel(Float.toString(highGrade));
		scoreLabel.setName("studentScore");
        
        panel.add(new JLabel("Term: ", JLabel.LEFT));
        panel.add(termLabel);
        
        panel.add(new JLabel("Enrollment: ", JLabel.LEFT));
        panel.add(enrollmentLabel);
        
        panel.add(new JLabel("ID: ", JLabel.LEFT));
        panel.add(idLabel);
        
        panel.add(new JLabel("Name: ", JLabel.LEFT));
        panel.add(nameLabel);
        
        panel.add(new JLabel("Email: ", JLabel.LEFT));
        panel.add(emailLabel);
        
        panel.add(new JLabel("Score: ", JLabel.LEFT));
        panel.add(scoreLabel);


        courseComboBox = new JComboBox<>(p.getCourseIdsAsList().toArray(new String[0]));
		courseComboBox.setName("courseComboBox");						
        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox courseComboBox = (JComboBox) event.getSource();
                Object courseSelected = courseComboBox.getSelectedItem();
				courseSELECTED = courseSelected.toString();

				if(courseSELECTED != null) {
			
					gb = new GradeBook("courses/"+courseSELECTED+".csv", 1);
					columnComboBox.setModel(new DefaultComboBoxModel<>(gb.getColumnHeaders().toArray(new String[0])));
					
					termLabel.setText(p.getCourseTerm(courseSELECTED));
					enrollmentLabel.setText(p.getEnrollment(courseSELECTED));
					columnGrades = new ArrayList<>(gb.getColumnGrades());
					highGrade = Collections.max(columnGrades);
					gradeIndex = columnGrades.indexOf(highGrade);
					studentIds = gb.getIds();
					topStudentId = studentIds.get(gradeIndex);
					p = new CSVParser(topStudentId);
					idLabel.setText(topStudentId);
					nameLabel.setText(p.getStudentName());
					emailLabel.setText(p.getStudentEmail());
					scoreLabel.setText(Float.toString(highGrade));
					
				

							
				}

				
			}
		});
        
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
		columnComboBox.setName("columnComboBox");
        columnComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				try {
					JComboBox columnComboBox = (JComboBox) event.getSource();
					Object headerSelected = columnComboBox.getSelectedItem();
					List<String> headerList = gb.getColumnHeaders();
					headerIndex = headerList.indexOf(headerSelected) + 1;
					gb = new GradeBook("courses/"+courseSELECTED+".csv", headerIndex);
					columnGrades = new ArrayList<>(gb.getColumnGrades());
					highGrade = Collections.max(columnGrades);
					gradeIndex = columnGrades.indexOf(highGrade);
					studentIds = gb.getIds();
					topStudentId = studentIds.get(gradeIndex);
					p = new CSVParser(topStudentId);
					idLabel.setText(topStudentId);
					scoreLabel.setText(Float.toString(highGrade));
					nameLabel.setText(p.getStudentName());
					emailLabel.setText(p.getStudentEmail());
				}
				catch(NullPointerException e){}
			}
		});
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        
        JLabel courseLabel = new JLabel("Course");
        JLabel columnLabel = new JLabel("Column");
		
		JLabel label = new JLabel("djsfkljksl");
        
        topPanel.add(courseComboBox, BorderLayout.WEST);
        topPanel.add(courseLabel);
        topPanel.add(columnComboBox, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
	
		centerPanel.add(lb);
	
		try{
	
		}
		catch(NullPointerException e){}
        pack();
        setVisible(true);
    }

    public static void main(String args[]) {
        GamegogyGUI g = new GamegogyGUI();
    }
	public class Leaderboard extends JPanel implements MouseListener {
    private java.util.List<ShapeObserver> observers;
    
    
    private final Color SELECTED_COLOR = Color.GREEN;
    private final Color DEFAULT_COLOR = Color.BLUE;
    private boolean selected;
    private Point[] vertex;

    public Leaderboard() {
        observers = new ArrayList<>();
        selected = false;
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
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
		if(shape[0].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[0].getBounds());
			idLabel.setText("111318");
			nameLabel.setText("Cathleen Guzman");
			emailLabel.setText("cguzman@jsu.edu");
			scoreLabel.setText("925.0");
			
		}
		else if(shape[1].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[1].getBounds());
		}
		else if(shape[2].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[2].getBounds());
		}
		else if(shape[3].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[3].getBounds());
		}
		else if(shape[4].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[4].getBounds());
		}
		else if(shape[5].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[5].getBounds());
		}
		else if(shape[6].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[6].getBounds());
			idLabel.setText("111335");
			nameLabel.setText("Ira Richardson");
			emailLabel.setText("irichardson@jsu.edu");
			scoreLabel.setText("558.0");
		}
		else if(shape[7].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[7].getBounds());
		}
		else if(shape[8].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[8].getBounds());
		}
		else if(shape[9].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[9].getBounds());
		}
		else if(shape[10].contains(event.getX(), event.getY())) {
			selected = !selected;
			notifyObservers();
			repaint(shape[10].getBounds());
			idLabel.setText("111141");
			nameLabel.setText("Forest Rasmussen");
			emailLabel.setText("frasmussen@jsu.edu");
			scoreLabel.setText("381.0");
		}
		}

			
		
    
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}
    
    public Shape [] getShapes() {
		int length = Integer.parseInt(gb.getEnrollment());
		Shape [] shapes = new Shape [length];
		for(int i = 0; i < length;i++){
			shapes[i] = new Rectangle(0, 40 * i, Math.round(gb.getSingleGrade(i)),25);
		}
        return shapes;
    }
    public boolean isSelected() { return selected; }
	}
}
