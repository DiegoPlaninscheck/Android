package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {

    EditText nome, preco;

    ImageButton addImage;

    ImageView imageGeted;

    Button buttonAdd;

    Uri selectedImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nome = findViewById(R.id.editTextNome);
        preco = findViewById(R.id.editTextPreco);
        buttonAdd = findViewById(R.id.buttonAdd);
        addImage = findViewById(R.id.addImage);
        imageGeted = findViewById(R.id.imageGeted);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        buttonAdd.setOnClickListener(view -> addItem(imageGeted));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            imageGeted.setImageURI(selectedImage);
        }
    }

    private void addItem(ImageView imageGeted) {
        Item item = new Item(nome.getText().toString(), preco.getText().toString(), imageGeted);
        MainActivity.items.add(item);

        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 2);
//        Bundle bundle = getIntent().getParcelableExtra("items");
//        ArrayList<Item> items = bundle.getParcelableArrayList("items");
//        Item item = new Item(nome.getText().toString(), preco.getText().toString(), imageGeted.getId());
//        items.add(item);
//        Intent intent = new Intent(this, MainActivity.class);
//        bundle.putParcelableArrayList("itemsList", items);
//        intent.putExtra("itemsList", bundle);
//        startActivityForResult(intent, 2);
    }
}