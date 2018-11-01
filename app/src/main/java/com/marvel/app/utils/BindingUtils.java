package com.marvel.app.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }


    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url)
                .apply(new RequestOptions().override(480, 300).centerCrop())
                .into(imageView);
    }

    @BindingAdapter("imageDrawable")
    public static void setImageDrawable(ImageView imageView, @DrawableRes int id) {
        Glide.with(imageView.getContext()).load(id).into(imageView);
    }
}
