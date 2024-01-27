package com.filesearch;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class FileChooser extends JFrame implements ActionListener {

    private final JTextArea textArea1;
    private final JTextArea textArea2;
    private final JTextArea textArea3;

    public FileChooser(JTextArea textArea1, JTextArea textArea2, JTextArea textArea3) {
        this.textArea1 = textArea1;
        this.textArea2 = textArea2;
        this.textArea3 = textArea3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select a file for searching");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getPath();
            String strStart = Optional.ofNullable(textArea1.getText()).filter(s -> !s.trim().isEmpty()).orElse(null);
            String strInside = Optional.ofNullable(textArea2.getText()).filter(s -> !s.trim().isEmpty()).orElse(null);
            String strEnd = Optional.ofNullable(textArea3.getText()).filter(s -> !s.trim().isEmpty()).orElse(null);
            if (strStart != null || strInside != null || strEnd != null) {
                FileSearchHandler ts = new FileSearchHandler(fileName, strStart, strInside, strEnd);
                ts.doSearchAndReturn();
            }
        }
    }
}

