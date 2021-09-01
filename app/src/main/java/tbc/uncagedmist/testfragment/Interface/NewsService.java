package tbc.uncagedmist.testfragment.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import tbc.uncagedmist.testfragment.Model.News;
import tbc.uncagedmist.testfragment.Model.WebSite;

public interface NewsService {
    @GET("v1/sources?language=en")
    Call<WebSite> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}
