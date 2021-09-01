package tbc.uncagedmist.testfragment.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dmax.dialog.SpotsDialog;
import tbc.uncagedmist.testfragment.Common.Common;
import tbc.uncagedmist.testfragment.R;

public class ThirdFragment extends Fragment {

    WebView webView;
    AlertDialog dialog;

    Context context;

    @Override
    public void onAttach(@NonNull Activity activity) {
        context = activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragment = inflater.inflate(R.layout.fragment_third, container, false);

        dialog = new SpotsDialog(context);
        dialog.show();

        //WebView
        webView = myFragment.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
            }
        });

        if(Common.CURRENT_WEB_HOT_URL != null)
            webView.loadUrl(Common.CURRENT_WEB_HOT_URL);

        return myFragment;
    }
}