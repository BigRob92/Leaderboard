package edu.jsu.mcis;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Leaderboard extends JPanel {
   private static final int RECT_X = 20;
   private static final int RECT_Y = RECT_X;
   private static final int RECT_W = 20;
   private static final int RECT_E = 80;
   private static final int RECT_WIDTH = 50;
   private static final int RECT_HEIGHT = 25;

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
	  //g2d.setColor(Color.GREEN);
	  g.drawRect(RECT_W, RECT_E, RECT_WIDTH, RECT_HEIGHT);
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(500, 500);
   }

   private static void createAndShowGui() {
      Leaderboard mainPanel = new Leaderboard();

      JFrame frame = new JFrame("DrawRect");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}