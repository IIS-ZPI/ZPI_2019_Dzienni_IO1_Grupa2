package sample.data_providers;

import sample.PeriodEnum;

public interface IDataProvider
{
    String GetDateStringForPeriod(PeriodEnum period);

    int GetSessionIncreaseAmount(PeriodEnum period, String currency);
    int GetSessionDecreaseAmount(PeriodEnum period, String currency);
    int GetSessionWithoutChangeAmount(PeriodEnum period, String currency);

    double GetMedianOfRate(PeriodEnum period, String currency);
    double GetDominantOfRate(PeriodEnum period, String currency);
    double GetStandardDevationOfRate(PeriodEnum period, String currency);
    double GetCoefficientOfVariationOfRate(PeriodEnum period, String currency);

    void GetMonthlyDistributionOfChanges(String currencyOne, String currencyTwo);
    void GetQuarterDistributionOfChanges(String currencyOne, String currencyTwo);
}
