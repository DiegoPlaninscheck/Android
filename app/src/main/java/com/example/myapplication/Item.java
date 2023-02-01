package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Item implements Parcelable {

    String nome, preco;
    int image;
    ImageView imageGeted;

    public Item(String nome, String preco, int image) {
        this.nome = nome;
        this.preco = preco;
        this.image = image;
    }

    public Item(String nome, String preco, ImageView imageGeted) {
        this.nome = nome;
        this.preco = preco;
        this.imageGeted = imageGeted;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", preco='" + preco + '\'' +
                ", image=" + image +
                '}';
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    protected Item(Parcel in) {
        nome = in.readString();
        preco = in.readString();
        image = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(preco);
        parcel.writeInt(image);
    }
}

