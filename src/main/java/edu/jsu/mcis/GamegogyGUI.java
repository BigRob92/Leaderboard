package edu.jsu.mcis;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class GamegogyGUI extends JFrame {

    private JLabel courseLabel;
    private JLabel columnLabel;
    private JLabel termLabel;
    private JLabel enrollmentLabel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel scoreLabel;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> columnComboBox;
    private JTextField termField;
    private JTextField enrollmentField;
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField scoreField;
    private JSeparator jSeparator;
    private CSVParser p;

    public GamegogyGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        p = new CSVParser();

        courseLabel = new JLabel();
        courseComboBox = new JComboBox<>();

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gamegogy");

        courseLabel.setText("Course");
        courseComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Course 1", "Course 2", "Course 3" }));
        
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

        enrollmentLabel.setText("Enrollment:");
        enrollmentField.setText("Enrollment goes here");
        
        /*
        enrollmentField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionListener evt) {
                enrollmentFieldActionPerformed(evt);
            }
        });
        */
        
        idLabel.setText("ID:");
        idField.setText("ID goes here");

        nameLabel.setText("Name:");
        nameField.setText("Name goes here");

        emailLabel.setText("Email:");
        emailField.setText("Email goes here");

        scoreLabel.setText("Score:");
        scoreField.setText("Score goes here");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(courseLabel)
                .addComponent(columnLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(termField)
                    .addComponent(courseComboBox, 0, 215, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enrollmentLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(columnLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enrollmentField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(emailLabel)
                    .addComponent(scoreLabel)
                    .addComponent(idLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailField)
                    .addComponent(scoreField, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(emailField)
                    .addComponent(scoreField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(courseLabel)
                        .addComponent(courseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(columnComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(columnLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(termField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enrollmentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(termLabel)
                    .addComponent(enrollmentLabel))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idLabel)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(scoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scoreLabel)
                            .addComponent(scoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        courseComboBox.getAccessibleContext().setAccessibleName("");
        columnComboBox.getAccessibleContext().setAccessibleDescription("");

        pack();
    }

    //private void termFieldActionPerformed(ActionEvent evt) {}

    //private void enrollmentFieldActionPerformed(ActionEvent evt) {}

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GamegogyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamegogyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamegogyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamegogyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamegogyGUI().setVisible(true);
            }
        });
    }
}
