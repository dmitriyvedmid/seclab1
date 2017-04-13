import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmitr on 09.03.2017.
 */
public class UserFrame extends JFrame implements ActionListener {
    JButton changePWButton;
    JButton infoButton;
    int userIndex;

    public UserFrame(String NN, int userIndex) {
        super(NN);
        setLayout(new FlowLayout());
        changePWButton = new JButton("Изменить пароль");
        infoButton = new JButton("details");
        add(changePWButton);
        add(infoButton);
        changePWButton.addActionListener(this);
        infoButton.addActionListener(this);
        setSize(200, 100);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.userIndex=userIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==changePWButton) {
            if (JOptionPane.showInputDialog("Введите новый пароль").equals(""))
                FileReader.usersbase.get(userIndex).password=null;
            else if (checkPass(JOptionPane.showInputDialog("Введите новый пароль")))
                FileReader.usersbase.get(userIndex).password=JOptionPane.showInputDialog("Введите новый пароль");
            LoginFrame.file.rewriteData();
        }
        else if (e.getSource()==infoButton){
            JOptionPane.showMessageDialog(null, "Авторы:\nВедмидь Дмитрий БС-33\nСтвровойтенко Владимир БС-33");
        }
    }

    boolean checkPass(String password) {
        if (password.matches("(?i).*[a-zа-я].*")&&(password.matches(".*\\W+.*")))
            return true;
        return false;
    }
}
