package com.marvel.app.data.model;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class MarvelCharacter {
    public int id;
    public String name;
    public String thumbnail;

    public MarvelCharacter(int id, String name, String thumbnail) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public static DiffUtil.ItemCallback<MarvelCharacter> DIFF_CALLBACK = new DiffUtil.ItemCallback<MarvelCharacter>() {
        @Override
        public boolean areItemsTheSame(@NonNull MarvelCharacter oldItem, @NonNull MarvelCharacter newItem) {
            return oldItem.name.equals(newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull MarvelCharacter oldItem, @NonNull MarvelCharacter newItem) {
            return oldItem.equals(newItem);
        }
    };


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        MarvelCharacter article = (MarvelCharacter) obj;
        return article.name.equals(this.name);
    }
}
