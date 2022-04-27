import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.TimeZone;


public class Zad5 {
    public static void main(String[] args)
    {
        System.out.println("Local time: "+LocalTime());
        System.out.println("Global time: "+GlobalTime());
    }
    private static String LocalTime(){

        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }
    private static String GlobalTime(){

        DateFormat global = new SimpleDateFormat("HH:mm:ss");
        global.setTimeZone(TimeZone.getTimeZone("GMT"));
        return global.format(Calendar.getInstance().getTime());
    }
}
