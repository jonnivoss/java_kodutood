package junit.sales;


public class TopSalesFinder {
    private SalesRecord[] records = new SalesRecord[0];
    public void registerSale(SalesRecord record) {
        // store sales record for later analyses by findItemsSoldOver()
        records = appendSale(records, record);
    }
    private SalesRecord[] appendSale(SalesRecord[] rec, SalesRecord str){
        SalesRecord[] array = copy(rec, rec.length + 1);
        array[array.length-1] = str;
        return array;
    }

    private int amountSold(String id){
        int profit = 0;
        for (SalesRecord sale : records){
            if(sale.getProductId().equals(id)){
                profit += sale.getItemsSold() * sale.getProductPrice();
            }
        }
        return profit;
    }

    private String[] appendString(String[] rec, String str){
        String[] array = copyString(rec, rec.length + 1);
        array[array.length-1] = str;
        return array;
    }
    private boolean hasAppeared(String[] array,String id){
        for (String s : array){
            if(s.equals(id)){
                return true;
            }
        }
        return false;
    }

    public String[] findItemsSoldOver(int amount) {
        String[] temp = new String[0];
        String[] itemsOver = new String[0];
        // find ids of records that sold over specified amount.
        for (SalesRecord sale : records){
            if (!hasAppeared(temp, sale.getProductId())){
                temp = appendString(temp, sale.getProductId());
                if(amountSold(sale.getProductId()) > amount){
                    itemsOver = appendString(itemsOver, sale.getProductId());
                }
            }
        }

        return itemsOver;
    }

    private SalesRecord[] removeFromSalesArray(SalesRecord[] rec, int index){
        SalesRecord[] temp = copy(rec, rec.length);
        for (int i = index; i < rec.length - 1; i++) {
            temp[i] = temp[i+1];
        }
        return copy(temp, temp.length - 1);
    }

    public void removeSalesRecordsFor(String id) {
        SalesRecord[] temp = copy(records, records.length);
        // removes records with specified id
        int index = 0;
        for (SalesRecord sale : records) {
            if(sale.getProductId().equals(id)){
                temp = removeFromSalesArray(temp, index);
                index--;
            }
            index++;
        }
        records = copy(temp, temp.length);
    }

    private SalesRecord[] copy(SalesRecord[] records, int lenght){
        SalesRecord[] temp = new SalesRecord[lenght];
        int ler;
        ler = (records.length < lenght)? records.length:lenght;
        for (int i = 0; i < ler; i++) {
            temp[i] = records[i];
        }
        return temp;
    }
    private String[] copyString(String[] records, int lenght){
        String[] temp = new String[lenght];
        int ler;
        ler = (records.length < lenght)? records.length:lenght;
        for (int i = 0; i < ler; i++) {
            temp[i] = records[i];
        }
        return temp;
    }

}


