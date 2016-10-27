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
    private File filePath;
    private List<String> files;
    //private String filePath;
    private List<String> courseIds;
    private List<String> courseInfo;
    private List<String> studentInfo;

    public GamegogyGUI() {
        filePath = new File("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses");
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
        //filePath = "99000.csv\"";
        gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv");
        c = new Course("","","","");

        setTitle("Gamegogy");
        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        termLabel = new JLabel();
        enrollmentLabel = new JLabel();
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
        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox courseComboBox = (JComboBox) event.getSource();
                Object selected = courseComboBox.getSelectedItem();
                //filePath = selected.toString()+".csv";
                //filePath = "/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/"+filePath+".csv";
                //populateColumnComboBox();

                if(selected.toString().equals("99001")) {
                    nameLabel.setText(gb.getGrades().toString());
                }
                else if(selected.toString().equals("99003")) {
                    nameLabel.setText(p.getCourseYear());
                }
                else if(selected.toString().equals("99005")) {
                    nameLabel.setText(gb.getIds().toString());
                }
                else if(selected.toString().equals("99007")) {
                    nameLabel.setText(gb.getEnrollment());
                }
            }
        });

        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(courseComboBox, BorderLayout.WEST);
        topPanel.add(columnComboBox, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
    /*
    public void populateColumnComboBox() {
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
    }

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
