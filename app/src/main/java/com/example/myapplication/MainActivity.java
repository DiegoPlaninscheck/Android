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
    public static ArrayList<Item> items = new ArrayList<Item>();
    MyAdapter adpter;
    RecyclerView recyclerView;

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

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adpter);

        addButton.setOnClickListener(view -> addItem());
    }

    private void addItem() {
        Intent intent = new Intent(this, AddItem.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemLongClick(int position) {
        items.remove(position);
        this.recyclerView.getAdapter().notifyItemRemoved(position);
    }
}
