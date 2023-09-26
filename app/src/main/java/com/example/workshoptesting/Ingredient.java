package com.example.workshoptesting;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class Ingredient implements Parcelable {
    public long id;
    public double amount;
    public String unit;
    public String unitLong;
    public String unitShort;
    public String aisle;
    public String name;
    public String original;
    public String originalName;
    public ArrayList<String> meta;
    public String extendedName;
    public String image;

    protected Ingredient(Parcel in) {
        id = in.readLong();
        amount = in.readDouble();
        unit = in.readString();
        unitLong = in.readString();
        unitShort = in.readString();
        aisle = in.readString();
        name = in.readString();
        original = in.readString();
        originalName = in.readString();
        meta = in.createStringArrayList();
        extendedName = in.readString();
        image = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeDouble(amount);
        dest.writeString(unit);
        dest.writeString(unitLong);
        dest.writeString(unitShort);
        dest.writeString(aisle);
        dest.writeString(name);
        dest.writeString(original);
        dest.writeString(originalName);
        dest.writeStringList(meta); // use writeStringList for ArrayList<String>
        dest.writeString(extendedName);
        dest.writeString(image);
    }
}
