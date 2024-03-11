package inheritance.analyser;

import java.util.*;

public sealed abstract class AbstractTaxSalesAnalyser permits DifferentiatedTaxSalesAnalyser, FlatTaxSalesAnalyser, TaxFreeSalesAnalyser {
    protected List<SalesRecord> records;

    public AbstractTaxSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
    }

    protected abstract Double getTax(boolean reduced);

    protected Double getTotalSales() {
        double totalSale = 0.0;
        for (SalesRecord sale : records) {
            totalSale += (sale.getItemsSold() * sale.getProductPrice()) / (1.0 + getTax(sale.hasReducedRate()));
        }
        return totalSale;
    }

    protected Double getTotalSalesByProductId(String id) {
        double totalSales = 0d;
        for (SalesRecord sale : records) {
            if (sale.getProductId().equals(id)) {
                totalSales += (sale.getItemsSold() * sale.getProductPrice()) / (1.0 + getTax(sale.hasReducedRate()));
            }
        }
        return totalSales;
    }

    public List<String> getTop3PopularItems() {
        List<SalesRecord> temp = getSalesVolume();
        List<String> top = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SalesRecord biggest = new SalesRecord("",0,0);
            for (SalesRecord s : temp){
                if(s.getItemsSold() > biggest.getItemsSold()){
                    biggest = s;
                }
            }
            temp.remove(biggest);
            top.add(biggest.getProductId());
        }
        return top;
    }

    private List<SalesRecord> getSalesVolume(){
        List<String> strings = getUniqueId();
        int[] temp = new int[strings.size()];
        int[] price = new int[strings.size()];
        for (SalesRecord sale : records){
            temp[strings.indexOf(sale.getProductId())] += sale.getItemsSold();
            price[strings.indexOf(sale.getProductId())] = sale.getProductPrice();
        }

        List<SalesRecord> maxSales = new ArrayList<>();
        for (int j = 0; j < strings.size(); j++) {
            maxSales.add(new SalesRecord(strings.get(j),price[j],temp[j]));
        }
        return maxSales;
    }
    private List<String> getUniqueId(){
        List<String> temp = new ArrayList<>();
        for (SalesRecord record : records) {
            if (!temp.contains(record.getProductId())) {
                temp.add(record.getProductId());
            }
        }
        return temp;
    }

    public String getIdOfItemWithLargestTotalSales() {
        List<SalesRecord> temp = getSalesVolume();
        SalesRecord biggest = new SalesRecord("", 0,0);
        for (SalesRecord s : temp){
            if(s.getItemsSold()*s.getProductPrice() > biggest.getProductPrice()*biggest.getItemsSold()){
                biggest = s;
            }
        }
        return biggest.getProductId();
    }
}
