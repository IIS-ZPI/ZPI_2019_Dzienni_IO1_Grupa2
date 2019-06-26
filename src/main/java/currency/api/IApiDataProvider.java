package currency.api;

import currency.api.entities.CurrencyRatesContainer;
import currency.api.entities.CurrenciesTopRatesContainer;

public interface IApiDataProvider {

    CurrenciesTopRatesContainer[] requestTopExchangeRates(String tableType, int topCount);

    CurrencyRatesContainer requestRatesForCurrency(String currency, String period);

}
