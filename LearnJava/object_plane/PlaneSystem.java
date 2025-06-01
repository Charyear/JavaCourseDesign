package object_plane;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PlaneSystem {
    //对象写的操作
    public static void WriteObj(Object obj,File file) throws Exception{
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
    public static Object ReadObj(File file) throws Exception{
        FileInputStream fi = null;
        ObjectInputStream ois = null;
        Object obj = null;
        fi = new FileInputStream(file);
        ois = new ObjectInputStream(fi);
        obj = ois.readObject();
        ois.close();
        fi.close();
        return obj;
    }
    public static String random_ordernumber(){

        StringBuilder str1 = new StringBuilder();
        Random random = new Random();
        for(int j=0;j<8;j++){
            str1.append(random.nextInt(10));
        }

        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int index = random.nextInt(a.length());
        String str2 = a.substring(index,index+1);
        String str3 = str1.toString();
        String str = str2+str3;
        return str;
    }
    @SuppressWarnings("all")
    public static String check_flag(String numbers){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.number.equals(numbers) && p.flag.equals("满")){
                    return "满";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "无";
    }
    @SuppressWarnings("all")
    public  List create_plane(){
        Scanner in = new Scanner(System.in);
        Plane p = new Plane();
        //创建基本信息
        System.out.printf("请输入航班号:");
        p.number = in.next();
        System.out.printf("请输入起飞地点:");
        p.start_place = in.next();
        System.out.printf("请输入降落地点:");
        p.end_place = in.next();
        System.out.printf("请输入起飞时间:");
        p.start_time = in.next();
        System.out.printf("请输入降落时间:");
        p.end_time = in.next();
        p.flag = "无";
        System.out.printf("请输入该航班价格:");
        p.price = in.nextDouble();
        System.out.printf("请输入该航班折扣:");
        p.discount = in.nextDouble();

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        pi.list_plane.add(p);
        return pi.list_plane;
    }
}