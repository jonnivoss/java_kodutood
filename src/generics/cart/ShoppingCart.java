package generics.cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart<T  extends CartItem> {
    public class Entry<T  extends CartItem>{
        private T cartItem;
        private Integer itemAmount;


        public Entry(T item){
            cartItem = item;
            itemAmount = 1;
        }
        public Integer getItemAmount(){
            return itemAmount;
        }
        public void increaseItemAmount(){
            itemAmount++;
        }

        public String getId(){return cartItem.getId();}
        public Double getPrice(){return cartItem.getPrice();}
    }
    private List<Double> itemDiscounts = new ArrayList<>();
    private Double totalDiscount = 0.0;

    private List<Entry> itemsInCart = new ArrayList<>();

    public void add(Object item) {
        if(!(item instanceof CartItem)){
            throw new RuntimeException("miskit on mada");
        }
        Entry<T> temp = new Entry<>((T) item);
        int index = doesCartContainId(temp.getId());
        if (index != -1){
            itemsInCart.get(index).increaseItemAmount();
        }else {
            itemsInCart.add(temp);
        }
    }

    private int doesCartContainId(String id){
        for (int i = 0; i < itemsInCart.size(); i++){
            if(id.equals(itemsInCart.get(i).getId())){
                return i;
            }
        }
        return -1;
    }

    public void removeById(String id) {
        int index = doesCartContainId(id);
        if(index != -1){
            itemsInCart.remove(index);
        }
    }

    public Double getTotal() {
        double sum = 0d;
        for(Entry e : itemsInCart){
            sum+= e.getPrice() * e.getItemAmount();
        }
        return sum * getTotalDiscount();
    }

    public void increaseQuantity(String id) {
        int index = doesCartContainId(id);
        if(index != -1){
            itemsInCart.get(index).increaseItemAmount();
        }
    }

    public void applyDiscountPercentage(Double discount) {
        itemDiscounts.add(discount);
    }

    private Double getTotalDiscount(){
        double precent = 100;
        for (Double d : itemDiscounts){
            double temp = (100.0 - d) / 100.0;
            precent  *= temp;
        }
        totalDiscount = precent/100.0;
        return totalDiscount;
    }
    public void removeLastDiscount() {
        itemDiscounts.removeLast();
    }

    public void addAll(Object items) {
        if(!(items instanceof List<?>)){
            throw new RuntimeException("millegi parast on Listi asemel midagi muud");
        }
        List<?> temp = (List<Object>)items;
        for (Object o : temp){
            add(o);
        }
    }

    @Override
    public String toString(){
        String temp = "";
        for (int i = 0; i < itemsInCart.size(); i++) {
            temp += "(%s, %s, %s)".
                    formatted(itemsInCart.get(i).getId(),
                            itemsInCart.get(i).getPrice(),
                            itemsInCart.get(i).getItemAmount());
            if(i < itemsInCart.size()-1){temp += ", ";}
        }
        return temp;
    }
}
