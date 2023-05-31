import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiasFestivos {
    public List<LocalDate> cargarDiasFestivos(String archivo) {

        try (Stream<String> lines = Files.lines(Path.of(archivo)) ) {
            return lines.map(LocalDate::parse)
                        .collect(Collectors.toList());

        } catch (IOException exception) {
            System.out.println("Error al leer el archivo de dias festivos.");
            return List.of();
        }
    }
}