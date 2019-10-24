package com.haanhgs.recyclersearchviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final List<String> test = new ArrayList<>(Arrays.asList("Lord of the Ring",
            "My lord", "Lord of the World", "My Try", "The godfather"));
    private Adapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tbrMain = findViewById(R.id.tbrMain);
        setSupportActionBar(tbrMain);

        RecyclerView rvMain = findViewById(R.id.rvContent);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapter(test);
        rvMain.setAdapter(adapter);

//        SearchView searchView = findViewById(R.id.svMain);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        MenuItem item = menu.findItem(R.id.mniSearch);
        searchView = (SearchView) item.getActionView();
//        SearchManager manager = (SearchManager)getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(true);
//        searchView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
//        searchView.setMaxWidth(Integer.MAX_VALUE);

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
        if (!searchView.isIconified()){
            searchView.setIconified(true);
        }else {
            super.onBackPressed();
        }
    }
}
