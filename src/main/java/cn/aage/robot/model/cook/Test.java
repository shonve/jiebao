package cn.aage.robot.model.cook;

import java.io.*;

/**
 * Created by shonve on 2016/10/22.
 */
public class Test {
    public static void main(String args[]) {
        try {
            String s = readFile("C:\\Users\\shonve\\Desktop\\IP_DATA\\IP_DATA.txt");
            System.out.println("00000000");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
//        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = "";
        while ((line = br.readLine()) != null) {

//操作
        }
        return "";
    }
}
