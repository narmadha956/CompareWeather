package builders;

public class WeatherConditions {

    public WeatherConditions(String tempDegree, String source, String humidity, String cityName) {
        this.tempDegree = tempDegree;
        this.source = source;
        this.humidity = humidity;
        this.cityName = cityName;
    }
    String tempDegree;

    public String getTempDegree() {
        return tempDegree;
    }

    public String getSource() {
        return source;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getCityName() {
        return cityName;
    }

    String source;
    String humidity;
    String cityName;

    public void setTempDegree(String tempDegree) {
        this.tempDegree = tempDegree;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
