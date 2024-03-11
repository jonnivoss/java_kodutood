package inheritance.analyser;

import java.util.List;

final class DifferentiatedTaxSalesAnalyser extends AbstractTaxSalesAnalyser {

    public DifferentiatedTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected Double getTax(boolean reduced){
        return reduced?0.1:0.2;
    }


}
