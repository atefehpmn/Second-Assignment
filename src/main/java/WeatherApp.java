import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import javax.swing.*;
import java.util.Scanner;

public class WeatherApp {
    public final static String apiKey = "d95c7dd5806b4bd484c113131232702";

    public WeatherApp(String city, String cityWeather, JSONObject cityJson) {
        JFrame frame = new JFrame();
        JLabel labelTemperature = new JLabel("Temperature: " + getTemperature(cityWeather, cityJson) + " C");
        JLabel labelHumidity = new JLabel("Humidity Percentage: " + getHumidity(cityWeather, cityJson) + "%");
        JLabel labelWindsp = new JLabel("Wind Speed: " + getWindSpeed(cityWeather, cityJson) + " mph");
        JLabel labelWinddir = new JLabel("Wind Direction: " + getWindDir(cityWeather, cityJson));


        labelTemperature.setFont(new Font("Serif", Font.PLAIN, 30));
        labelHumidity.setFont(new Font("Serif", Font.PLAIN, 30));
        labelWinddir.setFont(new Font("Serif", Font.PLAIN, 30));
        labelWindsp.setFont(new Font("Serif", Font.PLAIN, 30));
        labelTemperature.setForeground(Color.BLUE);
        labelHumidity.setForeground(Color.BLUE);
        labelWinddir.setForeground(Color.BLUE);
        labelWindsp.setForeground(Color.BLUE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(90, 80, 90, 80));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(labelTemperature);
        panel.add(labelHumidity);
        panel.add(labelWindsp);
        panel.add(labelWinddir);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(city + "'s current Weather Data");
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter city's name: ");
        String city = in.nextLine();
        String cityWeather = getWeatherData(city);
        while (cityWeather == null){
            System.out.println("City not found! please enter a valid city name:");
            city = in.nextLine();
            cityWeather = getWeatherData(city);
        }
        JSONObject cityJson = new JSONObject(cityWeather); //converting the weather data in string to a json object
        new WeatherApp(city, cityWeather, cityJson);
    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static double getTemperature(String weatherJson, JSONObject city){
        double answer = 0.0;
        answer = city.getJSONObject("current").getDouble("temp_c");
        return answer;
    }


    public static int getHumidity(String weatherJson, JSONObject city){
        int answer = 0;
        answer = city.getJSONObject("current").getInt("humidity");
        return answer;
    }

    public static double getWindSpeed(String weatherJson, JSONObject city){
        double answer = 0.0;
        answer = city.getJSONObject("current").getDouble("wind_mph");
        return answer;
    }

    public static String getWindDir (String weatherJson, JSONObject city){
        String answer = "";
        answer = city.getJSONObject("current").getString("wind_dir");
        return answer;
    }
}
