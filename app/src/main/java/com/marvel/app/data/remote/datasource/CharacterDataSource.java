package com.marvel.app.data.remote.datasource;

import android.annotation.SuppressLint;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.marvel.app.data.model.MarvelCharacter;
import com.marvel.app.ui.base.BaseViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;


public class CharacterDataSource extends PageKeyedDataSource<Integer, MarvelCharacter> {

    private BaseViewModel viewModel;
    private Gson gson;
    private int sourceIndex;
    private CompositeDisposable compositeDisposable;

    CharacterDataSource(BaseViewModel viewModel, CompositeDisposable compositeDisposable) {
        this.viewModel = viewModel;
        this.compositeDisposable = compositeDisposable;
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = builder.setLenient().create();
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MarvelCharacter> callback) {

        viewModel.getDataManager().getCharacters(sourceIndex)
                .subscribeOn(viewModel.getSchedulerProvider().io())
                .observeOn(viewModel.getSchedulerProvider().ui())
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    viewModel.setIsLoading(true);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            viewModel.setIsLoading(false);

                            JSONObject object = new JSONObject(gson.toJson(result));
                            JSONArray array = object.getJSONObject("data").getJSONArray("results");

                            ArrayList<MarvelCharacter> arrayList = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                JSONObject thumbnailObject = jsonObject.getJSONObject("thumbnail");
                                arrayList.add(new MarvelCharacter(jsonObject.optInt("id"),
                                        jsonObject.optString("name"), thumbnailObject
                                        .optString("path") + "." + thumbnailObject.optString("extension")));
                            }

                            sourceIndex += 20;
                            callback.onResult(arrayList, null, sourceIndex);
                        },
                        throwable ->
                                viewModel.setIsLoading(false)

                );

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MarvelCharacter> callback) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MarvelCharacter> callback) {

        viewModel.getDataManager().getCharacters(params.key)
                .subscribeOn(viewModel.getSchedulerProvider().io())
                .observeOn(viewModel.getSchedulerProvider().ui())
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    viewModel.setIsLoading(true);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            viewModel.setIsLoading(false);

                            JSONObject object = new JSONObject(gson.toJson(result));
                            JSONArray array = object.getJSONObject("data").getJSONArray("results");

                            ArrayList<MarvelCharacter> arrayList = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                JSONObject thumbnailObject = jsonObject.getJSONObject("thumbnail");
                                arrayList.add(new MarvelCharacter(jsonObject.optInt("id"),
                                        jsonObject.optString("name"), thumbnailObject
                                        .optString("path") + "." + thumbnailObject.optString("extension")));
                            }

                            callback.onResult(arrayList, params.key + 20);

                        },
                        throwable ->
                                viewModel.setIsLoading(false)
                );
    }
}
