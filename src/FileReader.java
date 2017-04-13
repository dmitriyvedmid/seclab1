import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dmitr on 15.03.2017.
 */
public class FileReader {
    Scanner scn;
    public static File file;
    public static ArrayList<User> usersbase = new ArrayList<>();

    public FileReader() {
        try {
            scn = new Scanner(new File("file.txt"));
        } catch (Exception ex) {
            User user = new User("admin", "null", "administrator");
            usersbase.add(user);
            createFile();
        }
        try {
            readFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void readFile() {
        while (scn.hasNext()) {
            User user = new User();
            String s = scn.next();
            String s1 = scn.next();
            String s2 = scn.next();
            user.nickname = s;
            user.password = s1;
            user.userRights = s2;
            usersbase.add(user);
        }
    }

    public void addData(User user) {
        String absoluteFilePath = "file.txt";
        clearFile(absoluteFilePath);
        usersbase.add(user);
        createFile();
    }

    void clearFile(String absoluteFilePath) {
        try {
            FileWriter fstream1 = new FileWriter(absoluteFilePath);
            BufferedWriter out1 = new BufferedWriter(fstream1);
            out1.write("");
            out1.close();
        } catch (Exception e) {
            System.out.println("error empting file");
        }
    }

    public void removeData(User user) {
        String absoluteFilePath = "file.txt";
        new File(absoluteFilePath).delete();
        usersbase.remove(user);
        createFile();
        }

    public void rewriteData(){
        String absoluteFilePath = "file.txt";
        clearFile(absoluteFilePath);
        createFile();
    }

    private void createFile() {
        String absoluteFilePath = "file.txt";
        file = new File(absoluteFilePath);
        try {
            file.createNewFile();
            try (FileWriter writer = new FileWriter("file.txt", false)) {
                for (int i = 0; i < usersbase.size(); i++) {
                    writer.write(usersbase.get(i).nickname + " ");
                    writer.write(usersbase.get(i).password + " ");
                    writer.write(usersbase.get(i).userRights + "\n");
                }
                writer.flush();
                writer.close();
            } catch (IOException exc) {
                System.out.println(exc.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFile();
    }

    public Object[][] toStringArray() {
        String[][] array = new String[7*usersbase.size()][3];
        for (int i = 0; i < usersbase.size(); i++) {
            User user = usersbase.get(i);
            array[i][0] = user.nickname;
            array[i][1] = user.password;
            array[i][2] = user.userRights;
        }
        return array;
    }

    public boolean verifyUserName(String username) {
        boolean value = true;
        for (int i = 0; i < usersbase.size(); i++) {
            if (usersbase.get(i).nickname.equals(username))
                value = false;
        }
        return value;
    }
}