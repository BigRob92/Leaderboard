package edu.jsu.mcis;

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
        List<String> courseIds = p.getCourseIdsAsList();

        setTitle("Gamegogy");
        setPreferredSize(new Dimension(850, 750));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        termLabel = new JLabel();
        enrollmentLabel = new JLabel();
        idLabel = new JLabel();
        nameLabel = new JLabel("Aaron Garrett");
        emailLabel = new JLabel();
        scoreLabel = new JLabel();
        panel.add(new JLabel("Term", JLabel.RIGHT));
        panel.add(termLabel);
        panel.add(new JLabel("Enrollment", JLabel.RIGHT));
        panel.add(enrollmentLabel);
        panel.add(new JLabel("ID", JLabel.RIGHT));
        panel.add(idLabel);
        panel.add(new JLabel("Name", JLabel.RIGHT));
        panel.add(nameLabel);
        panel.add(new JLabel("Email", JLabel.RIGHT));
        panel.add(emailLabel);
        panel.add(new JLabel("Score", JLabel.RIGHT));
        panel.add(scoreLabel);

        courseComboBox = new JComboBox<>(p.getCourseIdsAsList().toArray(new String[0]));

        columnComboBox = new JComboBox<>();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(courseComboBox, BorderLayout.WEST);
        topPanel.add(columnComboBox, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        
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
        System.out.println("hi");
    }
}
