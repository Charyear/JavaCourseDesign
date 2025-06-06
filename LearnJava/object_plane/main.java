package object_plane;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void menu(){
        System.out.printf("欢迎来到飞机订票系统\n");
        System.out.printf("*******************\n");
        System.out.printf("1.录入航班情况\n");
        System.out.printf("2.查询航线\n");
        System.out.printf("3.订   票\n");
        System.out.printf("4.退   票\n");
        System.out.printf("5.修改航班信息\n");
        System.out.printf("6.显示所以用户信息\n");
        System.out.printf("0.退出\n");
        System.out.printf("*******************\n");
    }
    public static void menu_select(){
        System.out.printf("欢迎来到飞机查询订票系统\n");
        System.out.printf("*******************\n");
        System.out.printf("1.通过航班号查询\n");
        System.out.printf("2.通过起降时间查询\n");
        System.out.printf("3.通过起飞抵达城市查询\n");
        System.out.printf("4.通过航班票价查询\n");
        System.out.printf("5.通过是否满仓查询\n");
        System.out.printf("6.通过折扣查询\n");
        System.out.printf("7.查询所有的航班信息\n");
        System.out.printf("0.退出\n");
        System.out.printf("*******************\n");
    }
    @SuppressWarnings("all")
    public static void main(String[] args) {

        while (true){
            menu();
            PlaneSystem planeSystem =new PlaneSystem();
            Scanner in = new Scanner(System.in);
            System.out.printf("请输入你要操作的数");
            int a = in.nextInt();

            if(a == 1){
//                List<Plane> list_info = new LinkedList<>();
//                plane_info.info_plane(list_info);
                Admin admin = new Admin();

                if(admin.check()==0){
                     List l = (LinkedList)planeSystem.create_plane();
                     plane_info.info_plane(l);

                }else{
                    System.out.printf("管理员账号密码错误,请重试");
                }

            }else if(a == 2){
                menu_select();
                System.out.printf("请选择你要的查询方式");
                int b = in.nextInt();
                if(b == 1){
                    System.out.printf("请输入你要查询的航班");
                    String plane_numbers = in.next();
                    planeSystem.plane_numbers_show(plane_numbers);
                }else if(b == 2){
                    System.out.printf("请输入你要查询的起飞时间");
                    String start_times= in.next();
                    System.out.printf("请输入你要查询的降落时间");
                    String end_times = in.next();
                    planeSystem.plane_time_show(start_times, end_times);
                } else if(b == 3){
                    System.out.printf("请输入你要查询的起始地点");
                    String start_planes = in.next();
                    System.out.printf("请输入你要查询的起始地点");
                    String end_planes = in.next();
                    planeSystem.plane_show(start_planes, end_planes);
                }else if(b == 4){
                    System.out.printf("请输入你要查询的航班的票价");
                    double prince = in.nextDouble();
                    planeSystem.plane_prince_show(prince);
                } else if(b == 5){
                    System.out.printf("请输入你要查询的航班的满仓情况");
                    String flag = in.next();
                    planeSystem.plane_flag_show(flag);
                } else if(b == 6){
                    System.out.printf("请输入你要查询的航班的折扣");
                    double discount = in.nextDouble();
                    planeSystem.plane_discount_show(discount);
                } else if(b==7)
                planeSystem.show();

            }else if(a==3){
//                List<User> list_user_info = new LinkedList<>();
//                User_info.info_user(list_user_info);

                planeSystem.show();
                List l = (LinkedList)planeSystem.create_user();
                User_info.info_user(l);
                System.out.printf("订票成功！！\n");
                continue;

            }else if(a==4) {
                System.out.printf("请输入你订单号");
                String ordernumbers = in.next();
                List l = planeSystem.change_user_info(ordernumbers);
                User_info.info_user(l);
                System.out.printf("退票成功！！\n");

            } else if(a==5){
//                System.out.printf("请输入你要修改的航班所以信息");
                //创建基本信息
                System.out.printf("请输入你要修改的航班号\n");
                String number = in.next();
                System.out.printf("请输入你要修改的起点\n");
                String start_place = in.next();
                System.out.printf("请输入你要修改的终点\n");
                String end_place = in.next();
                System.out.printf("请输入你要修改的起飞时间\n");
                String start_time = in.next();
                System.out.printf("请输入你要修改的降落时间\n");
                String end_time = in.next();
                System.out.printf("请输入是否满仓\n");
                String flag = in.next();
                System.out.printf("请输入你要修改的价格\n");
                double price = in.nextDouble();
                System.out.printf("请输入你要修改的折扣数\n");
                double discount = in.nextDouble();

                List l = (LinkedList)planeSystem.change_plane_info(number, start_place, end_place, start_time, end_time, price, discount, flag);
                plane_info.info_plane(l);

            }else if(a==6) {
                Admin admin = new Admin();

                if(admin.check()==0){
                    planeSystem.show_user();
                }else{
                    System.out.printf("管理员账号密码错误,请重试");
                }

            }else{
                return ;
            }
        }
    }
}