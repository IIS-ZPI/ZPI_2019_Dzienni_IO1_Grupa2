package sample.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import sample.api.entities.CurrencyRatesContainer;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiConnector implements IApiDataProvider {
    // Singleton related definitions
    private ApiConnector() {
    }

    private static ApiConnector Instance;

    public static ApiConnector GetInstance() {
        if (Instance == null)
            Instance = new ApiConnector();

        return Instance;
    }

    // Class functionality
    private HttpURLConnection connection;
    private URL connectionTarget;
    private boolean isConncected = false;

    private boolean ConnectToApi(String urlName) {
        if (isConncected)
            return false;

        try {
            connectionTarget = new URL(urlName);
            connection = (HttpURLConnection) connectionTarget.openConnection();
        } catch (MalformedURLException malformed) {
            System.out.println("Blad podczas tworzenia obieku URL!");
            return false;
        } catch (java.io.IOException io) {
            System.out.println("Blad podczas uzyskiwania polaczenia!");
            return false;
        }

        isConncected = true;
        return true;
    }

    public CurrencyRatesContainer[] RequestTopExchangeRates(String tableType, int topCount) {
        StringBuilder builder = new StringBuilder("http://api.nbp.pl/api/exchangerates/tables/");
        builder.append(tableType);
        builder.append("/last/");
        builder.append(topCount);
        builder.append("/?format=json");
        System.out.println(builder.toString());
        if (!ConnectToApi(builder.toString()))
            return null;

        CurrencyRatesContainer[] requestResult = Request(CurrencyRatesContainer[].class);
        //for (var node : requestResult)
        //System.out.println(node.toString());
        return requestResult;
    }

    private <T> T[] Request(Class<T[]> classType) {
        if (!isConncected)
            return null;

        T[] result = null;

        try {
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200)
                result = GetJsonFileFromResponse(classType);
        } catch (Exception e) {
            System.out.println("Blad podczas pobierania danych z api!");
        }

        CloseConnection();
        return result;
    }

    private void CloseConnection() {
        connection.disconnect();
        connection = null;
        connectionTarget = null;
        isConncected = false;
    }

    private <T> T[] GetJsonFileFromResponse(Class<T[]> classType) {
        InputStreamReader input;
        try {
            input = new InputStreamReader(connection.getInputStream());
        } catch (Exception e) {
            System.out.println("Blad podczas uzyskiwania strumienia danych!");
            return null;
        }

        //BufferedReader reader = new BufferedReader(input);
        ObjectMapper mapper = new ObjectMapper();
        T[] result = null;

        // Debug code
        try {
            result = mapper.readValue(input, classType);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas mapowania obiektu!");
            return null;
        }

        return result;
    }

    public static <T> T[] GetJsonFileFromStringResponse(Class<T[]> classType, String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        T[] result = null;

        try {
            result = mapper.readValue(jsonString, classType);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas mapowania obiektu!");
        }

        return result;
    }
}
