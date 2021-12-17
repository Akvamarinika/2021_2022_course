package ru.cft.focusstart.task6.client.view;

import ru.cft.focusstart.task6.client.ChatImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NickNameWindow extends Window {
    private final JPanel inputNicknamePanel = new JPanel();
    private final JPanel btnPanel = new JPanel();

    private JTextField nicknameField;

    private final NickNameEventListener nicknameListener;

    public NickNameWindow(NickNameEventListener nicknameListener) {
        initWindow();
        initInputNicknamePanel();

        JButton nickNameButton = createNicknameButton(nicknameField);

        add(BorderLayout.NORTH, inputNicknamePanel);
        add(BorderLayout.SOUTH, btnPanel.add(nickNameButton));

        this.nicknameListener = nicknameListener;

        setDefaultCloseOperation(ConnectionWindow.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
       // setVisible(true);
    }

    private void initWindow(){
        setTitle("Ввод никнейма");
        ImageIcon icon = ChatImage.ICON_APP.getImageIcon(); //icon
        setIconImage(icon.getImage());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();// size monitor
        setBounds(dimension.width / 2 - 200, dimension.height / 2 - 100, 400, 200); // size app window
    }

    private void initInputNicknamePanel(){
        inputNicknamePanel.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding

        JLabel nicknameLabel = new JLabel("Никнейм: ");
        nicknameField = new JTextField(20);

        inputNicknamePanel.add(nicknameLabel, BorderLayout.CENTER);
        inputNicknamePanel.add(nicknameField, BorderLayout.CENTER);
    }

    private JButton createNicknameButton(JTextField nicknameTextField){
        JButton nicknameButton = new JButton();

        nicknameButton.setText("OK");

        nicknameButton.addActionListener(e -> {
            dispose();

            if (nicknameListener != null) {
                nicknameListener.onNicknameEntered(nicknameTextField.getText());
            }
        });

        return nicknameButton;
    }

}
