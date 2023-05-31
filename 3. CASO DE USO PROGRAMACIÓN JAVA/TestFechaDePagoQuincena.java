import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TestFechaDePagoQuincena {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese la fecha (yyyy-mm-dd): ");

        try {
            String fechaIngresada = input.nextLine();
            LocalDate fecha = LocalDate.parse(fechaIngresada);
            DiasFestivos diasFestivo = new DiasFestivos();
            List<LocalDate> diasFestivos = diasFestivo.cargarDiasFestivos("dias_festivos.txt");
            LocalDate fechaDePago = FechaDePagoQuincena.obtenerFechaPagoQuincena(fecha, diasFestivos);
            System.out.println("La fecha de pago de la quincena es: " + fechaDePago);
        }
        catch (DateTimeParseException exception) {
            System.out.println("Ingrese la fecha en el formato correcto (yyyy-mm-dd): ");
        }
    }
}