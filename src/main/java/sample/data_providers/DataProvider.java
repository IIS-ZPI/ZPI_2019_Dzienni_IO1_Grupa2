package sample.data_providers;

import sample.PeriodEnum;
import sample.api.ApiConnectorFactory;
import sample.api.IApiDataProvider;
import sample.api.entities.CurrencyRate;
import sample.api.entities.CurrencyRatesContainer;

import java.time.LocalDateTime;
import java.util.List;

public class DataProvider implements IDataProvider
{
    private IApiDataProvider apiDataProvider;

    DataProvider()
    {
        apiDataProvider = ApiConnectorFactory.GetDefaultConnector();
    }

    @Override
    public String GetDateStringForPeriod(PeriodEnum period)
    {
        StringBuilder builder = new StringBuilder("/");
        LocalDateTime dateTime = LocalDateTime.now();
        String currentTime = GetDateStringFromDate(dateTime);
        long daysToDecrease = 0;
        long monthsToDecrease = 0;
        long yearsToDecrease = 0;

        switch (period)
        {
            case PERIOD_WEEK:
                daysToDecrease = 7;
                break;
            case PERIOD_TWO_WEEKS:
                daysToDecrease = 14;
                break;
            case PERIOD_MONTH:
                monthsToDecrease = 1;
                break;
            case PERIOD_QUARTER:
                monthsToDecrease = 3;
                break;
            case PERIOD_HALF_OF_YEAR:
                monthsToDecrease = 6;
                break;
            case PERIOD_YEAR:
                yearsToDecrease = 1;
                break;
        }

        dateTime = dateTime.minusDays(daysToDecrease);
        dateTime = dateTime.minusMonths(monthsToDecrease);
        dateTime = dateTime.minusYears(yearsToDecrease);

        builder.append(GetDateStringFromDate(dateTime));
        builder.append("/");
        builder.append(currentTime);
        builder.append("/");

        return builder.toString();
    }

    private String GetDateStringFromDate(LocalDateTime time)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(time.getYear());
        builder.append("-");
        builder.append(time.getMonth());
        builder.append("-");
        builder.append(time.getDayOfMonth());
        return builder.toString();
    }

    @Override
    public int GetSessionIncreaseAmount(PeriodEnum period, String currency) {
        String periodStr = GetDateStringForPeriod(period);
        CurrencyRatesContainer container = apiDataProvider.RequestRatesForCurrency(currency, periodStr);
        int amount = 0;
        double lastValue = 0;
        List<CurrencyRate> list = container.getRates();

        if (list.size() > 0)
            lastValue = list.get(0).getMid();

        for (int i = 1; i < list.size(); ++i)
        {
            double val = list.get(i).getMid();
            if (val > lastValue)
                ++amount;

            lastValue = val;
        }

        return amount;
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
