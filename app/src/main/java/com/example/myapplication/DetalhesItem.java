package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalhesItem extends AppCompatActivity {

    private TextView nome, preco;
    private ImageView foto;
    private Button voltarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_item);

        nome = findViewById(R.id.nome);
        preco = findViewById(R.id.preco);
        foto = findViewById(R.id.foto);
        voltarButton = findViewById(R.id.buttonVoltar);

        carregarItem();

        voltarButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void carregarItem() {
        String carregarNome = getIntent().getExtras().getString("nome");
        String carregarPreco = getIntent().getExtras().getString("preco");
        int carregarFoto = getIntent().getExtras().getInt("foto");

        nome.setText(carregarNome);
        preco.setText(carregarPreco);
        foto.setImageResource(carregarFoto);
    }
}