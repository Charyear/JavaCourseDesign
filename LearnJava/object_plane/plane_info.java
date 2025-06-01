package object_plane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

public class plane_info {
    public static void WriteText(List<Plane> planes, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Plane plane : planes) {
                writer.write(plane.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void ReadText(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // 可以根据需要对每一行进行进一步处理
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void info_plane(List<Plane> planes) {
        File file = new File("E://学Java//object_plane//plane.txt");
        WriteText(planes, file);
        ReadText(file);
    }

    public LinkedList<Plane> list_plane;
}