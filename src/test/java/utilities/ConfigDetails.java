package utilities;

import com.google.gson.Gson;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import java.io.FileReader;

public class ConfigDetails {

    public static class executor {
        String baseAPI;

        public executor(String baseAPI, String baseWebsite, String[] cityNames, String[] scenario) {
            this.baseAPI = baseAPI;
            this.baseWebsite = baseWebsite;
            this.cityNames = cityNames;
            this.scenario = scenario;
        }

        String baseWebsite;
        String[] cityNames;
        String[] scenario;

        public String getBaseAPI() {
            return baseAPI;
        }

        public void setBaseAPI(String baseAPI) {
            this.baseAPI = baseAPI;
        }

        public String getBaseWebsite() {
            return baseWebsite;
        }

        public void setBaseWebsite(String baseWebsite) {
            this.baseWebsite = baseWebsite;
        }

        public String[] getCityNames() {
            return cityNames;
        }

        public void setCityNames(String[] cityNames) {
            this.cityNames = cityNames;
        }

        public String[] getScenario() {
            return scenario;
        }

        public void setScenario(String[] scenario) {
            this.scenario = scenario;
        }
    }


    public static class scenarios {
        String variance;
        String metric;

        public scenarios(String variance, String metric, String uiPage, String endpoint, String jsonPath, String uiPath) {
            this.variance = variance;
            this.metric = metric;
            this.uiPage = uiPage;
            this.endpoint = endpoint;
            this.jsonPath = jsonPath;
            this.uiPath = uiPath;
        }

        String uiPage;
        String endpoint;
        String jsonPath;
        String uiPath;

        public String getVariance() {
            return variance;
        }

        public void setVariance(String variance) {
            this.variance = variance;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }

        public String getUiPage() {
            return uiPage;
        }

        public void setUiPage(String uiPage) {
            this.uiPage = uiPage;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getJsonPath() {
            return jsonPath;
        }

        public void setJsonPath(String jsonPath) {
            this.jsonPath = jsonPath;
        }

        public String getUiPath() {
            return uiPath;
        }

        public void setUiPath(String uiPath) {
            this.uiPath = uiPath;
        }

    }

    public static class runner {
        executor base;

        public runner(executor base, scenarios[] suite) {
            this.base = base;
            this.suite = suite;
        }

        public executor getBase() {
            return base;
        }

        public void setBase(executor base) {
            this.base = base;
        }

        public scenarios[] getSuite() {
            return suite;
        }

        public void setSuite(scenarios[] suite) {
            this.suite = suite;
        }

        scenarios[] suite;
    }

    public static class configReader {
        private static final String filePath = "src/test/java/config/comparatorConfig.json";
        private static final String jsonExecutionRootKey = "execution";
        private static final String jsonScnearioRootKey = "scenarios";

        public static runner fetchConfig() {
            try {
                Gson gson = new Gson();
                FileReader reader = new FileReader(filePath);
                JSONParser parser = new JSONParser();

                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;
                String execution = (jsonObject.get(jsonExecutionRootKey).toString());
                executor execute = gson.fromJson(execution, executor.class);
                String[] testcases = (execute.scenario);
                scenarios[] suite = new scenarios[testcases.length];
                int count = 0;
                for (String s : testcases) {
                    JSONObject jsonChildObject = (JSONObject) jsonObject.get(jsonScnearioRootKey);
                    String temp2 = (jsonChildObject.get(s).toString());
                    scenarios scenario = gson.fromJson(temp2, scenarios.class);
                    suite[count] = scenario;
                    count++;
                }

                runner run = new runner(execute, suite);

                return run;

            }catch(IOException | ParseException f)
            {
                f.printStackTrace();
            }
            return null;
        }
    }

}
