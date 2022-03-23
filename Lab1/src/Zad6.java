import java.io.*;

public class Zad6 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis=new FileInputStream("src/File.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        String line;
        int i=1;
        while((line= br.readLine()) !=null) {
            System.out.println(i+" "+line);
            i++;
        }
    }
}