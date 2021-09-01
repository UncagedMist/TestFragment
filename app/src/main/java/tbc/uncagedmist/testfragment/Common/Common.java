package tbc.uncagedmist.testfragment.Common;

import tbc.uncagedmist.testfragment.Interface.IconBetterIdeaService;
import tbc.uncagedmist.testfragment.Interface.NewsService;
import tbc.uncagedmist.testfragment.Remote.IconBetterIdeaClient;
import tbc.uncagedmist.testfragment.Remote.RetrofitClient;

public class Common {
    public static String CURRENT_WEB_HOT_URL;
    public static  String CURRENT_SORT_BY;
    public static String CURRENT_SOURCE_ID;

    private static final String BASE_URL="https://newsapi.org/";

    public  static final String API_KEY="4f91baeac7724fe197d4de6e975d8b5b";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService()
    {
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    public static String getAPIUrl(String source,String sortBy,String apiKEY)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v1/articles?source=");
        return apiUrl.append(source)
                .append("&sortBy=")
                .append(sortBy)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }


}