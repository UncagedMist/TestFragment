package tbc.uncagedmist.testfragment.Common;

import tbc.uncagedmist.testfragment.Interface.NewsService;
import tbc.uncagedmist.testfragment.Remote.RetrofitClient;

public class Common {
    public static String CURRENT_WEB_HOT_URL;
    public static String CURRENT_SOURCE_ID;

    private static final String BASE_URL="https://newsapi.org/";

    public  static final String API_KEY="4f91baeac7724fe197d4de6e975d8b5b";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }


    //https://newsapi.org/v2/top-headlines/sources?category=sports&sortBy=latest&apiKey=4f91baeac7724fe197d4de6e975d8b5b
    public static String getAPIUrl(String category,String apiKEY)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(category)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }
}