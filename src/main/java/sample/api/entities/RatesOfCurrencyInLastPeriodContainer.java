package sample.api.entities;

public class RatesOfCurrencyInLastPeriodContainer {
    // table type
    private String table;
    // data batch date(?)
    private String currency;
    // date of obtained data
    private String no;
    // Rates
    private RatesOfCurrencyInLastPeriod[] rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String setNo() {
        return no;
    }

    public void getNo(String effectiveDate) {
        this.no = no;
    }

    public RatesOfCurrencyInLastPeriod[] getRates() {
        return rates;
    }

    public void setRates(RatesOfCurrencyInLastPeriod[] rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(table);
        builder.append(" ");
        builder.append(currency);
        builder.append(" ");
        builder.append(no);
        for (var rate : rates) {
            builder.append("\n");
            builder.append(rate.getCode());
            builder.append(" ");
            builder.append(rate.getEffectiveDate());
            builder.append(" ");
            builder.append(rate.getMid());
        }

        return builder.toString();
    }
}
