package currency.api.entities;

public class CurrenciesTopRatesContainer
{
    // table type
    private String table;
    // data batch date(?)
    private String no;
    // date of obtained data
    private String effectiveDate;
    // Rates
    private CurrencyTopRates[] rates;

    public String getTable() { return table; }
    public void setTable(String table) { this.table = table; }

    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }

    public String getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(String effectiveDate) { this.effectiveDate = effectiveDate; }

    public CurrencyTopRates[] getRates() { return rates; }
    public void setRates(CurrencyTopRates[] rates) { this.rates = rates; }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(table);
        builder.append(" ");
        builder.append(no);
        builder.append(" ");
        builder.append(effectiveDate);
        for (var rate : rates)
        {
            builder.append("\n");
            builder.append(rate.getCurrency());
            builder.append(" ");
            builder.append(rate.getCode());
            builder.append(" ");
            builder.append(rate.getMid());
        }

        return builder.toString();
    }
}
