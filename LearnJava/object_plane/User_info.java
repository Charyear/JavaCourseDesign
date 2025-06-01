package object_plane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class User_info {
    public List<User> list_users ;

    public static void WriteObj(Object obj, File file) throws Exception{
        FileOutputStream fo = null;
        ObjectOutputStream oos = null;
        fo = new FileOutputStream(file);
        oos = new ObjectOutputStream(fo);
        oos.writeObject(obj);
        oos.writeObject(null);
        oos.flush();
        oos.close();
        fo.close();
    }
    public static void info_user(Object obj){

        File file = new File("user");
        try {
            WriteObj(obj,file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}