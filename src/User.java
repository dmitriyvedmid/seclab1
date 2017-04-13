/**
 * Created by dmitr on 15.03.2017.
 */
public class User {
    String nickname;
    String password;
    String userRights;

    public User(){}

    public User(String nickname, String password, String userRights){
        this.nickname = nickname;
        this.password = password;
        this.userRights = userRights;
    }

    public void printUser(){
        System.out.println(this.nickname + " " + this.password + " " + this.userRights);
    }
}
