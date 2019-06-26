package currency.data_providers;

public class DataProviderFactory {
    public static IDataProvider GetDefaultDataProvider() {
        return new DataProvider();
    }
}
