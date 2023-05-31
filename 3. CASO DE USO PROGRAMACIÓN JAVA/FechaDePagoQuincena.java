import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class FechaDePagoQuincena {
    public static LocalDate obtenerFechaPagoQuincena(LocalDate fecha, List<LocalDate> diasFestivos) {
        int diaDelMes = fecha.getDayOfMonth();
        LocalDate fechaDePago;

        if (diaDelMes <= 15) {
            fechaDePago = fecha.withDayOfMonth(15);
        } else {
            fechaDePago = fecha.withDayOfMonth(30);
        }

        while(!esDiaHabil(fechaDePago, diasFestivos)) {
            fechaDePago = fechaDePago.minusDays(1);
        }
        return fechaDePago;
    }

    public static boolean esDiaHabil(LocalDate fecha, List<LocalDate> diasFestivos) {
        return fecha.getDayOfWeek() != DayOfWeek.SATURDAY &&
                fecha.getDayOfWeek() != DayOfWeek.SUNDAY &&
                !diasFestivos.contains(fecha);
    }
}
