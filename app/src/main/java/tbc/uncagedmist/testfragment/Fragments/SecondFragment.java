package tbc.uncagedmist.testfragment.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import tbc.uncagedmist.testfragment.Common.Common;
import tbc.uncagedmist.testfragment.R;

public class SecondFragment extends Fragment {

    View myFragment;
    WebView webView;
    TextView txtUrl;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_second, container, false);

        webView = myFragment.findViewById(R.id.webView);
        txtUrl = myFragment.findViewById(R.id.txtUrl);
        progressBar = myFragment.findViewById(R.id.progressBar);

        txtUrl.setText(Common.CURRENT_URL);
        webView.loadUrl(Common.CURRENT_URL);

        webView.setWebViewClient(new MyWebViewClient());

        return myFragment;
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}