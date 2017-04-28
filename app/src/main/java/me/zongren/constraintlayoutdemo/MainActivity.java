package me.zongren.constraintlayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private enum STYLE{
        LIST,
        GRID;
    }
    private STYLE style = STYLE.LIST;
    RecyclerView recyclerView;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        updateRecyclerView(recyclerView);
    }

    private void updateRecyclerView(RecyclerView recyclerView) {
        switch (style){
            case LIST:{
                adapter.layout = R.layout.activity_main_item_list;
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                adapter.notifyDataSetChanged();
                break;
            }
            case GRID: {
                adapter.layout = R.layout.activity_main_item_grid;
                GridLayoutManager layoutManager = new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(layoutManager);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.list:
                showList();
                return true;
            case R.id.grid:
                showGrid();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showGrid() {
        style = STYLE.GRID;
        updateRecyclerView(recyclerView);
    }

    private void showList() {
        style = STYLE.LIST;
        updateRecyclerView(recyclerView);
    }
}
