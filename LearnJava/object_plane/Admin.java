package object_plane;

import java.util.Scanner;

public class Admin {
    String admin = "admin";
    String passwd = "123";

    public int check(){
        try (Scanner in = new Scanner(System.in)) {
            System.out.printf("请输入账号:");
            String admin_output = in.next();
            System.out.printf("请输入密码:");
            String passwd_output = in.next();
            if (admin.equals(admin_output) && passwd.equals(passwd_output)) {
                System.out.printf("操作成功！\n");
                return 0;
            }
        }
        //        System.out.printf("账号密码错误");
        return -1;
    }
}