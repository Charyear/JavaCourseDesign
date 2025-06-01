package object_plane;

import java.io.Serializable;

public class Plane implements Serializable {
        public String number;
        public String start_place;
        public String end_place;
        public String start_time;
        public String end_time;
        public double price;
        public double discount;
        String flag;//是否满仓 0为没有满仓
}