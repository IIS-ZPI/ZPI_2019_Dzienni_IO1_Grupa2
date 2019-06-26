package currency.api;

import currency.api.entities.CurrencyRatesContainer;

public interface IApiDataProvider {
    CurrencyRatesContainer[] RequestTopExchangeRates(String tableType, int topCount);
}
