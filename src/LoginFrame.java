import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmitr on 06.03.2017.
 */
public class LoginFrame extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField nicknameField;
    JTextField passwordField;
    JButton loginButton;
    public static FileReader file;
    String prevNickname;
    int loginIterator;


    public LoginFrame(String s) {
        super(s);
        loginIterator = 0;
        setLayout(new FlowLayout());
        l1 = new JLabel("nickname:");
        l2 = new JLabel("password:");
        loginButton = new JButton("login");
        nicknameField = new JTextField(10);
        passwordField = new JTextField(10);
        add(l1);
        add(nicknameField);
        add(l2);
        add(passwordField);
        add(loginButton);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(200, 120);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginButton.addActionListener(this);
        file = new FileReader();
        file.readFile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String curNickname = nicknameField.getText();
            if (loginIterator == 0)
                prevNickname = curNickname;
            if (curNickname.equals(prevNickname)) {
                loginIterator++;
            } else if (!curNickname.equals(prevNickname)) {
                loginIterator = 0;
            }
            if (loginIterator < 3) {
                login(nicknameField.getText(), passwordField.getText());
            } else {
                JOptionPane.showMessageDialog(null, "User locked\n Too many attempts to login");
                for (int i = 0; i < FileReader.usersbase.size(); i++)
                    if (FileReader.usersbase.get(i).nickname.equals(curNickname)) {
                        FileReader.usersbase.get(i).userRights = "blocked";
                        LoginFrame.file.rewriteData();
                    }
            }
        }
    }

    void login(String NN, String PW) {
        for (int i = 0; i < FileReader.usersbase.size(); i++) {
            if (FileReader.usersbase.get(i).nickname.equals(NN)) {
                if (FileReader.usersbase.get(i).password.equals(PW) ||
                        (FileReader.usersbase.get(i).password.equals("null") && PW.equals(""))) {
                    if (FileReader.usersbase.get(i).userRights.equals("administrator")) {
                        file.readFile();
                        AdminFrame admin = new AdminFrame("admin");
                        loginIterator = 0;
                        setVisible(false);
                        break;
                    } else if (FileReader.usersbase.get(i).userRights.equals("user")) {
                        UserFrame uf = new UserFrame(NN, i);
                        loginIterator = 0;
                        setVisible(false);
                        break;
                    } else JOptionPane.showMessageDialog(null, "User locked\n Too many attempts to login");
                } else if (i == FileReader.usersbase.size() - 1)
                    JOptionPane.showMessageDialog(null, "wrong password entered");
            } else if (i == FileReader.usersbase.size() - 1)
                JOptionPane.showMessageDialog(null, "wrong username entered");
        }
    }
}


