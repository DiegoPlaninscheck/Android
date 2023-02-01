package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private Button addButton;
    static ArrayList<Item> items = new ArrayList<Item>();
    MyAdapter adpter;

    static {
        items.add(new Item("Teste 1", "R$150", R.drawable.book));
        items.add(new Item("Teste 2", "R$100", R.drawable.book));
        items.add(new Item("Teste 3", "R$50", R.drawable.book));
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.button);

        adpter = new MyAdapter(this, items, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

//        Bundle bundle = getIntent().getParcelableExtra("itemsList");
//
//        if (bundle != null) {
//            bundle = getIntent().getParcelableExtra("itemsList");
//            ArrayList<Item> itemsList = bundle.getParcelableArrayList("itemsList");
//            System.out.println(itemsList.toString());
//
//            items = itemsList;
//        }
//
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adpter);
//
        addButton.setOnClickListener(view -> addItem());
    }

    private void addItem() {
        Intent intent = new Intent(this, AddItem.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("items", items);
        intent.putExtra("items", bundle);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        adpter.notifyDataSetChanged();
    }

    @Override
    public void onItemLongClick(int position) {
        items.remove(position);
        adpter.notifyItemRemoved(position);

        System.out.println(items.toString());
    }
}
