import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmitr on 07.03.2017.
 */
public class AdminFrame extends JFrame implements ActionListener {
    JTable usersListTable;
    JButton deleteUser;
    JButton addUser;
    JButton changeRights;
    TableModel model;
    static Object[] columnNames = {"Nickname", "Password", "User Rights"};


    public AdminFrame(String s) {
        super(s);
        setLayout(new FlowLayout());
        deleteUser = new JButton("delete selected user");
        addUser = new JButton("add new user");
        changeRights = new JButton("Change User Rights");
        model = new DefaultTableModel(LoginFrame.file.toStringArray(), columnNames);
        usersListTable = new JTable(model);
        usersListTable.setPreferredSize(new Dimension(350, 400));
        add(usersListTable);
        add(addUser);
        add(changeRights);
        add(deleteUser);
        setSize(400, 520);
        setLocationRelativeTo(null);
        setVisible(true);
        deleteUser.addActionListener(this);
        addUser.addActionListener(this);
        changeRights.addActionListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == deleteUser) {
            LoginFrame.file.removeData(FileReader.usersbase.remove(usersListTable.getSelectedRow()));
            ((DefaultTableModel) usersListTable.getModel()).setDataVector(LoginFrame.file.toStringArray(), columnNames);
        } else if (source == addUser) {
            AddUserFrame fr = new AddUserFrame("Add new user");
            ((DefaultTableModel) usersListTable.getModel()).setDataVector(LoginFrame.file.toStringArray(), columnNames);
        } else if (source == changeRights) {
            User user = FileReader.usersbase.get(usersListTable.getSelectedRow());
            if (user.userRights.equals("administrator")) {
                user.userRights = "user";
            } else if (user.userRights.equals("user")) {
                user.userRights = "administrator";
            } else {
                user.userRights = "user";
            }
            LoginFrame.file.rewriteData();
            ((DefaultTableModel) usersListTable.getModel()).setDataVector(LoginFrame.file.toStringArray(), columnNames);
        }
    }
}

