package sample;

import sample.api.ApiConnector;
import sample.api.IApiDataProvider;

public class DataProvider implements IDataProvider
{
    private IApiDataProvider apiDataProvider;

    public DataProvider()
    {
        apiDataProvider = ApiConnector.GetInstance();
    }

    
}
