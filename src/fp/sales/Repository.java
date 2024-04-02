package fp.sales;

import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries() {
        try{
            List<Entry> entries = Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .skip(1)
                    .map(line -> line.split("\t"))
                    .map(this::mapToEntry)
                    .collect(Collectors.toList());

            return entries;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Entry mapToEntry(String[] p){
        Entry e = new Entry();
        e.setDate(LocalDate.parse(p[0],formatter));
        e.setState(p[1]);
        e.setProductId(p[2]);
        e.setCategory(p[3]);
        e.setAmount(Double.parseDouble(p[5].replaceAll(",",".")));
        return e;
    }

}
