package currency.api;

import currency.api.entities.CurrencyRatesContainer;
import currency.api.entities.CurrenciesTopRatesContainer;

public interface IApiDataProvider {
    CurrenciesTopRatesContainer[] RequestTopExchangeRates(String tableType, int topCount);

    CurrencyRatesContainer RequestRatesForCurrency(String currency, String period);
}
