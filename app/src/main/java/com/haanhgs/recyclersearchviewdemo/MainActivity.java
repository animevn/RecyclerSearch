package com.haanhgs.recyclersearchviewdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final List<String> test = new ArrayList<>(Arrays.asList("Lord of the Ring",
            "My lord", "Lord of the World", "My Try", "The godfather"));

    @BindView(R.id.tbrMain)
    Toolbar tbrMain;
    @BindView(R.id.rvContent)
    RecyclerView rvContent;

    private Adapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(tbrMain);
        adapter = new Adapter(test);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        MenuItem item = menu.findItem(R.id.mniSearch);
        searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }else {
            super.onBackPressed();
        }
    }
}
