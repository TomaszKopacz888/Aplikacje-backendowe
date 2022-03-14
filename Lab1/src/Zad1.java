import java.io.*;

public class Zad1 {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis=new FileInputStream("src/File.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(fis));

        System.out.println(br);
    }
}
