package poly.customer;

import java.time.LocalDate;
import java.util.Arrays;

public final class RegularCustomer extends AbstractCustomer {

    private LocalDate lastOrderDate;
    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);

        this.lastOrderDate = lastOrderDate;
    }

    public LocalDate getLastOrderDate(){
        return lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if(order.getTotal() <= 100.0){
            return;
        }
        if(order.getDate().toEpochDay() - lastOrderDate.toEpochDay() <= 31){
            bonusPoints += order.getTotal() *1.5;
        }else {
            bonusPoints += order.getTotal();
        }
        lastOrderDate = order.getDate();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AbstractCustomer){
            AbstractCustomer temp  = (AbstractCustomer) obj;
            return temp.getName().equals(getName()) &&  temp.getId().equals(getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("not implemented yet hash");
    }

    @Override
    public String asString() {
        String template = "REGULAR;" + getId() + ";"
                + getName() + ";" + getBonusPoints() + ";";
        String date =  getLastOrderDate().toString().formatted("YYYY-MM-DD");
        return template+date;
    }

}