import java.io.*;

public class Zad1 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis=new FileInputStream("src/File.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        String line;
        while((line= br.readLine()) !=null) {
            System.out.println(line);
        }
    }
}
