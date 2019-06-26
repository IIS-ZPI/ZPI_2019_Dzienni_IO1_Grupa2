package currency.data_providers;

import currency.PeriodEnum;
import currency.api.ApiConnectorFactory;
import currency.api.IApiDataProvider;

public class DataProvider implements IDataProvider {
    private IApiDataProvider apiDataProvider;

    public DataProvider() {
        apiDataProvider = ApiConnectorFactory.GetDefaultConnector();
    }

    @Override
    public int GetSessionIncreaseAmount(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public int GetSessionDecreaseAmount(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public int GetSessionWithoutChangeAmount(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double GetMedianOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double GetDominantOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double GetStandardDevationOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double GetCoefficientOfVariationOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public void GetMonthlyDistributionOfChanges(String currencyOne, String currencyTwo) {

    }

    @Override
    public void GetQuarterDistributionOfChanges(String currencyOne, String currencyTwo) {

    }
}
