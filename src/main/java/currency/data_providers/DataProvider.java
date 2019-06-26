package currency.data_providers;

import currency.PeriodEnum;
import currency.api.ApiConnectorFactory;
import currency.math.Statistic;
import currency.api.IApiDataProvider;
import currency.api.entities.CurrencyRate;
import currency.api.entities.CurrencyRatesContainer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataProvider implements IDataProvider {
    private IApiDataProvider apiDataProvider;

    DataProvider() {
        apiDataProvider = ApiConnectorFactory.getDefaultConnector();
    }

    @Override
    public String getDateStringForPeriod(PeriodEnum period) {
        StringBuilder builder = new StringBuilder("/");
        LocalDateTime dateTime = LocalDateTime.now();
        String currentTime = getDateStringFromDate(dateTime);
        long daysToDecrease = 0;
        long monthsToDecrease = 0;
        long yearsToDecrease = 0;

        switch (period) {
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

        builder.append(getDateStringFromDate(dateTime));
        builder.append("/");
        builder.append(currentTime);
        builder.append("/");

        return builder.toString();
    }

    private String getDateStringFromDate(LocalDateTime time) {
        StringBuilder builder = new StringBuilder();
        builder.append(time.getYear());
        builder.append("-");
        builder.append(time.getMonth());
        builder.append("-");
        builder.append(time.getDayOfMonth());
        return builder.toString();
    }

    @Override
    public int getSessionIncreaseAmount(PeriodEnum period, String currency) {
        String periodStr = getDateStringForPeriod(period);
        CurrencyRatesContainer container = apiDataProvider.RequestRatesForCurrency(currency, periodStr);
        if (container == null)
            return 0;

        int amount = 0;
        double lastValue = 0;
        List<CurrencyRate> list = container.getRates();

        if (list.size() > 0)
            lastValue = list.get(0).getMid();

        for (int i = 1; i < list.size(); ++i) {
            double val = list.get(i).getMid();
            if (val > lastValue)
                ++amount;

            lastValue = val;
        }

        return amount;
    }

    @Override
    public int getSessionDecreaseAmount(PeriodEnum period, String currency) {
        String periodStr = getDateStringForPeriod(period);
        CurrencyRatesContainer container = apiDataProvider.RequestRatesForCurrency(currency, periodStr);
        if (container == null)
            return 0;

        int amount = 0;
        double lastValue = 0;
        List<CurrencyRate> list = container.getRates();

        if (list.size() > 0)
            lastValue = list.get(0).getMid();

        for (int i = 1; i < list.size(); ++i) {
            double val = list.get(i).getMid();
            if (val < lastValue)
                ++amount;

            lastValue = val;
        }

        return amount;
    }

    @Override
    public int getSessionWithoutChangeAmount(PeriodEnum period, String currency) {
        String periodStr = getDateStringForPeriod(period);
        CurrencyRatesContainer container = apiDataProvider.RequestRatesForCurrency(currency, periodStr);
        if (container == null)
            return 0;

        int amount = 0;
        double lastValue = 0;
        List<CurrencyRate> list = container.getRates();

        if (list.size() > 0)
            lastValue = list.get(0).getMid();

        for (int i = 1; i < list.size(); ++i) {
            double val = list.get(i).getMid();
            if (val == lastValue)
                ++amount;

            lastValue = val;
        }

        return amount;
    }

    private double[] GetValuesListForRate(PeriodEnum period, String currency) {
        String periodStr = getDateStringForPeriod(period);
        CurrencyRatesContainer container = apiDataProvider.RequestRatesForCurrency(currency, periodStr);
        if (container == null)
            return null;

        double[] listOfValues = new double[container.getRates().size()];
        for (int i = 0; i < container.getRates().size(); ++i)
            listOfValues[i] = container.getRates().get(i).getMid();

        return listOfValues;
    }

    @Override
    public double getMedianOfRate(PeriodEnum period, String currency) {
        double[] listOfValues = GetValuesListForRate(period, currency);
        if (listOfValues == null)
            return 0.0f;
        return Statistic.median(listOfValues);
    }

    @Override
    public List<Double> getDominantOfRate(PeriodEnum period, String currency) {
        double[] listOfValues = GetValuesListForRate(period, currency);
        if (listOfValues == null)
            return new ArrayList<>();

        return Statistic.dominant(listOfValues);
    }

    @Override
    public double getStandardDevationOfRate(PeriodEnum period, String currency) {
        double[] listOfValues = GetValuesListForRate(period, currency);
        return Statistic.standardDeviation(listOfValues);
    }

    @Override
    public double getCoefficientOfVariationOfRate(PeriodEnum period, String currency) {
        double[] lisOfValues = GetValuesListForRate(period, currency);
        return Statistic.coefficientOfVariation(lisOfValues);
    }

    @Override
    public List<Double> getMonthlyDistributionOfChanges(String currencyOne, String currencyTwo) {
        return getDistributionChanges(PeriodEnum.PERIOD_MONTH, currencyOne, currencyTwo);
    }

    @Override
    public List<Double> getQuarterDistributionOfChanges(String currencyOne, String currencyTwo) {
        return getDistributionChanges(PeriodEnum.PERIOD_QUARTER, currencyOne, currencyTwo);
    }

    private List<Double> getDistributionChanges(PeriodEnum periodEnum, String currencyOne, String currencyTwo) {
        String period = getDateStringForPeriod(PeriodEnum.PERIOD_MONTH);
        CurrencyRatesContainer containerOne = apiDataProvider.RequestRatesForCurrency(currencyOne, period);
        CurrencyRatesContainer containerTwo = apiDataProvider.RequestRatesForCurrency(currencyTwo, period);

        if (containerOne == null || containerTwo == null)
            return new ArrayList<>();

        if (containerOne.getRates().size() == 0 || containerTwo.getRates().size() == 0)
            return new ArrayList<>();

        ArrayList<Double> result = new ArrayList<>();
        List<CurrencyRate> ratesOne = containerOne.getRates();
        List<CurrencyRate> ratesTwo = containerTwo.getRates();

        double lastSum = ratesOne.get(0).getMid() + ratesTwo.get(0).getMid();
        int i = 1;
        int j = 1;

        while (i < ratesOne.size() && j < ratesTwo.size()) {
            double sum = ratesOne.get(i).getMid() + ratesOne.get(j).getMid();
            double change = sum - lastSum;
            lastSum = sum;
            result.add(change);
            ++i;
            ++j;
        }

        return result;
    }
}
