package com.filesearch;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("File Chooser");
        setLayout(new FlowLayout());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Choose a file");
        JTextArea textArea1 = new JTextArea(2, 20);
        JLabel jlabel1 = new JLabel("Key at beginning");
        jlabel1.setLabelFor(textArea1);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 2));
        panel1.add(jlabel1);
        panel1.add(textArea1);

        JTextArea textArea2 = new JTextArea(2, 20);
        JLabel jlabel2 = new JLabel("Key inside");
        jlabel2.setLabelFor(textArea2);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(jlabel2);
        panel2.add(textArea2);

        JTextArea textArea3 = new JTextArea(2, 20);
        JLabel jlabel3 = new JLabel("Key at end");
        jlabel3.setLabelFor(textArea3);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 2));
        panel3.add(jlabel3);
        panel3.add(textArea3);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1));
        mainPanel.add(panel1);
        mainPanel.add(new Label());
        mainPanel.add(panel2);
        mainPanel.add(new Label());
        mainPanel.add(panel3);
        mainPanel.add(new Label());
        mainPanel.add(button);

        FileChooser fc = new FileChooser(textArea1, textArea2, textArea3);
        button.addActionListener(fc);
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
