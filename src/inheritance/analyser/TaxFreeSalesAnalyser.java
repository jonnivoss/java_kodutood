package inheritance.analyser;

import java.util.List;

final class TaxFreeSalesAnalyser extends AbstractTaxSalesAnalyser{

    public TaxFreeSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected Double getTax(boolean reduced){
        return 0.0;
    }


}
