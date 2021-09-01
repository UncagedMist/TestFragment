package tbc.uncagedmist.testfragment.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tbc.uncagedmist.testfragment.Adapter.ListSourceAdapter;
import tbc.uncagedmist.testfragment.Common.Common;
import tbc.uncagedmist.testfragment.Interface.NewsService;
import tbc.uncagedmist.testfragment.Model.WebSite;
import tbc.uncagedmist.testfragment.R;

public class FirstFragment extends Fragment {

    RecyclerView listWebsite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    AlertDialog dialog;
    SwipeRefreshLayout swipeLayout;

    Context context;

    @Override
    public void onAttach(@NonNull Activity activity) {
        context = activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragment = inflater.inflate(R.layout.fragment_first, container, false);
        Paper.init(context);

        //Init Service
        mService = Common.getNewsService();

        //Init View
        swipeLayout = myFragment.findViewById(R.id.swipeRefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });

        listWebsite = myFragment.findViewById(R.id.list_source);
        listWebsite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        listWebsite.setLayoutManager(layoutManager);

        dialog = new SpotsDialog(context);


        loadWebsiteSource(false);

        return myFragment;
    }

    private void loadWebsiteSource(boolean isRefreshed) {
        if(!isRefreshed)
        {
            String cache = Paper.book().read("cache");
            if(cache != null && !cache.isEmpty()) // If have cache
            {
                WebSite website = new Gson().fromJson(cache,WebSite.class); // Convert cache from Json to Object
                adapter = new ListSourceAdapter(context,website);
                adapter.notifyDataSetChanged();
                listWebsite.setAdapter(adapter);
            }
            else // If not have cache
            {
                dialog.show();
                //Fetch new data
                mService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                        adapter  = new ListSourceAdapter(context,response.body());
                        adapter.notifyDataSetChanged();
                        listWebsite.setAdapter(adapter);

                        //Save to cache
                        Paper.book().write("cache",new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });
            }
        }
        else // If from Swipe to Refresh
        {

            dialog.show();
            //Fetch new data
            mService.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    adapter  = new ListSourceAdapter(context,response.body());
                    adapter.notifyDataSetChanged();
                    listWebsite.setAdapter(adapter);

                    //Save to cache
                    Paper.book().write("cache",new Gson().toJson(response.body()));

                    //Dismiss refresh progressring
                    swipeLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<WebSite> call, Throwable t) {

                }
            });

        }
    }
}