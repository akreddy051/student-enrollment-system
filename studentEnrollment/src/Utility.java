import java.time.LocalDate;

public class Utility {
    public static boolean isValidDate(String date) {
        String[] d= date.split("/",3);
        LocalDate now = LocalDate.now();
        LocalDate dd = LocalDate.of(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]));
        return dd.isBefore(now);
    }
}
