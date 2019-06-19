package sample.api;

public class DataProvider implements IDataProvider
{
    private IApiDataProvider apiDataProvider;

    public DataProvider()
    {
        apiDataProvider = ApiConnector.GetInstance();
    }

    
}
