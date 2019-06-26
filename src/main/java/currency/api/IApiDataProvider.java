package currency.api;

import currency.api.entities.CurrencyRatesContainer;

public interface IApiDataProvider {
    CurrencyRatesContainer[] requestTopExchangeRates(String tableType, int topCount);
}
