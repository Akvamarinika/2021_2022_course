package ru.cft.focusstart.task6.client.view;

import ru.cft.focusstart.task6.client.ChatImage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ChatWindow extends Window {
    JPanel messagesPanel;
    JPanel usersPanel;
    JPanel inputPanel;

    public ChatWindow() {
        setTitle("MegaChat");
        ImageIcon icon = ChatImage.ICON_APP.getImageIcon();
        setIconImage(icon.getImage());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();// size monitor
        setBounds(dimension.width / 2 - 400, dimension.height / 2 - 350, 810, 700); // size app window
        setDefaultCloseOperation(ChatWindow.EXIT_ON_CLOSE);

        createTextAreaMessages();
        createInputPanel();
        createUsersPanel();

        add(BorderLayout.CENTER, messagesPanel);
        add(BorderLayout.EAST, usersPanel);
        add(BorderLayout.SOUTH, inputPanel);

        setResizable(false);
        pack();


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                super.windowClosing(event);
                //TODO
            }


        });
    }

    private void createTextAreaMessages(){
        messagesPanel = new JPanel();
        messagesPanel.setPreferredSize(new Dimension(600, 400));
        messagesPanel.setBorder(new EmptyBorder(5, 10, 5, 10)); //padding

        JTextArea allMessagesTextArea = new JTextArea(600, 400);
        allMessagesTextArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        allMessagesTextArea.setEditable(false);
        allMessagesTextArea.setLineWrap(true); //не расширять

        JScrollPane messageScrollPane = new JScrollPane(allMessagesTextArea);
        messagesPanel.add(messageScrollPane, BorderLayout.CENTER);
    }

    private void createInputPanel(){
        inputPanel = new JPanel();
        inputPanel.setBorder(new EmptyBorder(10, 5, 10, 5)); //padding

        JLabel inputLabel = new JLabel("Ввод сообщения: ");
        inputPanel.add(inputLabel, BorderLayout.WEST);

        JTextField inputTextField = new JTextField(40); // accepts upto 40 characters
        inputPanel.add(inputTextField, BorderLayout.WEST);

        JButton resetBtn = new JButton("Сброс");
        inputPanel.add(resetBtn, BorderLayout.CENTER);

        JButton sendMessageBtn = new JButton("Отправить");
        inputPanel.add(sendMessageBtn, BorderLayout.EAST);
    }

    private void createUsersPanel(){
        usersPanel = new JPanel();

        Border borderUsersOnline = BorderFactory.createEtchedBorder();
        Border borderTitleUsersOnline = BorderFactory.createTitledBorder(borderUsersOnline, "Сейчас Онлайн: ");

        usersPanel.setBorder(borderTitleUsersOnline);
        usersPanel.setPreferredSize(new Dimension(150, 400));

        JLabel inputLabel = new JLabel("NickName1 ");
        usersPanel.add(inputLabel, BorderLayout.WEST);
    }

}




