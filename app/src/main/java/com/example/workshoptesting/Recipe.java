package com.example.workshoptesting;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    public long id;
    public String title;
    public String image;
    public String imageType;
    public int usedIngredientCount;
    public int missedIngredientCount;
    public ArrayList<Ingredient> missedIngredients;
    public ArrayList<Ingredient> usedIngredients;
    public ArrayList<Ingredient> unusedIngredients;
    public int likes;

    protected Recipe(Parcel in) {
        id = in.readLong();
        title = in.readString();
        image = in.readString();
        imageType = in.readString();
        usedIngredientCount = in.readInt();
        missedIngredientCount = in.readInt();
        missedIngredients = in.createTypedArrayList(Ingredient.CREATOR);
        usedIngredients = in.createTypedArrayList(Ingredient.CREATOR);
        unusedIngredients = in.createTypedArrayList(Ingredient.CREATOR);
        likes = in.readInt();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(imageType);
        dest.writeInt(usedIngredientCount);
        dest.writeInt(missedIngredientCount);
        dest.writeTypedList(missedIngredients);
        dest.writeTypedList(usedIngredients);
        dest.writeTypedList(unusedIngredients);
        dest.writeInt(likes);
    }
}
