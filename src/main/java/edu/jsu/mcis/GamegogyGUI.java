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
    private Course c;
    private List<String> courseIds;
    private List<String> courseInfo;
    private List<String> studentInfo;

    public GamegogyGUI() {
        initComponents();
    }

    public List<String> getCourseComboValues() {
        
        // get the strings that are loaded in the course combo box
        // return them as a list
        return null;
    }

    private void initComponents() {
        p = new CSVParser();
        courseIds = p.getCourseIdsAsList();
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
                Object selected = courseComboBox.getSelectedItem();
                String SELECTED = selected.toString();
                // get course term and enrollment
                // read file to get column headers
                // populate 2nd combobox with headers
                try {
                    if(SELECTED != null && !SELECTED.isEmpty()) {
                        gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/"+SELECTED+".csv");
                        columnComboBox.setModel(new DefaultComboBoxModel<>(gb.getColumnHeaders().toArray(new String[0])));
                        //columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
                        p = new CSVParser();
                        c = new Course("","","","");
                        nameLabel.setText(gb.getGrades().toString());
                        //populateColumnComboBox();
                        termLabel.setText(p.getCourseTerm(SELECTED));
                        enrollmentLabel.setText(p.getEnrollment(SELECTED));
                    }
                } catch (NullPointerException e) {}
            }
        });

        columnComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox columnComboBox = (JComboBox) event.getSource();
                Object selected = courseComboBox.getSelectedItem();
                // sort corresponding column in course file
                // get info of highest scoring student
            }
        });

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
/*
    public List<String> updateCourseInfo() {
        return courseInfo;
    }

    public List<String> updateStudentInfo() {
        return studentInfo;
    }

    public void updateTermLabel() {
        termLabel.setText(p.getCourseTerm());
    }

    public void updateEnrollmentLabel() {
        enrollmentLabel.setText(gb.getEnrollment());
    }

    public void updateIdLabel() {
        idLabel.setText(c.getId());
    }

    public void updateNameLabel() {
        nameLabel.setText(p.getName());
    }

    public void updateEmailLabel() {
        emailLabel.setText(p.getEmail());
    }

    public void updateScoreLabel() {
        scoreLabel.setText(p.getScore());
    }
*/
    public static void main(String args[]) {
        GamegogyGUI g = new GamegogyGUI();
    }

}
