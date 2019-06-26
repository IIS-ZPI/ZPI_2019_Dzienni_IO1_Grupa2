package sample.api;

import sample.api.entities.CurrenciesTopRatesContainer;

public interface IApiDataProvider
{
    CurrenciesTopRatesContainer[] RequestTopExchangeRates(String tableType, int topCount);

}
