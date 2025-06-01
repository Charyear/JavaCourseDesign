import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.YearMonth;
import java.lang.Thread;
import java.text.SimpleDateFormat;
// 用户类
class User implements Serializable {
    private static final long serialVersionUID=1L;
    private String username;
    private String password;
    private String name;
    private String idNumber;
    private String phoneNumber;
    private int tnumber;//预定机票数量
    private List<String> flights;//添加航班
    private List<String> flightHistory; // 添加历史航班列表
    // 构造函数
    public User(String username, String password, String name, String idNumber, String phoneNumber, int tnumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.tnumber = tnumber;
        this.flights = new ArrayList<>();
        this.flightHistory = new ArrayList<>();
    }
    // 获取用户名
    public String getUsername() {
        return username;
    }
    // 获取密码
    public String getPassword() {
        return password;
    }
    // 获取姓名
    public String getName() {
        return name;
    }
    // 获取身份证号
    public String getIdNumber() {
        return idNumber;
    }
    // 获取手机号
    public String getPhoneNumber() {
        return phoneNumber;
    }
    // 获取航班列表
    public List<String> getFlights() {
        return flights;
    }
    // 获取历史航班列表
    public List<String> getFlightHistory() {
        return flightHistory;
    }
    // 获取机票数量
    public int getTnumber() {
        return tnumber;
    }
    // 修改机票数量
    public void setTnumber(int tnumber) {
        this.tnumber = tnumber;
    }
    // 修改用户名
    public void setName(String username) {
        this.username = username;
    }
    // 修改身份证号
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    // 修改手机号
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    // 添加航班
    public void addFlight(String flight) {
        flights.add(flight);
    }
    // 移除航班
    public void removeFlight(String flight) {
        flights.remove(flight);
    }
    // 添加历史航班
    public void addFlightHistory(String flight) {
        if (flightHistory == null) {
            flightHistory = new ArrayList<>();
        }
        flightHistory.add(flight);
    }
    // 移除历史航班
    public void removeFlightHistory(String flight) {
        flightHistory.remove(flight);
    }
}
// 航班类
class Flight implements Serializable {
	private static final long serialVersionUID=1L;
    private String flightNumber;
    private String departure;
    private String destination;
    private String date;
    private String time;
    private double price;
    private int ticketCount;
    // 构造函数
    public Flight(String flightNumber, String departure, String destination, String date, String time, double price, int ticketCount) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.ticketCount = ticketCount;//机票数量
    }
    // 获得FlightNumber值
    public String getFlightNumber() {
        return flightNumber;
    }
    // 获得Departure值
    public String getDeparture() {
        return departure;
    }
    // 获得Destination值
    public String getDestination() {
        return destination;
    }
    // 获得Date值
    public String getDate() {
        return date;
    }
    // 获得Time值
    public String getTime() {
        return time;
    }
    // 获得Price值
    public double getPrice() {
        return price;
    }
    // 获得TicketCount值
    public int getTicketCount() {
        return ticketCount;
    }
    // 重写setDeparture方法
    public void setDeparture(String departure) {
    	this.departure=departure;
    }
    // 重写setDestination方法
    public void setDestination(String destination) {
    	this.destination=destination;
    }
    // 重写setDate方法
    public void setDate(String date) {
    	this.date=date;
    }
    // 重写setTime方法
    public void setTime(String time) {
    	this.time=time;
    }
    // 重写setPrice方法
    public void setPrice(double price) {
    	this.price=price;
    }
    // 重写setTicketCount方法
    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
    // 重写toString方法
    public String toString() {
        return "航班号：" + flightNumber + "\n" +"行程："+ departure + " -> " + destination + "\n出发时间： " + date + "\n预计抵达时间： " + time + " \n花费: $" + price + " \n剩余机票数量: " + ticketCount;
    }
}
// 管理员类
class Admin {
    //用户名
    private String username;
    //密码
    private String password;
    // 构造函数
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // 获得用户名
    public String getUsername() {
        return username;
    }
    // 获得密码
    public String getPassword() {
        return password;
    }
}
// 主类
public class Mainn {
    private static final String USER_FILE = "User.json";//用户文件
    private static final String FLIGHT_FILE = "Flight.json";//航班文件
    private static final String FLIGHT_HISTORY_FILE = "flight_history.json";//历史航班文件
    private static final String ADMIN_USERNAME = "admin";//默认管理员用户名
    private static final String ADMIN_PASSWORD = "123";//默认管理员密码

    private static boolean shouldReturnToMainMenu;
    
    private static List<User> users;
    private static List<Flight> flights;
    private static List<Flight> flightHistory;
    private static Admin admin;
    // 主函数
    public static void main(String[] args) {
        users = readUserFile();//读取用户数据
        flights = readFlightFile();//读取航班数据
        flightHistory = readFlightHistory();//读取历史航班数据
        admin = new Admin(ADMIN_USERNAME, ADMIN_PASSWORD); //创建管理员对象

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Helper.clearScreen();
            System.out.println("*-欢迎来到爪哇航班-*");
            System.out.println("1. 新用户注册");
            System.out.println("2. 用户名");
            System.out.println("3. 管理员");
            System.out.println("4. 退出");
            System.out.println("_________________________");
            System.out.print("请输入您的选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    registerAccount(scanner);
                    break;
                case 2:
                    User user = userLogin(scanner);

                    if (user != null) {
                        userMenu(scanner, user);
                    }
                    break;
                case 3:
                    if (adminLogin(scanner)) {
                        adminMenu(scanner);
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("错误的选择，请重试！");
                    break;
            }
        }

        writeUserFile(users);//写入用户数据
        writeFlightFile(flights);//写入航班数据
        writeFlightHistory(flightHistory);//写入历史航班数据
        System.out.println("感谢您使用爪哇航班!");
    }
    // 新用户注册
    private static void registerAccount(Scanner scanner) {
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();

        System.out.println("注册新的用户");

        System.out.print("请输入您的用户名: ");
        String username = scanner.nextLine();
        if (isUsernameTaken(username)) {
            System.out.println("用户名已经存在，请重新输入.");
            return;
        }

        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        System.out.print("请再次输入密码: ");
        String confirmPassword = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("您输入的两次密码不一致，请重试！");
            return;
        }

        System.out.print("输入您的真实姓名: ");
        String name = scanner.nextLine();
        
        int sz=0;
        String idNumber=null;
        System.out.print("输入您的身份证号码: ");
        for(int i=0;i<3;i++)
        {
            idNumber = scanner.nextLine();
            sz=checkID(idNumber);
            if(sz==1)
            {
            	break;
            }
            else {
                if(i!=2)
                {
                    System.out.println("请重新输入合理的身份证号！");
                }
            }
        }
        if(sz==1) 
        {
            String phoneNumber=null;
            int zs=0;
            System.out.print("输入您的手机号码: ");
            for(int i=0;i<3;i++)
            {
                phoneNumber = scanner.nextLine();
                zs=checkPhoneNumber(phoneNumber);
                if(zs==1)
                {
            	    break;
                }
                else {
                    if(i!=2)
                    {
                        System.out.println("请重新输入合理的手机号码！");
                    }
                }
            }
            if(zs==1)
            {
                int tnumber=0;
                User user = new User(username, password, name, idNumber, phoneNumber,tnumber);//创建用户对象
                users.add(user);
                writeUserFile(users);
                System.out.println("您的账号注册成功!");
            }
            else{
                System.out.println("手机号码输入错误次数过多，新用户注册失败！");
                return;
            }
        }else {
    	    System.out.println("身份证号输入错误次数过多，新用户注册失败！");
            return;
        }

    }
    // 用户登录
    private static User userLogin(Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        System.out.println("*-用户登录-*");

        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();

        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("登录成功!");
                return user;
            }
        }

        System.out.println("用户名或密码错误");
        return null;
    }
    // 管理员登录
    private static boolean adminLogin(Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        System.out.println("*-管理员登录-*");

        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();

        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println("登录成功!");
            return true;
        }

        System.out.println("用户名或密码错误");
        return false;
    }
    // 用户菜单
    private static void userMenu(Scanner scanner, User user) {
        boolean loggedIn = true;
        
        while (loggedIn) {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Helper.clearScreen();
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");//格式化时间
            String timeStr = format.format(new Date());//获取当前时间
            int hour = Integer.parseInt(format.format(now).substring(12, 14).trim());//获取当前时间的小时

            String timeOfDay;
            if (hour >= 6 && hour <= 11) {
                timeOfDay = "早上好！";
            } else if (hour >= 12 && hour <= 17) {
                timeOfDay = "下午好！";
            } else {
                timeOfDay = "晚上好！";
            }
            char digit = user.getIdNumber().charAt(user.getIdNumber().length() - 2);//获取身份证号码的最后一位
            int secondLastDigit = Character.getNumericValue(digit);//获取身份证号码的最后一位的数字
            if(secondLastDigit%2!=0){
                System.out.println("当前时间：" + timeStr);
                System.out.println(timeOfDay+" 尊敬的"+user.getName()+"先生");
            }
            else{
                System.out.println("当前时间：" + timeStr);
                System.out.println(timeOfDay+" 尊敬的"+user.getName()+"女士");
            }
            System.out.println("1. 查看当前航班");
            System.out.println("2. 我的航班");
            System.out.println("3. 推荐航班");
            System.out.println("4. 查看历史航班");
            System.out.println("5. 更改用户个人信息");
            System.out.println("6. 注销用户");
            System.out.println("7. 返回主界面");
            System.out.println("_________________________");
            System.out.print("请输入您的选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    viewCurrentFlights(user,scanner);
                    break;
                case 2:
                    myFlights(user,scanner);
                    break;
                case 3:
                    recommendedFlights();
                    break;
                case 4:
                    viewHistoryFlights(user,scanner);
                    break;
                case 5:
                    updatePersonalInformation(scanner, user);
                    break;
                case 6:
                    unregisterAccount(scanner, user);
                    // 如果用户选择注销账户，则直接回到主界面
                    if (shouldReturnToMainMenu) {
                        return;
                    } else {
                        break;
                    }
                case 7:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("错误的选择，请重试！");
                    break;
            }
        }
    }
    // 显示当前航班
    private static void viewCurrentFlights(User user, Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        boolean OUT = false;
        Date now = new Date();//创建时间对象
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");//格式化时间
        String timeStr = format.format(now);//获取当前时间
        System.out.println("当前时间：" + timeStr);
        System.out.println("           *所有航班*            ");
        System.out.println("---------------------------------");
        for (Flight flight : flights) {
            OUT = true;
            System.out.println(flight);
            System.out.println("---------------------------------");
        }
        if (!OUT) {
            System.out.println("暂无航班");
            System.out.println("---------------------------------");
        }
        System.out.println("请选择您要的操作：");
        System.out.println("1. 根据航班号来预定航班");
        System.out.println("2. 根据目的地与出发地来预定航班");
        System.out.println("3. 根据出发时间来预定航班");
        System.out.println("0. 返回主菜单");
        int choice = Integer.parseInt(scanner.nextLine().trim());
        switch (choice) {
            case 1:
                System.out.println("请输入您要查找的航班号:");
                String flightNumber = scanner.nextLine().trim();
                if (flightNumber.isEmpty()) {
                    System.out.println("无效的航班号，请重新输入！");
                    viewCurrentFlights(user, scanner); // 输入无效航班号，重新进入航班列表
                }
                for (Flight flight : flights) {
                    if (flight.getFlightNumber().equals(flightNumber)) {
                        if (flight.getTicketCount() > 0) {
                            //展示航班
                            System.out.println("航班信息：" + flight);
                            user.addFlight(flight.getFlightNumber());
                            flight.setTicketCount(flight.getTicketCount() - 1);//减少航班机票数
                            user.setTnumber(user.getTnumber()+1);//增加航班预定数
                            System.out.println("航班预定成功！");
                            System.out.println("--爪哇航班祝您一路顺风！");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return;
                        } else {
                            System.out.println("该航班机票已售罄，无法预定！");
                            return;
                        }
                    }
                }
                System.out.println("无法找到您所需要的航班！");
                viewCurrentFlights(user, scanner); // 未找到对应航班，重新进入航班列表
                break;
            case 2:
                System.out.println("请输入您要查找的目的地：");
                String destination = scanner.nextLine().trim();
                System.out.println("请输入您要查找的出发地：");
                String departure = scanner.nextLine().trim();
                if (destination.isEmpty() || departure.isEmpty()) {
                    System.out.println("目的地和出发地不能为空！");
                    viewCurrentFlights(user, scanner); // 输入为空，重新进入航班列表
                }
                ArrayList<Flight> filteredFlights = new ArrayList<>();
                for (Flight flight : flights) {
                    if (flight.getDestination().equals(destination) && flight.getDeparture().equals(departure)) {
                        filteredFlights.add(flight);
                    }
                }
                if (filteredFlights.size() == 0) {
                    System.out.println("无法找到您所需要的航班！");
                    viewCurrentFlights(user, scanner); // 找不到对应航班，重新进入航班列表
                }
                boolean found = false;
                int invalidCount = 0;
                while (!found) {
                    System.out.println("找到以下航班：");
                    for (int i = 0; i < filteredFlights.size(); i++) {
                        System.out.println((i + 1) + ". " + filteredFlights.get(i));
                    }
                    System.out.println("请选择您想要预订的航班（输入序号，按0退出）：");
                    int index = Integer.parseInt(scanner.nextLine().trim());
                    if(index == 0){
                        return;
                    }
                    if (index < 1 || index > filteredFlights.size()) {
                        System.out.println("输入无效，请重新输入！");
                        invalidCount++;
                        if (invalidCount == 3) {
                            System.out.println("您的输入有误！！");
                            break;
                        }
                        continue;
                    }
                    Flight selectedFlight = filteredFlights.get(index - 1);//获取选择的航班
                    if (selectedFlight.getTicketCount() > 0) {
                        user.addFlight(selectedFlight.getFlightNumber());
                        selectedFlight.setTicketCount(selectedFlight.getTicketCount() - 1);//减少航班机票数
                        user.setTnumber(user.getTnumber()+1);
                        System.out.println("航班预定成功！");
                        System.out.println("--爪哇航班祝您一路顺风！");
                        return;
                    } else {
                        System.out.println("该航班机票已售罄，无法预定！");
                        return;
                    }
                }
                break;
            case 3:
                System.out.println("请输入您要查找的出发时间(格式：yyyy-MM-dd HH:mm)");
                String departureTime = scanner.nextLine().trim();
                if (departureTime.isEmpty()) {
                    System.out.println("出发时间不能为空！");
                    viewCurrentFlights(user, scanner); // 输入为空，重新进入航班列表
                }
                ArrayList<Flight> filteredByTimeFlights = new ArrayList<>();
                for (Flight flight : flights) {
                    if (flight.getDate().equals(departureTime)) {
                        filteredByTimeFlights.add(flight);//添加航班
                    }
                }
                if (filteredByTimeFlights.size() == 0) {
                    System.out.println("无法找到您所需要的航班！");
                    viewCurrentFlights(user, scanner); // 找不到对应航班，重新进入航班列表
                }
                boolean foundByTime = false;
                int invalidInputCount = 0;
                while (!foundByTime) {
                    System.out.println("找到以下航班：");
                    for (int i = 0; i < filteredByTimeFlights.size(); i++) {
                        System.out.println((i + 1) + ". " + filteredByTimeFlights.get(i));
                    }
                    System.out.println("请选择您想要预订的航班（输入序号,按0退出）");
                    int index = Integer.parseInt(scanner.nextLine().trim());
                    if(index == 0){
                        return;
                    }
                    else if (index < 1 || index > filteredByTimeFlights.size()) {
                        System.out.println("输入无效，请重新输入！");
                        invalidInputCount++;
                        if (invalidInputCount == 3) {
                            System.out.println("您的输入有误！！");
                            break;
                        }
                        continue;
                    }
                    Flight selectedFlight = filteredByTimeFlights.get(index - 1);//获取选择的航班
                    if (selectedFlight.getTicketCount() > 0) {
                        user.addFlight(selectedFlight.getFlightNumber());//添加航班
                        selectedFlight.setTicketCount(selectedFlight.getTicketCount() - 1);//减少航班机票数
                        user.setTnumber(user.getTnumber()+1);//增加航班预订数
                        System.out.println("航班预定成功！");
                        System.out.println("--爪哇航班祝您一路顺风！");
                        return;
                    } else {
                        System.out.println("该航班机票已售罄，无法预定！");
                        return;
                    }
                }
                break;
            case 0:
                return;
            default:
                System.out.println("无效的选择，请重新选择！");
                viewCurrentFlights(user, scanner);//输入无效，重新进入航班列表
                break;
        }
    }    
    // 我的航班
    private static void myFlights(User user, Scanner scanner) { 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
    	List<String> userFlights = user.getFlights(); // 获取用户航班列表
    	System.out.println("---------------------------------"); 
    	if (userFlights.isEmpty()) { 
    		System.out.println("姓名："+user.getName()); 
    		System.out.println("联系方式："+user.getPhoneNumber()); 
    		System.out.println("身份证号："+user.getIdNumber()); 
    		System.out.println("您暂无行程安排."); 
    	} else { 
    		System.out.println("姓名："+user.getName()); 
    		System.out.println("联系方式："+user.getPhoneNumber()); 
    		System.out.println("身份证号："+user.getIdNumber()); 
    		System.out.println("--我的行程--");
            double price = 0.0; 
    		for (String flightNumber : userFlights) { 
    			Flight flight = findFlightByNumber(flightNumber); 
    			System.out.println(flight);
                price = flight.getPrice()+price; 
    		} 
            System.err.println("共"+user.getTnumber()+"张机票，"+"共花费：$"+price);
    	} 
    	System.out.println("---------------------------------"); 
    	System.out.println("1、更改航班。"); 
    	System.out.println("2、取消航班。"); 
    	System.out.println("3、返回。");
    	System.out.print("请输入您的选择："); 
    	int choice = scanner.nextInt(); 
    	scanner.nextLine(); // Consume newline character 
    	if(choice==1) { 
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            String timeStr = format.format(now);
            System.out.println("当前时间：" + timeStr);
    		System.out.print("请输入您原来的航班号："); 
    		String oldFlightNumber = scanner.nextLine().trim(); // Consume newline character
    		System.out.println("---------------------------------");
            boolean oldFlightFound = false; 
    		for (String flightNumber : userFlights) { 
    			if (flightNumber.equals(oldFlightNumber)) { 
    				oldFlightFound = true; 
    				System.out.print("请输入您要修改的航班号："); 
    				String newFlightNumber = scanner.nextLine().trim(); // Consume newline character
    				boolean newFlightFound = false; 
    				for (Flight flight : flights) { 
    					if (flight.getFlightNumber().equals(newFlightNumber)) { 
    						newFlightFound = true; 
                            user.removeFlight(oldFlightNumber); 
    						flight.setTicketCount(flight.getTicketCount() + 1);//增加航班机票数
                            user.setTnumber(user.getTnumber()-1);//减少航班预订数
    						user.addFlight(newFlightNumber); 
    						flight.setTicketCount(flight.getTicketCount() - 1);//减少航班机票数
                            user.setTnumber(user.getTnumber()+1);//增加航班预订数
    						System.out.println("航班修改成功!"); 
    						System.out.println("--爪哇航班祝您一路顺风!"); 
    						break;
    					} 
    				} 
    				if (!newFlightFound) { 
    					System.out.println("无效的选择，请重新选择！"); 
    				} 
    				break; 
    			} 
    		} 
    		if (!oldFlightFound) { 
    			System.out.println("无效的选择，请重新选择！");
    		} 
    	}
    	else if (choice == 2) {
            System.out.println("请输入您要取消的航班号：");
            String flightNumber = scanner.nextLine().trim();// Consume newline character
            boolean flightFound = false;
            for (String userFlightNumber : userFlights) {
                if (userFlightNumber.equals(flightNumber)) {
                    flightFound = true;
                    Flight flight = findFlightByNumber(flightNumber);//查找航班
                    if (flight == null) {
                        System.out.println("找不到航班，请重新选择！");
                        break;
                    }
                    user.removeFlight(flightNumber);
                    flight.setTicketCount(flight.getTicketCount() + 1);//增加航班机票数
                    user.setTnumber(user.getTnumber() - 1);//减少航班预订数
                    System.out.println("航班取消成功!");
        
                    // 读取历史航班列表并添加取消的航班
                    List<Flight> flightHistory = readFlightHistory();
                    flightHistory.add(flight);
        
                    // 更新用户的历史航班记录
                    user.addFlightHistory(flightNumber);
        
                    // 写入更新后的历史航班列表
                    writeFlightHistory(flightHistory);
                    break;
                }
            }
            if (!flightFound) {
                System.out.println("无效的选择，请重新选择！");
            }
        }
    	else {
    		return;
    	}
    }
    //查看历史航班
    private static void viewHistoryFlights(User user, Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        List<String> flightHistory = user.getFlightHistory();//获取历史航班列表
        System.out.println("姓名："+user.getName()); 
    	System.out.println("联系方式："+user.getPhoneNumber()); 
    	System.out.println("身份证号："+user.getIdNumber());
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String timeStr = format.format(now);//获取当前时间
        System.out.println("当前时间：" + timeStr);
        // 检查历史航班列表是否为空
        if (flightHistory.isEmpty()) {
            System.out.println("_________________________");
            System.out.println("您还没有历史航班记录。");
            System.out.println("_________________________");
        } else {
            boolean flightFound = false;
            System.out.println("您的历史航班记录如下：");
            System.out.println("_________________________");
            for (String flightNumber : flightHistory) {
                Flight flight = findFlightByNumber(flightNumber);
                if (flight != null) {
                    flightFound = true;
                    System.out.println(flight);
                    System.out.println("_________________________");
                }
            }
            if (!flightFound) {
                System.out.println("暂无历史航班记录");
                System.out.println("_________________________");
            }
        }
        // 用户选择取消航班
        System.out.println("请选择操作：");
        System.out.println("1. 删除历史航班");
        System.out.println("2. 返回");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 消费换行符
        switch (choice) {
            case 1:
                System.out.print("请输入要删除的航班号: ");
                String flightNumber = scanner.nextLine().trim();
                user.removeFlightHistory(flightNumber);
                System.out.println("历史记录删除成功!");
                break;
            case 2:
                return;
            default:
                System.out.println("错误的选择，请重试！");
                break;
        }
    }
    // 推荐航班
    private static void recommendedFlights() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        // 创建一个 Date 对象，表示当前时间
        Date now = new Date();
        // 创建一个 SimpleDateFormat 对象，指定时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        // 将 Date 对象格式化为指定格式的字符串
        String timeStr = format.format(now);
        // 输出当前时间的字符串表示形式
        System.out.println("    -*推荐航班*-    ");
        System.out.println("当前时间：" + timeStr);
        List<Flight> sortedFlights = new ArrayList<>(flights);
        sortedFlights.sort(Comparator.comparingDouble(Flight::getPrice)
                .thenComparing(Flight::getDate)
                .thenComparing(Flight::getTime));
        System.out.println("---------------------------------");
        for (Flight flight : sortedFlights) {
            System.out.println(flight);
        }
        System.out.println("---------------------------------");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 更新用户个人信息
    private static void updatePersonalInformation(Scanner scanner, User user) { 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        System.out.println("    *更改用户个人信息*    ");
        String id = user.getIdNumber();
        char digit = id.charAt(id.length() - 2);
        int secondLastDigit = Character.getNumericValue(digit);
        if(secondLastDigit%2!=0){
            System.out.println("尊敬的"+user.getName()+"先生您好！");
        }
        else{
            System.out.println("尊敬的"+user.getName()+"女士您好！");
        }
    	System.out.println("您绑定的联系方式："+user.getPhoneNumber()); 
    	System.out.println("您绑定的身份证号："+user.getIdNumber());
        System.out.println("---请输入您的选择---"); 
    	System.out.println("1.修改实名信息");
        System.out.println("2.更改手机号码");
        System.out.println("3.返回");
        int choice = scanner.nextInt(); 
    	scanner.nextLine(); // Consume newline character 
        if(choice==1){
            System.out.print("输入新的姓名: ");
    	    String name = scanner.nextLine();
    	    user.setName(name);

    	    String idNumber = null;
    	    int sz = 0;
    	    System.out.print("输入新的身份证号: ");
    	    for (int i = 0; i < 3; i++) {
    		    idNumber = scanner.nextLine();
    		    sz = checkID(idNumber);
    		    if (sz == 1) {
    			    break;
    		    } else {
    			    System.out.println("请重新输入合理的身份证号！");
    		    }
    	    }
            if(sz==1){
                user.setIdNumber(idNumber);
                writeUserFile(users);
                System.out.println("个人信息更新成功!");
                return;
            }else {
    		    System.out.println("身份证号输入错误次数过多，个人信息更新失败！");
                return;
    	    }
        }
    	else if (choice == 2) {
    		String phoneNumber=null;
            int zs=0;
            System.out.print("输入您新的手机号码: ");
            for(int i=0;i<3;i++)
            {
                phoneNumber = scanner.nextLine();
                zs=checkPhoneNumber(phoneNumber);
                if(zs==1)
                {
            	    break;
                }
                else {
                    if(i!=2)
                    {
                        System.out.println("请重新输入合理的手机号码！");
                    }
                }
            }
            if(zs==1)
            {
                user.setPhoneNumber(phoneNumber);
    		    writeUserFile(users);
    		    System.out.println("个人信息更新成功!");
                return;
            }
            else{
                System.out.println("手机号码输入错误次数过多，个人信息更新失败！");
                return;
            }
    	} 
        else {
            return;
    	}
    }
   // 用户注销账号
   private static void unregisterAccount(Scanner scanner, User user) {
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("    *用户注销*    ");
        System.out.println("用户名: " + user.getUsername());
        System.out.println("姓名: " + user.getName());
        System.out.println("手机号码: " + user.getPhoneNumber());
        System.out.println("身份证号码: " + user.getIdNumber());
        System.out.println("航班信息: " + user.getFlights());
        System.out.println("_________________________");
        System.out.println("您确定要注销您的账号吗？");
        System.out.println("注销后，您的账号将无法使用，请谨慎操作！");
        System.out.println("航班删除后将无法恢复，确认删除吗？(y/n)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")||confirm.equalsIgnoreCase("Y")) {
            users.remove(user);// 从用户列表中删除该用户
            writeUserFile(users);// 写入用户列表
            System.out.println("您的账号已成功注销!");
            shouldReturnToMainMenu = true; // 设置标志变量为true，表示应该返回主菜单
        } else {
            System.out.println("操作取消.");
            shouldReturnToMainMenu = false; // 设置标志变量为false，表示不返回主菜单，继续执行后续代码
        }
    }
    // 管理员菜单
    private static void adminMenu(Scanner scanner) {
        boolean loggedIn = true;

        while (loggedIn) {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Helper.clearScreen();
            System.out.println("*管理员菜单*");
            System.out.println("1. 添加新的航班");
            System.out.println("2. 修改航班");
            System.out.println("3. 删除航班");
            System.out.println("4. 查看所有用户");
            System.out.println("5. 返回主菜单");
            System.out.println("________________________");
            System.out.print("请输入您的选择: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addFlight(scanner);
                    break;
                case 2:
                    modifyFlight(scanner);
                    break;
                case 3:
                    deleteFlight(scanner);
                    break;
                case 4:
                    viewAllUsers(scanner);
                    break;
                case 5:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("错误的选择，请重试！");
                    break;
            }
        }
    }
    // 添加航班
    private static void addFlight(Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        // 创建一个 Date 对象，表示当前时间
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String timeStr = format.format(now);
        System.out.println("    *添加新的航班*    ");
        System.out.println("当前时间：" + timeStr);
        System.out.println("---------------------------------");
        System.out.print("输入航班号(返回请输入n/N): ");
        String flightNumber = scanner.nextLine();
        if(flightNumber.equals("n") || flightNumber.equals("N"))
        {
            return;
        }
        System.out.print("输入出发地: ");
        String departure = scanner.nextLine();

        System.out.print("输入目的地: ");
        String destination = scanner.nextLine();
    
        System.out.println("(格式：yyyy-MM-dd HH:mm)");
        System.out.print("输入预计出发时间: ");
        String date = scanner.nextLine();

        // 使用正则表达式检查时间格式
        String pattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
        if (!Pattern.matches(pattern, date)) {
            System.out.println("时间格式错误");
            return;
        }
    
        System.out.print("输入预计抵达时间: ");
        String time = scanner.nextLine();

        // 使用正则表达式检查时间格式
        String patern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
        if (!Pattern.matches(patern, time)) {
            System.out.println("时间格式错误");
            return;
        }

        double price;
        while (true) {
            try {
                System.out.print("输入机票价格: ");
                price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                break;
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入价格");
                scanner.nextLine(); // Consume invalid input
            }
        }
    
        int ticketCount;
        while (true) {
            try {
                System.out.print("输入机票数量: ");
                ticketCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                break;
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入数量");
                scanner.nextLine(); // Consume invalid input
            }
        }
    
        System.out.println("---------------------------------");
        Flight flight = new Flight(flightNumber, departure, destination, date, time, price, ticketCount);// 创建航班对象
        flights.add(flight);// 添加航班对象到航班列表
        writeFlightFile(flights);// 写入航班列表到文件

        System.out.println("新的航班添加成功!");
    }
    // 修改航班
    private static void modifyFlight(Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        boolean OUT = false;
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String timeStr = format.format(now);// 当前时间
        System.out.println("    *修改航班*    ");
        System.out.println("当前时间：" + timeStr);
        System.out.println("---------------当前航班--------------");
        for (Flight flight : flights) {
            OUT = true;
            System.out.println(flight);
            System.out.println("---------------------------------");
        }
        if (!OUT) {
            System.out.println("暂无航班");
            System.out.println("---------------------------------");
        }
        System.out.print("请输入要修改的航班号: ");
        String flightNumber = scanner.nextLine();

        Flight flight = findFlightByNumber(flightNumber);// 查找航班

        if (flight == null) {
            System.out.println("未找到相应航班.");
            return;
        }
        System.out.println("---------------------------------");
        System.out.println("Flight details: " + flight);
        System.out.println("---------------------------------");
        System.out.print("输入新的出发地: ");
        String departure = scanner.nextLine();
        flight.setDeparture(departure);

        System.out.print("输入新的目的地: ");
        String destination = scanner.nextLine();
        flight.setDestination(destination);

        System.out.println("(格式：yyyy-MM-dd HH:mm)");
        System.out.print("输入新的预计出发时间: ");
        String date = scanner.nextLine();
        // 使用正则表达式检查时间格式
        String pattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
        if (!Pattern.matches(pattern, date)) {
            System.out.println("时间格式错误");
            return;
        }
        flight.setDate(date);

        System.out.print("输入新的预计抵达时间: ");
        String time = scanner.nextLine();
        // 使用正则表达式检查时间格式
        String patern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
        if (!Pattern.matches(patern, time)) {
            System.out.println("时间格式错误");
            return;
        }
        flight.setTime(time);

        double price;
        while (true) {
            try {
                System.out.print("输入新的机票价格: ");
                price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                break;
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入价格");
                scanner.nextLine(); // Consume invalid input
            }
        }
        flight.setPrice(price);

        int ticketCount;
        while (true) {
            try {
                System.out.print("输入新的机票数量: ");
                ticketCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                break;
            } catch (InputMismatchException e) {
                System.out.println("输入错误，请重新输入数量");
                scanner.nextLine(); // Consume invalid input
            }
        }
        flight.setTicketCount(ticketCount);

        writeFlightFile(flights);

        System.out.println("航班修改成功!");
    }
    // 删除航班
    private static void deleteFlight(Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        boolean OUT = false;
        System.out.println("    *删除航班*    ");

        System.out.println("---------------当前航班--------------");
        for (Flight flight : flights) {
            OUT = true;
            System.out.println(flight);
            System.out.println("---------------------------------");
        }
        if (!OUT) {
            System.out.println("暂无航班");
            System.out.println("---------------------------------");
        }

        System.out.print("请输入要删除的航班号: ");
        String flightNumber = scanner.nextLine();

        Flight flight = findFlightByNumber(flightNumber);

        if (flight == null) {
            System.out.println("未找到相应航班.");
            return;
        }
        System.out.println("---------------------------------");
        System.out.println("航班详情: " + flight);
        System.out.println("---------------------------------");
        System.out.println("航班删除后将无法恢复，确认删除吗？(y/n)");
        if (!scanner.nextLine().equals("y")||!scanner.nextLine().equals("Y")) {
            return;
        }
        flights.remove(flight);// 删除航班
        writeFlightFile(flights);// 写入航班文件
        System.out.println("航班删除成功!");
    }
    // 显示所有用户的信息
    private static void viewAllUsers(Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Helper.clearScreen();
        System.out.println("             所有用户            ");
        System.err.println("---------------------------------");
        for (User user : users) {
            System.out.println("姓名："+user.getUsername());
            System.out.println("联系方式："+user.getPhoneNumber());
            System.out.println("身份证号码："+user.getIdNumber());
            List<String> userFlights = user.getFlights();// 获取用户的航班
            if (userFlights.isEmpty()) {
                System.out.println("暂无行程安排。");
            } else {
                System.out.println("本次行程:");
                for (String flightNumber : userFlights) {
                    Flight flight = findFlightByNumber(flightNumber);
                    System.out.println(flight);
                    System.err.println("共"+user.getTnumber()+"张机票，"+"共花费：$"+flight.getPrice());
                }
            }
            System.err.println("---------------------------------");
        }
    
        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1. 查找用户");
            System.out.println("2. 返回上一级菜单");
        
            String option = scanner.nextLine();
        
            switch (option) {
                case "1":
                    System.out.print("请输入要查找的用户名: ");
                    String inputUsername = scanner.nextLine();
                    boolean foundUser = false;
                    for (User user : users) {
                        if (user.getUsername().equals(inputUsername)) {
                            Helper.clearScreen();
                            System.out.println("找到用户：" + user.getUsername());
                            List<String> userFlights = user.getFlights();
                            if (userFlights.isEmpty()) {
                                System.out.println("暂无行程安排。");
                            } else {
                                System.out.println("本次行程:");
                                for (String flightNumber : userFlights) {
                                    Flight flight = findFlightByNumber(flightNumber);
                                    System.out.println(flight);
                                    System.err.println("共"+user.getTnumber()+"张机票，"+"共花费：$"+flight.getPrice()*user.getTnumber());
                                }
                            }
                            foundUser = true;
                            break;
                        }
                    }
                    if (!foundUser) {
                        System.out.println("未找到用户：" + inputUsername);
                    }
                    break;
                
                case "2":
                    return; // 返回上一级菜单
        
                default:
                    System.out.println("无效的选项");
                    break;
            }
        }
    }
    // 用户信息的检查，判断给定的用户名是否已经被占用
    private static boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    // 航班信息的查找
    private static Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
    // 用户信息的读取
    @SuppressWarnings("unchecked") 
    private static List<User> readUserFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();// 用户列表不存在时返回空列表
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("用户列表读取失败");
            // handle exception
        }
        return new ArrayList<>();
    }
    // 用户信息的保存与写入
    private static void writeUserFile(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);// 保存用户列表
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("用户列表保存失败");
            // handle exception
        }
    }
    // 航班信息的读取
    @SuppressWarnings("unchecked") 
    private static List<Flight> readFlightFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FLIGHT_FILE))) {
            return (List<Flight>) ois.readObject();// 读取航班列表
        } catch (FileNotFoundException e) {
            return new ArrayList<>();// 航班列表不存在时返回空列表
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("航班列表读取失败");
            // handle exception
        }
        return new ArrayList<>();
    }
    // 航班信息的保存与写入
    private static void writeFlightFile(List<Flight> flights) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FLIGHT_FILE))) {
            oos.writeObject(flights);// 保存航班列表
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("航班列表保存失败");
            // handle exception
        }
    }
    //历史航班数据读取
    @SuppressWarnings("unchecked")
    private static List<Flight> readFlightHistory() {
        try {
            FileInputStream fis = new FileInputStream(FLIGHT_HISTORY_FILE);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (List<Flight>) ois.readObject();// 读取历史航班数据
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();// 历史航班数据不存在时返回空列表
        }
    }
    //历史航班数据保存与写入
    private static void writeFlightHistory(List<Flight> flightHistory) {
        try {
            FileOutputStream fos = new FileOutputStream(FLIGHT_HISTORY_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);// 保存历史航班数据
            oos.writeObject(flightHistory);// 保存历史航班数据
            oos.close();// 关闭流
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 身份证号码的检查
    public static int checkID(String id) {
        //a)必须是18位
        int lc = lengthCheck(id);
        if (lc < 0)  return lc;
 
        //b) 前面位数必须是数字，最后一位可以是数字或小写字母；
        int dic = digitCheck(id);
        if (dic < 0)  return dic;
        
        //c) 日期是存在的。
        int dac = dateCheck(id);
        if (dac <0) return dac;
            
        //d)最后一位校验码检查。
        int ldc = lastDigitCheck(id);
        if (ldc < 0) return ldc;
 
        //所有校验通过后，返回1
        return 1;
        
    }
    // 判断身份证号码的长度是否为18
    public static int lengthCheck(String id) {
        //必须是18位
        if (id.length()!=18) {
            System.out.println("输入错误，请输入18位身份证号码！");
            return -1;
        }
        return 0;
    }
    // 判断身份证号码的前17位是否是数字
    public static int digitCheck(String id) {
        //前面位数必须是数字，最后一位可以是数字或小写字母；
        int i,n=0;
        for( i=0;i<17;i++)
        {
            if(id.charAt(i)>='0'&&id.charAt(i)<='9')
            {
                n++;
            }
            else
                break;
        }
    
        if(n<=16||!((id.charAt(17)>='0'&& id.charAt(17)<='9')||(id.charAt(17)>='A'&& id.charAt(17)<='z'))) {
            System.out.println("输入错误：前面位数必须是数字，最后一位可以是数字或小写字母！");
            return -2;
        }
        return 0;
    }
    // 判断身份证号码的日期是否存在
    public static int dateCheck(String id) {
        int year, month, day;
        year = Integer.valueOf(id.substring(6, 10));
        month = Integer.valueOf(id.substring(10, 12));
        day = Integer.valueOf(id.substring(12, 14));
        
        // 使用YearMonth类来验证月份的合理范围
        if (year < 1900 || year > 2023) {
            System.out.println("输入错误：无效的出生日期！");
            return -3;
        }
        else if (month < 1 || month > 12) {
            System.out.println("输入错误：无效的出生日期！");
            return -3;
        }
        
        // 使用YearMonth类来验证天数的合理范围
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        if (day < 1 || day > daysInMonth) {
            System.out.println("输入错误：无效的出生日期！");
            return -3;
        }
        
        // 使用LocalDate类来验证日期的合理范围
        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        } catch (Exception e) {
            System.out.println("输入错误：无效的出生日期！");
            return -3;
        }
        
        // 打印合理的出生日期
        int sex=Integer.valueOf(id.charAt(16));
        if(sex%2==0){
            System.out.println("您好女士！您的出生日期是：" + date);
        }else{
            System.out.println("您好先生！您的出生日期是：" + date);
        }
        return 0;
    }
    // 校验身份证号码最后一位    
    public static int lastDigitCheck(String id) {
        // 系数数组定义
        int[] factors = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // 求和
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (id.charAt(i) - '0') * factors[i];
        }
        // 取余
        int remainder = sum % 11;
        // 余数映射数组定义
        char[] mappedDigits = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        // 生成最后一位（校验位）
        char lastDigit = mappedDigits[remainder];
        // 判断输入的最后一位是否符合校验规则
        if(id.charAt(17)=='x'){
            if ('X' != lastDigit) {
                System.out.println("输入错误：最后一位不符合校验规则");
                return -4;
            }else{
                return 0;
            }
        }else{
            if (id.charAt(17) != lastDigit) {
                System.out.println("输入错误：最后一位不符合校验规则");
                return -4;
            }else{
                return 0;
            }
        }
    }
    // 手机号码验证
    public static int checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.length()!=11) {
            System.out.println("输入错误,请输入11位手机号码！");
            return -1;
        }
        int i,n=0;
        for( i=0;i<11;i++)
        {
            if(phoneNumber.charAt(i)>='0'&&phoneNumber.charAt(i)<='9')
            {
                n++;
            }
            else
                break;
        }
        if(n!=11)
        {
            System.out.println("请输入合法的手机号。");
            return -1;
        }
        int ph = Integer.parseInt(phoneNumber.substring(0, 3));
        int fr = Integer.parseInt(phoneNumber.substring(0, 1));

        if (fr == 1) 
        {
            if ((ph >= 130 && ph <= 141) || (ph >= 144 && ph <= 159) || (ph >= 170 && ph <= 178) || (ph >= 162 && ph <= 167) || (ph >= 180 && ph <= 199)) 
            {
                return 1;
            } else {
                System.out.println("请输入合法的手机号，包括合理的运营商区码。");
                return -1;
            }
        } else {
            System.out.println("请输入合法的手机号。");
            return -1;
        }
    }

}
//清空屏幕。
class Helper {
        public static void clearScreen() {
        try {
            //获取操作系统名称，判断操作系统是否为Windows系统。
            if (System.getProperty("os.name").contains("Windows")) {
                //Windows系统，使用new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()命令来清空屏幕。其中，"cmd"是命令行工具，"/c"表示执行命令后继续进入命令行，"cls"是清空屏幕的命令。
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                //其它系统，则使用new ProcessBuilder("clear").inheritIO().start().waitFor()命令来清空屏幕。其中，"clear"是清空屏幕的命令
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}