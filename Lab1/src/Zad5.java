import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Zad5 {
    public static void main(String[] args)
    {
        DateTimeFormatter date=DateTimeFormatter.ofPattern("HH:mm:ss");
        String localTime=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println(localTime);
    }
}
