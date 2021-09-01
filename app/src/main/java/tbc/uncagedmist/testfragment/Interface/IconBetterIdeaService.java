package tbc.uncagedmist.testfragment.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import tbc.uncagedmist.testfragment.Model.IconBetterIdea;

public interface IconBetterIdeaService {
    @GET
    Call<IconBetterIdea> getIconUrl(@Url String url);
}
