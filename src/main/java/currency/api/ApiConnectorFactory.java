package currency.api;

public class ApiConnectorFactory {
    public static IApiDataProvider GetDefaultConnector() {
        return ApiConnector.GetInstance();
    }
}
