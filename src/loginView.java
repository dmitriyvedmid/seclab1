import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by dmitr on 06.03.2017.
 */
public class loginView extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField nicknameField;
    JTextField passwordField;
    JButton loginButton;
    String nickname, password;
    static Scanner scn;
    File file;

    public loginView(String s) {
        super(s);
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
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 120);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            nickname = nicknameField.getText();
            password = passwordField.getText();
            try {
                scn = new Scanner(new File("file.txt"));
            } catch (Exception ex) {
                String absoluteFilePath = "file.txt";
                file = new File(absoluteFilePath);
                try {
                    file.createNewFile();
                    System.out.println(absoluteFilePath + " Файл создан");
                    try(FileWriter writer = new FileWriter("file.txt", false))
                    {
                        String text = "admin ";
                        String text1 = "pw ";
                        String text2 = "1";
                        writer.write(text);
                        writer.write(text1);
                        writer.write(text2);
                        writer.flush();
                    }
                    catch(IOException exc){
                        System.out.println(ex.getMessage());
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            login(nickname, password);
        }
    }

    void login(String NN, String PW) {
        while (scn.hasNext()){
            String s = scn.next();
            String s1 = scn.next();
            String s2 = scn.next();
            System.out.print(s+"\t");
            System.out.print(s1+"\t");
            System.out.println(s2);
            if(s.equals(NN))
                if(s1.equals(PW))
                    if(s2.equals("1"))
                        System.out.println("entered as admin");
                    else System.out.println("entered as user");
        }
        adminView adminWindow = new adminView("admin");
    }
}

