package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {

    EditText nome, preco;
    Button buttonAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nome = findViewById(R.id.editTextNome);
        preco = findViewById(R.id.editTextPreco);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(view -> addItem());
    }

    private void addItem() {
        Bundle bundle = getIntent().getParcelableExtra("items");
        ArrayList<Item> items = bundle.getParcelableArrayList("items");
        Item item = new Item(nome.getText().toString(), preco.getText().toString(), R.drawable.book);
        items.add(item);
        Intent intent = new Intent(this, MainActivity.class);
        bundle.putParcelableArrayList("itemsList", items);
        intent.putExtra("itemsList", bundle);
        startActivity(intent);
    }
}