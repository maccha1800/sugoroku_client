package com.ui.component.dialog;

import com.ui.component.LobbyPanel;
import com.ui.component.MainFrame;
import com.ui.scheme.FontScheme;
import com.ui.scheme.LayoutScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RequestPrivateMatchDialog extends AppDialog {
    MainFrame parentFrame;
    JTextField privateMatchIDField;

    RequestPrivateMatchDialog(MainFrame mainFrame, String label) {
        super(mainFrame);
        dialogMessage.setText(label);
        parentFrame = mainFrame;
        privateMatchIDField = new JTextField(10);
        privateMatchIDField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        privateMatchIDField.setHorizontalAlignment(JTextField.CENTER);
        privateMatchIDField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isAlphabetic(e.getKeyChar())
                    && !Character.isDigit(e.getKeyChar())
                    || privateMatchIDField.getText().length() >= 4) {
                    e.consume(); //Maximum private lobby ID length = 4, digit or alphabet only
                }
            }
        });
        positiveButton.addActionListener(new requestPrivateMatchAction());
        contentPane.add(privateMatchIDField, LayoutScheme.DIALOG_TEXTFIELD.getLayout());
    }

    public static RequestPrivateMatchDialog getDialog(MainFrame mainFrame, String label) {
        return new RequestPrivateMatchDialog(mainFrame, label);
    }

    class requestPrivateMatchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String id = privateMatchIDField.getText();
            if (id.length() == 4) { //TODO: need more work on invalid id handling
                parentFrame.changePanel(parentFrame.createLobbyPanel("private", id));
            }
        }
    }
}
