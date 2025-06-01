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
//        把对象plane加入文件里面
    }
    @SuppressWarnings("all")
    //所以查询的方法
    public void show() {

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        System.out.printf("航班号   起点    终点   出发时间    到达时间   价格   折扣  满仓\n");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                        p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("all")
    public void plane_show(String start_planes, String end_planes){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        System.out.printf("航班号   起点    终点   出发时间    到达时间   价格   折扣  满仓\n");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.start_place.equals(start_planes) && p.end_place.equals(end_planes)){
                    System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                            p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("all")
    public void plane_numbers_show(String plane_numbers){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        System.out.printf("航班号   起点    终点   出发时间    到达时间   价格   折扣  满仓\n");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.number.equals(plane_numbers)){
                    System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                            p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("all")
    public void plane_time_show(String start_times, String end_times){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        System.out.printf("航班号   起点    终点   出发时间    到达时间   价格   折扣  满仓\n");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.start_time.equals(start_times) && p.end_time.equals(end_times)){
                    System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                            p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("all")
    public void plane_prince_show(double price){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        System.out.printf("航班号   起点    终点   出发时间    到达时间   价格   折扣  满仓\n");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.price == price){
                    System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                            p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("all")
    public void plane_discount_show(double discount){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        System.out.printf("航班号   起点    终点   出发时间    到达时间   价格   折扣  满仓\n");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.discount == discount){
                    System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                            p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("all")
    public void plane_flag_show(String flag){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        System.out.printf("航班号   起点    终点   出发时间    到达时间   价格   折扣  满仓\n");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.flag.equals(flag) ){
                    System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                            p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询方法的结束位置
    @SuppressWarnings("all")
    public List change_plane_info(String number, String start_place, String end_place, String start_time, String end_time, double price, double discount, String flag){

        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        try {
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.number.equals(number)) {
                    p.start_place = start_place;
                    p.end_place = end_place;
                    p.start_time = start_time;
                    p.end_time = end_time;
                    p.price = price;
                    p.flag = flag;
                    p.discount = discount;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi.list_plane;
    }

    @SuppressWarnings("all")
    public static void best_plane(String numbers){
        System.out.printf("已经为你寻找最适合的航班！！\n");
        plane_info pi = new plane_info();
        File file = new File("E://学Java//object_plane//plane.txt");
        try {
            String start_place = " ";
            String end_place = " ";
            pi.list_plane = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.number.equals(numbers) ){
                    start_place = p.start_place;
                    end_place = p.end_place;
                }
            }

            for (int i = 0; i < pi.list_plane.size(); i++) {
                Plane p = (Plane) pi.list_plane.get(i);
                if(p.start_place.equals(start_place) && p.end_place.equals(end_place)&& !p.number.equals(numbers) ){
                    System.out.printf( p.number + "    " + p.start_place + "    " + p.end_place + "    " +
                            p.start_time + "      " + p.end_time + "     " + p.price + "    " + p.discount + "  "+p.flag+"\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("all")
    public  List create_user(){
        Scanner in = new Scanner(System.in);
        User u = new User();
        //创建基本信息
        System.out.printf("请输入航班号");
        u.number_user = in.next();

        if (check_flag(u.number_user).equals("满")){
            System.out.printf("已经满仓了，请等待，正在为你寻找最适合的航班\n");
            best_plane(u.number_user);
            System.out.printf("是否需要，不需要按n（or N) 需要 按 y （or Y）");
            String a =in.next();
            if(a.equals("n")||a.equals("N")){
                return null;
            }else if ((a.equals("Y")||a.equals("y"))){

            }else{
                System.out.printf("输入了错误的字符");
                return null;
            }
        }
        System.out.printf("请输入姓名");
        u.name = in.next();
        System.out.printf("请输入身份证号码");
        u.idcard = in.next();


        User_info pi = new User_info();
        File file = new File("user");
        try {
            pi.list_users = ((LinkedList) ReadObj(file));
            u.ordernumber = random_ordernumber();
            for(int i=0;i<pi.list_users.size();i++){
                User u1 = pi.list_users.get(i);
                if(u1.name == u.name){
                    u.sum++;
                }
                if(u1.ordernumber == u.ordernumber){
                    u.ordernumber = random_ordernumber();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("你的订单号为"+u.ordernumber+"\n");
        pi.list_users.add(u);
        return pi.list_users;
//        把对象plane加入文件里面
    }
    @SuppressWarnings("all")
    public List<User> change_user_info(String ordernumbers){
        User_info pi = new User_info();
        File file = new File("user");
        try {
            pi.list_users = ((LinkedList) ReadObj(file));

            for (int i = 0; i < pi.list_users.size(); i++) {
                User u = (User) pi.list_users.get(i);
                if(u.ordernumber.equals(ordernumbers)) {
                    pi.list_users.remove(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi.list_users;
    }
    @SuppressWarnings("all")
    public void show_user(){
        User_info pi = new User_info();
        File file = new File("user");
        try {
            pi.list_users = ((LinkedList) ReadObj(file));
            System.out.printf("用户姓名   身份证号码    订单飞机号      订 单 号    订单数量 \n");
            for (int i = 0; i < pi.list_users.size(); i++) {
                User u = (User) pi.list_users.get(i);
                System.out.printf(" "+u.name+"        "+u.idcard+"        "+u.number_user+"         "+u.ordernumber+"    "+u.sum+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}