package helper;

import java.util.Objects;

public class WeatherBuilder {

        double tempDegree;
        double tempFah;
        double humidity;
        String source;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeatherBuilder that = (WeatherBuilder) o;
            return Double.compare(that.tempDegree, tempDegree) == 0 &&
                    Double.compare(that.tempFah, tempFah) == 0 &&
                    Double.compare(that.humidity, humidity) == 0 &&
                    city.equals(that.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tempDegree, tempFah, humidity, city);
        }

        String city;

        public WeatherBuilder(double tempDegree, double tempFah, double humidity, String source, String city) {
            //Since API returns temperature only in Kelvin.
            if(source.equalsIgnoreCase("api")) {
                this.tempDegree = tempDegree - 273.15;
                this.tempFah = ((300 * tempFah  * 9)/5) - 459.67;
            }

            this.tempFah = tempFah;
            this.humidity = humidity;
            this.source = source;
            this.city=city;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }


        public double getTempDegree() {
            return tempDegree;
        }

        public void setTempDegree(double tempDegree) {
            this.tempDegree = tempDegree;
        }

        public double getTempFah() {
            return tempFah;
        }

        public void setTempFah(double tempFah) {
            this.tempFah = tempFah;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }



    }



