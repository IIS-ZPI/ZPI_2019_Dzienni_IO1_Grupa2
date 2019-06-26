package currency.data_providers;

import currency.PeriodEnum;
import currency.api.ApiConnectorFactory;
import currency.api.IApiDataProvider;

public class DataProvider implements IDataProvider {
    private IApiDataProvider apiDataProvider;

    public DataProvider() {
        apiDataProvider = ApiConnectorFactory.getDefaultConnector();
    }

    @Override
    public int getSessionIncreaseAmount(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public int getSessionDecreaseAmount(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public int getSessionWithoutChangeAmount(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double getMedianOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double getDominantOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double getStandardDevationOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public double getCoefficientOfVariationOfRate(PeriodEnum period, String currency) {
        return 0;
    }

    @Override
    public void getMonthlyDistributionOfChanges(String currencyOne, String currencyTwo) {

    }

    @Override
    public void getQuarterDistributionOfChanges(String currencyOne, String currencyTwo) {

    }
}
