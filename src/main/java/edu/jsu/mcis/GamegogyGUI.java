package edu.jsu.mcis;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class GamegogyGUI extends JFrame {
    private JLabel termLabel;
    private JLabel enrollmentLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel scoreLabel;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> columnComboBox;
    
    private CSVParser p;
    private GradeBook gb;
    private Student s;
    private Course c;
    private List<String> coursesList;

    public GamegogyGUI() {
        initComponents();
    }

    public List<String> getCourseComboValues() {
        return p.getCourseIdsAsList();
    }

    public List<String> getColumnComboValues() {
        return gb.getColumnHeaders();
    }

    private void initComponents() {
        p = new CSVParser();
        gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv");
        setTitle("Gamegogy");
        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        termLabel = new JLabel(p.getCourseTerm("99000"));
        enrollmentLabel = new JLabel(p.getEnrollment("99000"));
        idLabel = new JLabel();
        nameLabel = new JLabel();
        emailLabel = new JLabel();
        scoreLabel = new JLabel();
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
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));

        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox courseComboBox = (JComboBox) event.getSource();
                Object courseSelected = courseComboBox.getSelectedItem();
                String courseSELECTED = courseSelected.toString();
                try {
                    if(courseSELECTED != null && !courseSELECTED.isEmpty()) {
                        gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/"+courseSELECTED+".csv");
                        columnComboBox.setModel(new DefaultComboBoxModel<>(gb.getColumnHeaders().toArray(new String[0])));
                        p = new CSVParser();
                        c = new Course("","","","");
                        //nameLabel.setText(gb.getGrades().toString());
                        termLabel.setText(p.getCourseTerm(courseSELECTED));
                        enrollmentLabel.setText(p.getEnrollment(courseSELECTED));
                    }
                } catch (NullPointerException e) {}
            }
        });

        columnComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox columnComboBox = (JComboBox) event.getSource();
                Object headerSelected = columnComboBox.getSelectedItem();
                List<String> headerList = getColumnComboValues();
                Student s = new Student("","","","");
                List<List<Float>> gradesList = gb.getGrades();
                List<Float> rowGrades = gb.getRowGrades();
                List<String> idsList = gb.getIds();
    
                try {
                    int headerIndex = headerList.indexOf(headerSelected);                   
                    emailLabel.setText(Integer.toString(headerIndex));
                    
                    //for(int i = 0; i < headerList.size(); i++) {
                        for(int j = 0; j < idsList.size(); j++) {
                            float maxInColumn = gradesList.get(headerIndex).get(j);
                            if(maxInColumn < gradesList.get(headerIndex).get(j+1)) {
                                maxInColumn = gradesList.get(headerIndex).get(j++);
                            }  
                            scoreLabel.setText(""+maxInColumn);
                        }
                    //}
                    
                    

                    
    
                    //scoreLabel.setText(Integer.toString(headerIndex));
                // sort corresponding column in course file
                // get info of highest scoring student
            } catch (NullPointerException e) {}
              catch (ClassCastException e) {}
        }});
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        
        JLabel courseLabel = new JLabel("Course");
        JLabel columnLabel = new JLabel("Column");
        
        topPanel.add(courseComboBox, BorderLayout.WEST);
        topPanel.add(courseLabel);
        topPanel.add(columnComboBox, BorderLayout.EAST);
        //topPanel.add(columnLabel);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void changeCourse(String file) {
        gb = new GradeBook(file);
        populateCourseComboBox();
    }
    
    public void populateCourseComboBox() {
        courseComboBox = new JComboBox<>(p.getCourseIdsAsList().toArray(new String[0]));
    }

    public void populateColumnComboBox() {
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
    }

    public static void main(String args[]) {
        GamegogyGUI g = new GamegogyGUI();
    }

}
