import java.io.*;

public class Zad2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/File.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        String str = br.readLine();
       bw.write(str);
       bw.close();

    }

}