package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> items;
    private final RecyclerViewInterface recyclerViewInterface;

    public MyAdapter(Context context, ArrayList<Item> items, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nome.setText(items.get(i).getNome());
        myViewHolder.preco.setText(items.get(i).getPreco());
        myViewHolder.excluirItem.setImageResource(R.drawable.baseline_delete_24);
        myViewHolder.image.setImageResource(items.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image, excluirItem;
        TextView nome, preco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageview);
            nome = itemView.findViewById(R.id.nome);
            preco = itemView.findViewById(R.id.preco);
            excluirItem = itemView.findViewById(R.id.excluirItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, DetalhesItem.class);
                    String nome = items.get(position).getNome();
                    String preco = items.get(position).getPreco();
                    int foto = items.get(position).getImage();
                    intent.putExtra("nome", nome);
                    intent.putExtra("preco", preco);
                    intent.putExtra("foto", foto);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            excluirItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemLongClick(pos);
                        }
                    }
                }
            });
        }
    }
}

