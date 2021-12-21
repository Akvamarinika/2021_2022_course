package ru.cft.focusstart.task6.client;

import ru.cft.focusstart.task6.client.enumservice.TextDialogWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

public class ViewClient extends JFrame{
    private final Client client;
    private final JTextArea messages = new JTextArea(20, 20);
    private final JTextArea users = new JTextArea(20, 15);
    private final JPanel panel = new JPanel();
    private final JTextField textField = new JTextField(35);
    private final JButton buttonDisable = new JButton("Отключиться");
    private final JButton buttonConnect = new JButton("Подключиться");
    private final JButton buttonSendMessage = new JButton("Отправить");

    public ViewClient(Client client) {
        this.client = client;
        setTitle("MegaChat");
        ImageIcon icon = ImageChat.ICON_APP.getImageIcon();
        setIconImage(icon.getImage());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();// size monitor
        setBounds(dimension.width / 2 - 300, dimension.height / 2 - 100, 610, 200); // size app window

        messages.setEditable(false);
        users.setEditable(false);
        messages.setLineWrap(true); //перенос строки
        users.setLineWrap(true);
        setResizable(false); // запретить растягивать окно

        add(new JScrollPane(messages), BorderLayout.CENTER);
        add(new JScrollPane(users), BorderLayout.EAST);

        messages.setFont(new Font("", Font.PLAIN, 14));
        users.setForeground(Color.BLUE);
        users.setFont(new Font("", Font.BOLD, 12));

        panel.add(textField);
        panel.add(buttonConnect);
        panel.add(buttonDisable);
        panel.add(buttonSendMessage,  BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); // окно по центру
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void initFrameClient() {
        addWindowListener(new WindowAdapter() { //при закрытии окна чата
            @Override
            public void windowClosing(WindowEvent e) {
                if (client.isConnect()) {
                    client.disableClient();
                }
                System.exit(0);
            }
        });
        setVisible(true);

        buttonDisable.addActionListener(e -> client.disableClient());

        buttonConnect.addActionListener(
                e -> client.connectToServer()
        );

        textField.addActionListener(e -> {
            if (client.isConnect()) {
                client.sendMessageOnServerForOtherUsers(textField.getText());
                textField.setText("");
            } else {
                errorDialogWindow(TextDialogWindow.NO_CONNECTED_ERROR.toString());
            }
        });

        buttonSendMessage.addActionListener(e -> {
            if (client.isConnect()) {
                client.sendMessageOnServerForOtherUsers(textField.getText());
                textField.setText("");
            } else {
                errorDialogWindow(TextDialogWindow.NO_CONNECTED_ERROR.toString());
            }
        });
    }

    public void addMessage(String text) {
        messages.append(text);
    }

    public void updateConnectedUsers(Set<String> connectedUsers) {
        users.setText("");

        if (client.isConnect()) {
            StringBuilder text = new StringBuilder("Список пользователей:\n");

            for (String user : connectedUsers) {
                if (user.equals(client.getYourNickname())){
                    text.append(user)
                            .append(" (Вы)")
                            .append("\n");
                } else {
                    text.append(user)
                            .append("\n");
                }
            }

            users.append(text.toString());
        }
    }

    //окно ввода адреса сервера
    public String openHostIPDialogWindow() {
        while (true) {
            String addressServer = JOptionPane.showInputDialog(
                    this, "Введите адрес сервера:",
                    "Ввод адреса сервера",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (addressServer != null) {
                return addressServer.trim();
            } else {
                return "";
            }
        }
    }

    //окно ввода порта
    public int openPortDialogWindow() {
        while (true) {
            String port = JOptionPane.showInputDialog(
                    this, "Введите порт сервера:",
                    "Ввод порта сервера",
                    JOptionPane.QUESTION_MESSAGE
            );
            try {
                return Integer.parseInt(port.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this, "Введен неккоректный порт сервера. Попробуйте еще раз.",
                        "Ошибка ввода порта сервера", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    //окно ввода ника
    public String getNameUser() {
        return JOptionPane.showInputDialog(
                this, "Введите имя:",
                "Ввод никнейма",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    //окно ошибки
    public void errorDialogWindow(String text) {
        JOptionPane.showMessageDialog(
                this, text,
                "Ошибка", JOptionPane.ERROR_MESSAGE
        );
    }


}
