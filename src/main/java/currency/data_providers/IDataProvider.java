package currency.data_providers;

import currency.PeriodEnum;

import java.util.List;

public interface IDataProvider {

    String getDateStringForPeriod(PeriodEnum period);

    int getSessionIncreaseAmount(PeriodEnum period, String currency);

    int getSessionDecreaseAmount(PeriodEnum period, String currency);

    int getSessionWithoutChangeAmount(PeriodEnum period, String currency);

    double getMedianOfRate(PeriodEnum period, String currency);

    List<Double> getDominantOfRate(PeriodEnum period, String currency);

    double getStandardDevationOfRate(PeriodEnum period, String currency);

    double getCoefficientOfVariationOfRate(PeriodEnum period, String currency);

    List<Double> getMonthlyDistributionOfChanges(String currencyOne, String currencyTwo);

    List<Double> getQuarterDistributionOfChanges(String currencyOne, String currencyTwo);

}
