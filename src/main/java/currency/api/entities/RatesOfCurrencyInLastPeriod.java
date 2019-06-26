package currency.api.entities;

public class RatesOfCurrencyInLastPeriod {
    // currency code name
    private String code;
    // date of obtained data
    private String effectiveDate;
    // rates
    private double mid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }
}
