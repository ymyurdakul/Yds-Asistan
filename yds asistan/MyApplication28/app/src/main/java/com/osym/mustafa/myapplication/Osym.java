package com.osym.mustafa.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kazy.lx.LxWebView;

public class Osym extends AppCompatActivity {

    @BindView(R.id.osym_toolbar)Toolbar toolbar;
    @BindView(R.id.webview_view)LxWebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osym);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        webView.loadUrl("https://www.osym.gov.tr/TR,8860/hakkinda.html");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.osym_topmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.osym_topmenuitem_refresh:
                webView.reload();
                break;
            case R.id.osym_topmenuitem_zoomin:
                webView.zoomIn();
                break;
            case R.id.osym_topmenuitem_zoomout:
                webView.zoomOut();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
