package sample.api;

import sample.api.entities.CurrencyRatesContainer;

public interface IApiDataProvider
{
    CurrencyRatesContainer[] RequestTopExchangeRates(String tableType, int topCount);
}
