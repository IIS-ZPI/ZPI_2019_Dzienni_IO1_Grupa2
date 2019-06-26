package currency.api;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import currency.api.entities.CurrencyRatesContainer;

import static org.junit.Assert.assertThat;


public class ApiConnectorTest {

    private CurrencyRatesContainer[] currencyRatesContainer;

    @Before
    public void init() {
        currencyRatesContainer =
                ApiConnector.getJsonFileFromStringResponse(CurrencyRatesContainer[].class, getTestJson());
    }

    @Test
    public void shouldHave35Rates() {
        assertThat(currencyRatesContainer[0].getRates().length, Is.is(35));
        assertThat(currencyRatesContainer[1].getRates().length, Is.is(35));
    }

    @Test
    public void position7ShouldBeEuro() {
        assertThat(currencyRatesContainer[0].getRates()[7].getCurrency(), Is.is("euro"));
        assertThat(currencyRatesContainer[0].getRates()[7].getCode(), Is.is("EUR"));
        assertThat(currencyRatesContainer[0].getRates()[7].getMid(), Is.is(4.2788));
    }

    private String getTestJson(){
        return "[{\"table\":\"A\",\"no\":\"107/A/NBP/2019\",\"effectiveDate\":\"2019-06-04\",\"rates\":[{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1215},{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"mid\":3.8041},{\"currency\":\"dolar australijski\",\"code\":\"AUD\",\"mid\":2.6539},{\"currency\":\"dolar Hongkongu\",\"code\":\"HKD\",\"mid\":0.4852},{\"currency\":\"dolar kanadyjski\",\"code\":\"CAD\",\"mid\":2.8315},{\"currency\":\"dolar nowozelandzki\",\"code\":\"NZD\",\"mid\":2.5055},{\"currency\":\"dolar singapurski\",\"code\":\"SGD\",\"mid\":2.7793},{\"currency\":\"euro\",\"code\":\"EUR\",\"mid\":4.2788},{\"currency\":\"forint (Węgry)\",\"code\":\"HUF\",\"mid\":0.01329},{\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"mid\":3.8267},{\"currency\":\"funt szterling\",\"code\":\"GBP\",\"mid\":4.8144},{\"currency\":\"hrywna (Ukraina)\",\"code\":\"UAH\",\"mid\":0.1396},{\"currency\":\"jen (Japonia)\",\"code\":\"JPY\",\"mid\":0.035176},{\"currency\":\"korona czeska\",\"code\":\"CZK\",\"mid\":0.1661},{\"currency\":\"korona duńska\",\"code\":\"DKK\",\"mid\":0.5729},{\"currency\":\"korona islandzka\",\"code\":\"ISK\",\"mid\":0.030805},{\"currency\":\"korona norweska\",\"code\":\"NOK\",\"mid\":0.4378},{\"currency\":\"korona szwedzka\",\"code\":\"SEK\",\"mid\":0.4031},{\"currency\":\"kuna (Chorwacja)\",\"code\":\"HRK\",\"mid\":0.5768},{\"currency\":\"lej rumuński\",\"code\":\"RON\",\"mid\":0.9036},{\"currency\":\"lew (Bułgaria)\",\"code\":\"BGN\",\"mid\":2.1877},{\"currency\":\"lira turecka\",\"code\":\"TRY\",\"mid\":0.6545},{\"currency\":\"nowy izraelski szekel\",\"code\":\"ILS\",\"mid\":1.0528},{\"currency\":\"peso chilijskie\",\"code\":\"CLP\",\"mid\":0.005407},{\"currency\":\"peso filipińskie\",\"code\":\"PHP\",\"mid\":0.0735},{\"currency\":\"peso meksykańskie\",\"code\":\"MXN\",\"mid\":0.1935},{\"currency\":\"rand (Republika Południowej Afryki)\",\"code\":\"ZAR\",\"mid\":0.2636},{\"currency\":\"real (Brazylia)\",\"code\":\"BRL\",\"mid\":0.9791},{\"currency\":\"ringgit (Malezja)\",\"code\":\"MYR\",\"mid\":0.9110},{\"currency\":\"rubel rosyjski\",\"code\":\"RUB\",\"mid\":0.0584},{\"currency\":\"rupia indonezyjska\",\"code\":\"IDR\",\"mid\":0.00026648},{\"currency\":\"rupia indyjska\",\"code\":\"INR\",\"mid\":0.054881},{\"currency\":\"won południowokoreański\",\"code\":\"KRW\",\"mid\":0.003215},{\"currency\":\"yuan renminbi (Chiny)\",\"code\":\"CNY\",\"mid\":0.5501},{\"currency\":\"SDR (MFW)\",\"code\":\"XDR\",\"mid\":5.2811}]},{\"table\":\"A\",\"no\":\"108/A/NBP/2019\",\"effectiveDate\":\"2019-06-05\",\"rates\":[{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1210},{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"mid\":3.7929},{\"currency\":\"dolar australijski\",\"code\":\"AUD\",\"mid\":2.6551},{\"currency\":\"dolar Hongkongu\",\"code\":\"HKD\",\"mid\":0.4837},{\"currency\":\"dolar kanadyjski\",\"code\":\"CAD\",\"mid\":2.8362},{\"currency\":\"dolar nowozelandzki\",\"code\":\"NZD\",\"mid\":2.5195},{\"currency\":\"dolar singapurski\",\"code\":\"SGD\",\"mid\":2.7809},{\"currency\":\"euro\",\"code\":\"EUR\",\"mid\":4.2782},{\"currency\":\"forint (Węgry)\",\"code\":\"HUF\",\"mid\":0.013299},{\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"mid\":3.8234},{\"currency\":\"funt szterling\",\"code\":\"GBP\",\"mid\":4.8221},{\"currency\":\"hrywna (Ukraina)\",\"code\":\"UAH\",\"mid\":0.1411},{\"currency\":\"jen (Japonia)\",\"code\":\"JPY\",\"mid\":0.035038},{\"currency\":\"korona czeska\",\"code\":\"CZK\",\"mid\":0.1666},{\"currency\":\"korona duńska\",\"code\":\"DKK\",\"mid\":0.5729},{\"currency\":\"korona islandzka\",\"code\":\"ISK\",\"mid\":0.030668},{\"currency\":\"korona norweska\",\"code\":\"NOK\",\"mid\":0.4371},{\"currency\":\"korona szwedzka\",\"code\":\"SEK\",\"mid\":0.4027},{\"currency\":\"kuna (Chorwacja)\",\"code\":\"HRK\",\"mid\":0.5767},{\"currency\":\"lej rumuński\",\"code\":\"RON\",\"mid\":0.9040},{\"currency\":\"lew (Bułgaria)\",\"code\":\"BGN\",\"mid\":2.1874},{\"currency\":\"lira turecka\",\"code\":\"TRY\",\"mid\":0.6632},{\"currency\":\"nowy izraelski szekel\",\"code\":\"ILS\",\"mid\":1.0506},{\"currency\":\"peso chilijskie\",\"code\":\"CLP\",\"mid\":0.005471},{\"currency\":\"peso filipińskie\",\"code\":\"PHP\",\"mid\":0.0733},{\"currency\":\"peso meksykańskie\",\"code\":\"MXN\",\"mid\":0.1940},{\"currency\":\"rand (Republika Południowej Afryki)\",\"code\":\"ZAR\",\"mid\":0.2578},{\"currency\":\"real (Brazylia)\",\"code\":\"BRL\",\"mid\":0.9833},{\"currency\":\"ringgit (Malezja)\",\"code\":\"MYR\",\"mid\":0.9086},{\"currency\":\"rubel rosyjski\",\"code\":\"RUB\",\"mid\":0.0583},{\"currency\":\"rupia indonezyjska\",\"code\":\"IDR\",\"mid\":0.00026571},{\"currency\":\"rupia indyjska\",\"code\":\"INR\",\"mid\":0.054736},{\"currency\":\"won południowokoreański\",\"code\":\"KRW\",\"mid\":0.003219},{\"currency\":\"yuan renminbi (Chiny)\",\"code\":\"CNY\",\"mid\":0.5490},{\"currency\":\"SDR (MFW)\",\"code\":\"XDR\",\"mid\":5.2662}]}]\n";
    }

}
