package currency.api;

public class ApiConnectorFactory {
    public static IApiDataProvider getDefaultConnector() {
        return ApiConnector.getInstance();
    }
}
