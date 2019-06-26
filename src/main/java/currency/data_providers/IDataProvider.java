package currency.data_providers;

import currency.PeriodEnum;

public interface IDataProvider {

    int getSessionIncreaseAmount(PeriodEnum period, String currency);

    int getSessionDecreaseAmount(PeriodEnum period, String currency);

    int getSessionWithoutChangeAmount(PeriodEnum period, String currency);

    double getMedianOfRate(PeriodEnum period, String currency);

    double getDominantOfRate(PeriodEnum period, String currency);

    double getStandardDevationOfRate(PeriodEnum period, String currency);

    double getCoefficientOfVariationOfRate(PeriodEnum period, String currency);

    void getMonthlyDistributionOfChanges(String currencyOne, String currencyTwo);

    void getQuarterDistributionOfChanges(String currencyOne, String currencyTwo);

}
