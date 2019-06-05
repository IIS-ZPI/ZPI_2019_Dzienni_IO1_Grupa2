package sample.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiConnector
{
    // Singleton related definitions
    private ApiConnector() {}
    private static ApiConnector Instance;

    public static ApiConnector GetInstance()
    {
        if (Instance == null)
            Instance = new ApiConnector();

        return Instance;
    }

    // Class functionality
    private HttpURLConnection connection;
    private URL connectionTarget;
    private boolean isConncected = false;

    private boolean ConnectToApi(String urlName)
    {
        if (isConncected)
            return false;

        try
        {
            connectionTarget = new URL(urlName);
            connection = (HttpURLConnection)connectionTarget.openConnection();
        }
        catch (MalformedURLException malformed)
        {
            System.out.println("Blad podczas tworzenia obieku URL!");
            return false;
        }
        catch (java.io.IOException io)
        {
            System.out.println("Blad podczas uzyskiwania polaczenia!");
            return false;
        }

        isConncected = true;
        return true;
    }

    public void RequestTopExchangeRates(String tableType, int topCount)
    {
        StringBuilder builder = new StringBuilder("http://api.nbp.pl/api/exchangerates/tables/");
        builder.append(tableType);
        builder.append("/last/");
        builder.append(topCount);
        builder.append("/?format=json");
        System.out.println(builder.toString());
        if (!ConnectToApi(builder.toString()))
            return;

        Request();
    }

    private void Request()
    {
        if (!isConncected)
            return;

        try
        {
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200)
                GetJsonFileFromResponse();
        }
        catch (Exception e) { }

        CloseConnection();
    }

    private void CloseConnection()
    {
        connection.disconnect();
        connection = null;
        connectionTarget = null;
        isConncected = false;
    }

    private void GetJsonFileFromResponse()
    {
        InputStreamReader input;
        try
        {
            input = new InputStreamReader(connection.getInputStream());
        }
        catch (Exception e)
        {
            System.out.println("Blad podczas uzyskiwania strumienia danych!");
            return;
        }

        BufferedReader reader = new BufferedReader(input);
        // retro fit 2

        // Debug code
        try
        {
            String str = reader.readLine();
            while (str != null)
            {
                System.out.println(str);
                str = reader.readLine();
            }

        }catch (Exception e)
        {
            System.out.println("Koniec pliku!");
        }
    }
}
