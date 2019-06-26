package sample.api;

import sample.api.entities.CurrenciesTopRatesContainer;
import sample.api.entities.CurrencyRatesContainer;

public interface IApiDataProvider
{
    CurrenciesTopRatesContainer[] RequestTopExchangeRates(String tableType, int topCount);
    CurrencyRatesContainer RequestRatesForCurrency(String currency, String period);
}
