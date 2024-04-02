package fp.sales;

import java.sql.DataTruncation;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Analyser {

    private final Repository repository;

    public Analyser(Repository repository) {
        this.repository = repository;
    }

    public Double getTotalSales() {
        Double total = repository
                .getEntries()
                .stream()
                .mapToDouble(Entry::getAmount)
                .sum();
        return total;
    }

    public Double getSalesByCategory(String category) {
        Double total = repository
                .getEntries()
                .stream()
                .filter(e -> e.getCategory().equals(category))
                .mapToDouble(Entry::getAmount)
                .sum();
        return total;
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        Double total = repository
                .getEntries()
                .stream()
                .filter(e -> e.getDate().isBefore(end) && e.getDate().isAfter(start))
                .mapToDouble(Entry::getAmount)
                .sum();
        return total;
    }

    public String mostExpensiveItems() {
        String top = repository
                .getEntries()
                .stream()
                .sorted(Collections.reverseOrder(Comparator
                        .comparing(Entry::getAmount)))
                .limit(3)
                .sorted(Comparator.comparing(Entry::getProductId))
                .map(Entry::getProductId)
                .collect(Collectors.joining(", "));
        return top;
    }

    public String statesWithBiggestSales() {
        var map = repository
                .getEntries();
        var top = map
                .stream()
                .collect(Collectors
                        .toMap(Entry::getState,
                                Entry::getAmount,
                                Double::sum));
        System.out.println(top.keySet());
        return null;
    }
}
