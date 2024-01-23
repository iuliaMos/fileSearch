package com.filesearch;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("File Chooser");
        setLayout(new FlowLayout());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Choose a file");
        JTextArea textArea = new JTextArea(2, 20);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1));
        p.add(textArea);
        p.add(button);

        FileChooser fc = new FileChooser(textArea);
        button.addActionListener(fc);
        add(p);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
