package nvduy1997gmail.com.appnghenhac.service;

public class APIService {

    private static String url = "https://nhuvanduy97.000webhostapp.com/Server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(url).create(DataService.class);
    }
}
