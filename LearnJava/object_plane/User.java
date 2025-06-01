package object_plane;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String idcard;
    public int sum = 1;//订票数量
    public String number_user;
    public String ordernumber; //订单号
}