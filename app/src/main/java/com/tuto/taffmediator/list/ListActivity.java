package com.tuto.taffmediator.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tuto.taffmediator.DI.ViewModelFactory;
import com.tuto.taffmediator.data.Item;
import com.tuto.taffmediator.R;
import com.tuto.taffmediator.main.MainActivity;
import com.tuto.taffmediator.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button returnButton;
    private MyAdapter myAdapter;
    private ListViewModel listViewModel;
//    List<Item> item = secondViewModel.getListItemLiveData();
//    List<Item> item = listViewModel.getListItemLiveData().getValue();
//    List<Item> items = listViewModel.getItems();
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerview);
        returnButton = findViewById(R.id.returnbutton);




        final ListViewModel listViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(ListViewModel.class);

        listViewModel.getViewStateItemLiveData().observe(this, new Observer<List<ItemViewState>>() {
            @Override
            public void onChanged(List<ItemViewState> itemsViewState) {
                myAdapter.submitList(itemsViewState);
            }
        });

        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}