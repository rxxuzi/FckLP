package check;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class CheckUI {
    public static void main(String[] args) {
        var frame = new JFrame("Check");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(400,300);
        frame.add(new CheckPanel());
    }

    private static class CheckPanel extends JPanel {
        private JLabel[] labels = new JLabel[17];
        public CheckPanel() {
            setBackground(Color.white);
            setLayout(null);
            for (int i = 0 ; i < labels.length ; i ++){
                labels[i] = new JLabel();
                labels[i].setFont(new Font("Arial", Font.PLAIN, 10));
                labels[i].setBounds(10,i * 15,300,10);
                labels[i].setText(CheckJdk2.checkJdkVersion(i));
                add(labels[i]);
            }
        }

    }
}
