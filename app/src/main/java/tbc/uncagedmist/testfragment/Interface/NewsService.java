package tbc.uncagedmist.testfragment.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import tbc.uncagedmist.testfragment.Common.Common;
import tbc.uncagedmist.testfragment.Model.News;
import tbc.uncagedmist.testfragment.Model.WebSite;

public interface NewsService {
    @GET("v2/sources?category=sports&language=en&apiKey="+ Common.API_KEY)
    Call<WebSite> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}
