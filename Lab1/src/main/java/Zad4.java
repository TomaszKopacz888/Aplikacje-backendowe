
import java.util.zip.CRC32;

public class Zad4 {
    public static void main(String[] args) {
        String str="example text";
        CRC32 crc32=new CRC32();
        crc32.update(str.getBytes());
        String crc=Long.toHexString(crc32.getValue());
        System.out.println(crc);
    }
}