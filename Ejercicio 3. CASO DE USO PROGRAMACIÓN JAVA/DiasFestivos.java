import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DiasFestivos {
    public List<LocalDate> cargarDiasFestivos(String archivo) {
        try {
            return Files.lines(Paths.get(archivo))
                    .map(LocalDate::parse)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            System.out.println("Error al leer el archivo de dias festivos.");
            return List.of();
        }
    }
}
