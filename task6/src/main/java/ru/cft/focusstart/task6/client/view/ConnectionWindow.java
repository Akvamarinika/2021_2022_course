package ru.cft.focusstart.task6.client.view;

import ru.cft.focusstart.task6.client.ChatImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConnectionWindow extends Window {
    private final JPanel connectionPanel = new JPanel();
    private final JPanel inputPanel = new JPanel();

    private JTextField hostIPTextField;
    private JTextField portTextField;

    private final ConnectionChatEventListener connectionListener;

    //private ActionListener connectListener;

    public ConnectionWindow(ConnectionChatEventListener connectionListener){
        initWindow();
        initInputPanel();

        JButton connectionButton = createConnectionButton(hostIPTextField, portTextField);
        connectionPanel.add(connectionButton);

        add(BorderLayout.NORTH, inputPanel);
        add(BorderLayout.SOUTH, connectionPanel);

        this.connectionListener = connectionListener;

        setDefaultCloseOperation(ConnectionWindow.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
    }

    private void initWindow(){
        setTitle("Подключение к чату");
        ImageIcon icon = ChatImage.ICON_APP.getImageIcon(); //icon
        setIconImage(icon.getImage());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();// size monitor
        setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300); // size app window
    }

    private JButton createConnectionButton(JTextField hostField, JTextField portField){
        JButton connectButton = new JButton();

        Font font = new Font("Segoe UI Black", Font.PLAIN, 14);
        connectButton.setFont(font);
        connectButton.setText("Подключиться к чату");
        connectButton.setForeground(Color.BLACK); //text color

        connectButton.addActionListener(e -> {
            dispose();

            if (connectionListener != null) {
                connectionListener.onPressConnectionButton(hostField.getText(), portField.getText());
            }
        });

        return connectButton;
    }

    private void initInputPanel(){
        inputPanel.setPreferredSize(new Dimension(400, 90));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); //padding

        JLabel hostLabel = new JLabel("Хост: ");
        hostIPTextField = new JTextField(30);

        JLabel portLabel = new JLabel("Порт: ");
        portTextField = new JTextField(4);

        inputPanel.add(hostLabel, BorderLayout.CENTER);
        inputPanel.add(hostIPTextField, BorderLayout.CENTER);
        inputPanel.add(portLabel, BorderLayout.CENTER);
        inputPanel.add(portTextField, BorderLayout.CENTER);
    }

    @Override
    public void visible() {
        setVisible(true);
    }

    @Override
    public void close() {
        dispose();
    }


//    public void setConnectAction(ActionListener listener) {
//        connectButton.addActionListener(listener);
//    }

}
