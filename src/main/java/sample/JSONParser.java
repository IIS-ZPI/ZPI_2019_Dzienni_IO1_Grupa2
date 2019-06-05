package sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONParser {
    private final static String JSON_DATA =
            "{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"098/A/NBP/2019\",\"effectiveDate\":\"2019-05-22\",\"mid\":4.8906},{\"no\":\"099/A/NBP/2019\",\"effectiveDate\":\"2019-05-23\",\"mid\":4.8852},{\"no\":\"100/A/NBP/2019\",\"effectiveDate\":\"2019-05-24\",\"mid\":4.8768},{\"no\":\"101/A/NBP/2019\",\"effectiveDate\":\"2019-05-27\",\"mid\":4.8759},{\"no\":\"102/A/NBP/2019\",\"effectiveDate\":\"2019-05-28\",\"mid\":4.8641},{\"no\":\"103/A/NBP/2019\",\"effectiveDate\":\"2019-05-29\",\"mid\":4.8744},{\"no\":\"104/A/NBP/2019\",\"effectiveDate\":\"2019-05-30\",\"mid\":4.8672},{\"no\":\"105/A/NBP/2019\",\"effectiveDate\":\"2019-05-31\",\"mid\":4.8572},{\"no\":\"106/A/NBP/2019\",\"effectiveDate\":\"2019-06-03\",\"mid\":4.8410},{\"no\":\"107/A/NBP/2019\",\"effectiveDate\":\"2019-06-04\",\"mid\":4.8144}]}";

    public static void main(final String[] argv) throws JSONException {
        final JSONObject obj = new JSONObject(JSON_DATA);
        /*final JSONArray obj2 = obj.getJSONArray("rates");
        final JSONArray geodata = obj.getJSONArray("rates");
        Iterator a= obj.keys();

        while (a.hasNext()){
            System.out.println(a.next());
            //System.out.println(obj.getString("rates"));
            a.remove();
        }
        System.out.println(obj2.getJSONObject(1));*/
        String[][] a = parser(obj);
        for (String[] b : a
        ) {
            for (String c : b
            ) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        //System.out.println(obj.keys().next());
        //System.out.println(obj.keys());
        /*final int n = geodata.length();
        for (int i = 0; i < n; ++i) {
            final JSONObject person = geodata.getJSONObject(i);
            //System.out.println(person.getInt("id"));
            //System.out.println(person.getString("name"));
            //System.out.println(person.getString("gender"));
            //System.out.println(person.getDouble("latitude"));
            //System.out.println(person.getDouble("longitude"));
            }
            */

    }

    static String[][] parser(JSONObject jsonObject) {
        try {
            final JSONArray jsonArray = jsonObject.getJSONArray("rates");
            String[][] strings = new String[jsonArray.length()][jsonArray.getJSONObject(0).length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject rate = jsonArray.getJSONObject(i);
                Iterator iterator = rate.keys();
                int j = 0;
                while (iterator.hasNext()) {
                    strings[i][j++] = rate.get((String) iterator.next()).toString();
                }
            }
            return strings;
        } catch (JSONException e) {
            e.printStackTrace();
            return new String[0][0];
        }
    }
}
