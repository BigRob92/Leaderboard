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
		if (courseSELECTED == null || courseSELECTED.equals("99000")){
			if(shape[0].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111318");
				idLabel.setText("111318");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("925.0");
				
			}
			else if(shape[1].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[1].getBounds());
				p = new CSVParser("111310");
				idLabel.setText("111310");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("852");
			}
			else if(shape[2].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[2].getBounds());
				p = new CSVParser("111383");
				idLabel.setText("111383");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("741");
			}
			else if(shape[3].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[3].getBounds());
				p = new CSVParser("111262");
				idLabel.setText("111262");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("675");
			}
			else if(shape[4].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[4].getBounds());
				p = new CSVParser("111208");
				idLabel.setText("111208");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("614");
			}
			else if(shape[5].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[5].getBounds());
				p = new CSVParser("111115");
				idLabel.setText("111115");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("561");
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
				p = new CSVParser("111406");
				idLabel.setText("111406");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("524");
			}
			else if(shape[8].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[8].getBounds());
				p = new CSVParser("111190");
				idLabel.setText("111190");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("511");
			}
			else if(shape[9].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[9].getBounds());
				p = new CSVParser("111211");
				idLabel.setText("111211");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("488");
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
		else if (courseSELECTED.equals("99001")){
			if(shape[0].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111143");
				idLabel.setText("111143");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("886.0");
			}
			else if (shape[1].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111291");
				idLabel.setText("111291");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("823.0");
				
			}
			else if (shape[2].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[2].getBounds());
				p = new CSVParser("111254");
				idLabel.setText("111254");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("820.0");
				
			}
			else if (shape[3].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[3].getBounds());
				p = new CSVParser("111236");
				idLabel.setText("111236");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("669.0");
				
			}
			else if (shape[4].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[4].getBounds());
				p = new CSVParser("111157");
				idLabel.setText("111157");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("593.0");
				
			}
			else if (shape[5].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[5].getBounds());
				p = new CSVParser("111208");
				idLabel.setText("111208");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("557.0");
				
			}
			else if (shape[6].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[6].getBounds());
				p = new CSVParser("111293");
				idLabel.setText("111293");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("548.0");
				
			}
			else if (shape[7].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[7].getBounds());
				p = new CSVParser("111326");
				idLabel.setText("111326");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("499.0");
				
			}
			else if (shape[8].contains(event.getX(), event.getY())){
				selected = !selected;
				notifyObservers();
				repaint(shape[8].getBounds());
				p = new CSVParser("111148");
				idLabel.setText("111148");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("494.0");
				
			}
		}
		else if (courseSELECTED.equals("99002")){
			if(shape[0].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111142");
				idLabel.setText("111142");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("873.0");
				
			}
			else if(shape[1].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[1].getBounds());
				p = new CSVParser("111160");
				idLabel.setText("111160");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("872");
			}
			else if(shape[2].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[2].getBounds());
				p = new CSVParser("111310");
				idLabel.setText("111310");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("820");
			}
			else if(shape[3].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[3].getBounds());
				p = new CSVParser("111320");
				idLabel.setText("111320");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("808");
			}
			else if(shape[4].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[4].getBounds());
				p = new CSVParser("111261");
				idLabel.setText("111261");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("807");
			}
			else if(shape[5].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[5].getBounds());
				p = new CSVParser("111128");
				idLabel.setText("111128");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("787");
			}
			else if(shape[6].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[6].getBounds());
				p = new CSVParser("111202");
				idLabel.setText("111202");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("731.0");
			}
			else if(shape[7].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[7].getBounds());
				p = new CSVParser("111260");
				idLabel.setText("111260");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("683");
			}
			else if(shape[8].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[8].getBounds());
				p = new CSVParser("111215");
				idLabel.setText("111215");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("640");
			}
			else if(shape[9].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[9].getBounds());
				p = new CSVParser("111299");
				idLabel.setText("111299");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("553");
			}
			else if(shape[10].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[10].getBounds());
				p = new CSVParser("111227");
				idLabel.setText("111227");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("541");
			}
			else if(shape[11].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[11].getBounds());
				p = new CSVParser("111245");
				idLabel.setText("111245");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("521");
			}
			else if(shape[12].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[12].getBounds());
				p = new CSVParser("111391");
				idLabel.setText("111391");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("511");
			}
			
		}
		else if (courseSELECTED.equals("99003")){
			if(shape[0].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111167");
				idLabel.setText("111167");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("963.0");
				
			}
			else if(shape[1].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[1].getBounds());
				p = new CSVParser("111304");
				idLabel.setText("111304");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("892");
			}
			else if(shape[2].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[2].getBounds());
				p = new CSVParser("111409");
				idLabel.setText("111409");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("859");
			}
			else if(shape[3].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[3].getBounds());
				p = new CSVParser("111115");
				idLabel.setText("111115");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("856");
			}
			else if(shape[4].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[4].getBounds());
				p = new CSVParser("111316");
				idLabel.setText("111316");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("838");
			}
			else if(shape[5].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[5].getBounds());
				p = new CSVParser("111307");
				idLabel.setText("111307");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("837");
			}
			else if(shape[6].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[6].getBounds());
				p = new CSVParser("111228");
				idLabel.setText("111228");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("827.0");
			}
			else if(shape[7].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[7].getBounds());
				p = new CSVParser("111213");
				idLabel.setText("111213");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("825");
			}
			else if(shape[8].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[8].getBounds());
				p = new CSVParser("111192");
				idLabel.setText("111192");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("806");
			}
			else if(shape[9].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[9].getBounds());
				p = new CSVParser("111353");
				idLabel.setText("111353");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("771");
			}
			else if(shape[10].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[10].getBounds());
				p = new CSVParser("111185");
				idLabel.setText("111185");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("768");
			}
			else if(shape[11].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[11].getBounds());
				p = new CSVParser("111359");
				idLabel.setText("111359");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("763");
			}
			else if(shape[12].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[12].getBounds());
				p = new CSVParser("111198");
				idLabel.setText("111198");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("674");
			}
			else if(shape[13].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[13].getBounds());
				p = new CSVParser("111254");
				idLabel.setText("111254");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("620");
			}
			else if(shape[14].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[14].getBounds());
				p = new CSVParser("111364");
				idLabel.setText("111364");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("566");
			}
			else if(shape[15].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[15].getBounds());
				p = new CSVParser("111140");
				idLabel.setText("111140");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("532");
			}
			else if(shape[16].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[16].getBounds());
				p = new CSVParser("111133");
				idLabel.setText("111133");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("467");
			}
			
		}
		else if (courseSELECTED.equals("99004")){
			if(shape[0].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111315");
				idLabel.setText("111315");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("928.0");
				
			}
			else if(shape[1].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[1].getBounds());
				p = new CSVParser("111241");
				idLabel.setText("111241");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("881");
			}
			else if(shape[2].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[2].getBounds());
				p = new CSVParser("111200");
				idLabel.setText("111200");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("878");
			}
			else if(shape[3].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[3].getBounds());
				p = new CSVParser("111257");
				idLabel.setText("111257");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("868");
			}
			else if(shape[4].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[4].getBounds());
				p = new CSVParser("111153");
				idLabel.setText("111153");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("826");
			}
			else if(shape[5].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[5].getBounds());
				p = new CSVParser("111246");
				idLabel.setText("111246");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("807");
			}
			else if(shape[6].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[6].getBounds());
				p = new CSVParser("111289");
				idLabel.setText("111289");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("794.0");
			}
			else if(shape[7].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[7].getBounds());
				p = new CSVParser("111242");
				idLabel.setText("111242");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("749");
			}
			else if(shape[8].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[8].getBounds());
				p = new CSVParser("111284");
				idLabel.setText("111284");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("727");
			}
			else if(shape[9].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[9].getBounds());
				p = new CSVParser("111359");
				idLabel.setText("111359");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("698");
			}
			else if(shape[10].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[10].getBounds());
				p = new CSVParser("111154");
				idLabel.setText("111154");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("673");
			}
			else if(shape[11].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[11].getBounds());
				p = new CSVParser("111364");
				idLabel.setText("111364");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("662");
			}
			else if(shape[12].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[12].getBounds());
				p = new CSVParser("111363");
				idLabel.setText("111363");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("651");
			}
			else if(shape[13].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[13].getBounds());
				p = new CSVParser("111326");
				idLabel.setText("111326");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("642");
			}
			else if(shape[14].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[14].getBounds());
				p = new CSVParser("111116");
				idLabel.setText("111116");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("609");
			}
			else if(shape[15].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[15].getBounds());
				p = new CSVParser("111381");
				idLabel.setText("111381");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("570");
			}
			else if(shape[16].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[16].getBounds());
				p = new CSVParser("111328");
				idLabel.setText("111328");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("514");
			}
			else if(shape[17].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[17].getBounds());
				p = new CSVParser("111179");
				idLabel.setText("111179");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("504");
			}
			else if(shape[18].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[18].getBounds());
				p = new CSVParser("111389");
				idLabel.setText("111389");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("470");
			}
			else if(shape[19].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[19].getBounds());
				p = new CSVParser("111402");
				idLabel.setText("111402");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("434");
			}
			
		}
		else if (courseSELECTED.equals("99005")){
			if(shape[0].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111240");
				idLabel.setText("111240");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("947.0");
				
			}
			else if(shape[1].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[1].getBounds());
				p = new CSVParser("111327");
				idLabel.setText("111327");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("851");
			}
			else if(shape[2].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[2].getBounds());
				p = new CSVParser("111297");
				idLabel.setText("111297");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("800");
			}
			else if(shape[3].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[3].getBounds());
				p = new CSVParser("111274");
				idLabel.setText("111274");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("797");
			}
			else if(shape[4].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[4].getBounds());
				p = new CSVParser("111395");
				idLabel.setText("111395");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("777");
			}
			else if(shape[5].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[5].getBounds());
				p = new CSVParser("111299");
				idLabel.setText("111299");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("773");
			}
			else if(shape[6].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[6].getBounds());
				p = new CSVParser("111318");
				idLabel.setText("111318");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("764.0");
			}
			else if(shape[7].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[7].getBounds());
				p = new CSVParser("111125");
				idLabel.setText("111125");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("725");
			}
			else if(shape[8].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[8].getBounds());
				p = new CSVParser("111221");
				idLabel.setText("111221");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("649");
			}
			else if(shape[9].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[9].getBounds());
				p = new CSVParser("111287");
				idLabel.setText("111287");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("602");
			}
			else if(shape[10].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[10].getBounds());
				p = new CSVParser("111319");
				idLabel.setText("111319");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("546");
			}
			else if(shape[11].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[11].getBounds());
				p = new CSVParser("111409");
				idLabel.setText("111409");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("530");
			}
			else if(shape[12].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[12].getBounds());
				p = new CSVParser("111244");
				idLabel.setText("111244");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("508");
			}
			else if(shape[13].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[13].getBounds());
				p = new CSVParser("111250");
				idLabel.setText("111250");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("483");
			}
			else if(shape[14].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[14].getBounds());
				p = new CSVParser("111353");
				idLabel.setText("111353");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("457");
			}
		}
		else if (courseSELECTED.equals("99006")){
			if(shape[0].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[0].getBounds());
				p = new CSVParser("111212");
				idLabel.setText("111212");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("909.0");
				
			}
			else if(shape[1].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[1].getBounds());
				p = new CSVParser("111169");
				idLabel.setText("111169");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("898");
			}
			else if(shape[2].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[2].getBounds());
				p = new CSVParser("111133");
				idLabel.setText("111133");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("889");
			}
			else if(shape[3].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[3].getBounds());
				p = new CSVParser("111341");
				idLabel.setText("111341");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("883");
			}
			else if(shape[4].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[4].getBounds());
				p = new CSVParser("111285");
				idLabel.setText("111285");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("877");
			}
			else if(shape[5].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[5].getBounds());
				p = new CSVParser("111246");
				idLabel.setText("111246");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("807");
			}
			else if(shape[6].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[6].getBounds());
				p = new CSVParser("111289");
				idLabel.setText("111289");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("794.0");
			}
			else if(shape[7].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[7].getBounds());
				p = new CSVParser("111384");
				idLabel.setText("111384");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("850");
			}
			else if(shape[8].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[8].getBounds());
				p = new CSVParser("111169");
				idLabel.setText("111169");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("838");
			}
			else if(shape[9].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[9].getBounds());
				p = new CSVParser("111388");
				idLabel.setText("111388");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("800");
			}
			else if(shape[10].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[10].getBounds());
				p = new CSVParser("111115");
				idLabel.setText("111115");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("714");
			}
			else if(shape[11].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[11].getBounds());
				p = new CSVParser("111195");
				idLabel.setText("111195");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("698");
			}
			else if(shape[12].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[12].getBounds());
				p = new CSVParser("111214");
				idLabel.setText("111214");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("692");
			}
			else if(shape[13].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[13].getBounds());
				p = new CSVParser("111252");
				idLabel.setText("111252");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("677");
			}
			else if(shape[14].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[14].getBounds());
				p = new CSVParser("111226");
				idLabel.setText("111226");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("648");
			}
			else if(shape[15].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[15].getBounds());
				p = new CSVParser("111234");
				idLabel.setText("111234");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("610");
			}
			else if(shape[16].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[16].getBounds());
				p = new CSVParser("111247");
				idLabel.setText("111247");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("583");
			}
			else if(shape[17].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[17].getBounds());
				p = new CSVParser("111248");
				idLabel.setText("111248");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("556");
			}
			else if(shape[18].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[18].getBounds());
				p = new CSVParser("111213");
				idLabel.setText("111213");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("556");
			}
			else if(shape[19].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[19].getBounds());
				p = new CSVParser("111160");
				idLabel.setText("111160");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("471");
			}
			else if(shape[20].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[20].getBounds());
				p = new CSVParser("111349");
				idLabel.setText("111349");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("432");
			}
			else if(shape[21].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[21].getBounds());
				p = new CSVParser("111310");
				idLabel.setText("111310");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("429");
			}
			else if(shape[22].contains(event.getX(), event.getY())) {
				selected = !selected;
				notifyObservers();
				repaint(shape[22].getBounds());
				p = new CSVParser("111140");
				idLabel.setText("111140");
				nameLabel.setText(p.getStudentName());
				emailLabel.setText(p.getStudentEmail());
				scoreLabel.setText("393");
			}
			
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
