import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmitr on 15.03.2017.
 */
public class AddUserFrame extends JFrame implements ActionListener {

    JLabel nn;
    JLabel pw;
    JTextField NNField;
    JTextField PWField;
    JCheckBox AdminRights;
    JButton AddUser;
    User user = new User();
    FileReader file1;

    public AddUserFrame(String s) {
        super(s);
        setLayout(new FlowLayout());
        nn = new JLabel("Nickname");
        pw = new JLabel("Password");
        NNField = new JTextField(20);
        PWField = new JTextField(20);
        AdminRights = new JCheckBox("Has Admin rights", false);
        AddUser = new JButton("OK");
        add(nn);
        add(NNField);
        add(pw);
        add(PWField);
        add(AdminRights);
        add(AddUser);

        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        AddUser.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AddUser) {
            if (!NNField.getText().equals("")) {
                if (LoginFrame.file.verifyUserName(NNField.getText())) {
                    user.nickname = NNField.getText();
                    if (PWField.getText().equals("")) user.password = "null";
                    else if (checkPass(PWField.getText())){
                        user.password = PWField.getText();
                    if (AdminRights.isSelected())
                        user.userRights = "administrator";
                    else user.userRights = "user";
                    LoginFrame.file.addData(user);
                    this.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Wrong password try again");
                        PWField.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This username is already registered");
                    NNField.setText("");
                    PWField.setText("");
                }
            }
        }
    }

    boolean checkPass(String password) {
        if (password.matches("(?i).*[a-zа-я].*")&&(password.matches(".*\\W+.*")))
            return true;
        return false;
    }
}