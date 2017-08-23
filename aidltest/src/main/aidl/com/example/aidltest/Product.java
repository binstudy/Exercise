package com.example.aidltest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LiuBin on 2017/8/23 16:04.
 */

public class Product implements Parcelable {
    public String name;
    public int price;

    @Override
    public int describeContents() {
        return 0;
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.name = in.readString();
        this.price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.price);
    }

    public void readFromParcel(Parcel dest) {
        name = dest.readString();
        price = dest.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
