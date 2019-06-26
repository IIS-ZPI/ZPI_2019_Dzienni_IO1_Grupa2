package currency.data_providers;

public class DataProviderFactory {
    public static IDataProvider getDefaultDataProvider() {
        return new DataProvider();
    }
}
