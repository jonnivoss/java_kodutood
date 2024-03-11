package inheritance.analyser;

import java.util.List;

final class FlatTaxSalesAnalyser extends AbstractTaxSalesAnalyser {
    public FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected Double getTax(boolean reduced) {
        return 0.2;
    }
}
