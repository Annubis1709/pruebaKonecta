import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class FechaDePagoQuincena {
    public static LocalDate obtenerFechaPagoQuincena(LocalDate fecha, List<LocalDate> diasFestivos) {
        if (esDiaHabil(fecha, diasFestivos))
            return fecha;

        LocalDate fechaAnterior = fecha;
        LocalDate fechaSiguiente = fecha;

        while(true) {
            fechaAnterior = fechaAnterior.minusDays(1);
            fechaSiguiente = fechaSiguiente.plusDays(1);

            if (esDiaHabil(fechaAnterior, diasFestivos)) {
                return fechaAnterior;
            } else if (esDiaHabil(fechaSiguiente, diasFestivos)) {
                return fechaSiguiente;
            }
        }
    }

    public static boolean esDiaHabil(LocalDate fecha, List<LocalDate> diasFestivos) {
        return fecha.getDayOfWeek() != DayOfWeek.SATURDAY &&
                fecha.getDayOfWeek() != DayOfWeek.SUNDAY &&
                !diasFestivos.contains(fecha);
    }
}
