package sample.data_providers;

import sample.PeriodEnum;

import java.util.List;

public interface IDataProvider {
    String GetDateStringForPeriod(PeriodEnum period);

    int GetSessionIncreaseAmount(PeriodEnum period, String currency);

    int GetSessionDecreaseAmount(PeriodEnum period, String currency);

    int GetSessionWithoutChangeAmount(PeriodEnum period, String currency);

    double GetMedianOfRate(PeriodEnum period, String currency);

    List<Double> GetDominantOfRate(PeriodEnum period, String currency);

    double GetStandardDevationOfRate(PeriodEnum period, String currency);

    double GetCoefficientOfVariationOfRate(PeriodEnum period, String currency);

    List<Double> GetMonthlyDistributionOfChanges(String currencyOne, String currencyTwo);

    List<Double> GetQuarterDistributionOfChanges(String currencyOne, String currencyTwo);
}
