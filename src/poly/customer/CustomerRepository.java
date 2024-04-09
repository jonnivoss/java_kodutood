package poly.customer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private List<AbstractCustomer> customers = new ArrayList<>();

    public CustomerRepository(){
        makeCustomerList();
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void remove(String id) {
        if(getCustomerById(id).isEmpty()){
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId().equals(id)){
                customers.remove(i);
            }
        }
        writeText();
    }

    public void save(AbstractCustomer customer) {
        Optional<AbstractCustomer> temp = getCustomerById(customer.getId());
        if(temp.isPresent()){
            remove(customer.getId());
        }
        customers.add(customer);
        writeText();
    }

    private void writeText(){
        try {
            List<String> lines = customers.stream().map(AbstractCustomer::toString).toList();
            Files.write(Path.of(FILE_PATH),lines);
        } catch (IOException ignore) {
        }
    }

    public int getCustomerCount() {
        return customers.size();
    }

    private AbstractCustomer parseCustomer(String s){
        String[] words = s.split(";");
        switch (words[0]){
            case "REGULAR":
                return new RegularCustomer(words[1],words[2],Integer.parseInt(words[3]), LocalDate.parse(words[4]));
            case "GOLD":
                return new GoldCustomer(words[1],words[2],Integer.parseInt(words[3]));
            default:
                throw new IllegalArgumentException();
        }
    }
    private void makeCustomerList(){
        try {
            List<String> lines = Files.readAllLines(Path.of(FILE_PATH));
            for (String line : lines){
                customers.add(parseCustomer(line));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
