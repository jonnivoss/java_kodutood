package poly.customer;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints) {
        super(id, name, bonusPoints);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if(order.getTotal() <= 100.0){
            return;
        }
        bonusPoints += order.getTotal() * 1.5;
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public String asString() {
        String template = "GOLD;" + getId() + ";"
                + getName() + ";" + getBonusPoints() + ";";
        return template;
    }

}