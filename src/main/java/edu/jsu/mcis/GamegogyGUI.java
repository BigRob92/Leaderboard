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
    private String filePath;

    public GamegogyGUI() {
        initComponents();
    }

    public List<String> getCourseComboValues() {
        // get the strings that are loaded in the course combo box
        // return them as a list
        return null;
    }
    
    public void populateColumnComboBox() {
        columnComboBox = new JComboBox<>(gb.getColumnHeaders().toArray(new String[0]));
    }
    private void initComponents() {
        p = new CSVParser();
        List<String> courseIds = p.getCourseIdsAsList();
        //filePath = "99000.csv\"";
        gb = new GradeBook("/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv");

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
                filePath = selected.toString();
                filePath = "/home/ben/Development/cs310/Leaderboard/src/main/resources/courses/99000.csv";
                populateColumnComboBox();

                if(selected.toString().equals("99001")) {
                    nameLabel.setText(gb.getGrades().toString());
                }
                else if(selected.toString().equals("99003")) {
                    nameLabel.setText("Ben Carson");
                }
                else if(selected.toString().equals("99005")) {
                    nameLabel.setText(gb.getIds().toString());
                }
                else if(selected.toString().equals("99007")) {
                    nameLabel.setText(gb.numberOfEnrolledStudents());
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

        
        /*
        courseLabel = new JLabel();
        courseComboBox = new JComboBox(courseIds);

        columnLabel = new JLabel();
        columnComboBox = new JComboBox<>();

        termLabel = new JLabel();
        termField = new JTextField();
        
        enrollmentLabel = new JLabel();
        enrollmentField = new JTextField();
        
        jSeparator = new JSeparator();
        
        idLabel = new JLabel();
        idField = new JTextField();

        nameLabel = new JLabel();
        nameField = new JTextField();

        emailLabel = new JLabel();
        emailField = new JTextField();

        scoreLabel = new JLabel();
        scoreField = new JTextField();

        courseLabel.setText("Course");
        //courseComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Course 1", "Course 2", "Course 3" }));
        //courseComboBox.setModel(new DefaultComboBoxModel<>(courseIds));

        columnLabel.setText("Column");
        columnComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Assignment 1", "Assignment 2", "Assignment 3" }));

        termLabel.setText("Term:");
        termField.setText("Term goes here");
        
        /*
        termField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionListener evt) {
                termFieldActionPerformed(evt);
            }
        });
        */

//        enrollmentLabel.setText("Enrollment:");
//        enrollmentField.setText("Enrollment goes here");
        
        /*
        enrollmentField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionListener evt) {
                enrollmentFieldActionPerformed(evt);
            }
        });
        */
        

        /*
        idLabel.setText("ID:");
        idField.setText("ID goes here");

        nameLabel.setText("Name:");
        nameField.setText("Name goes here");

        emailLabel.setText("Email:");
        emailField.setText("Email goes here");

        scoreLabel.setText("Score:");
        scoreField.setText("Score goes here");

        courseComboBox.getAccessibleContext().setAccessibleName("");
        columnComboBox.getAccessibleContext().setAccessibleDescription("");
        */


        //pack();
        pack();
        setVisible(true);
    }

    //private void termFieldActionPerformed(ActionEvent evt) {}

    //private void enrollmentFieldActionPerformed(ActionEvent evt) {}

    public static void main(String args[]) {
        GamegogyGUI g = new GamegogyGUI();
    }
}
