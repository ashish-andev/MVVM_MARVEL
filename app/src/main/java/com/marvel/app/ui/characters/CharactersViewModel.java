package com.marvel.app.ui.characters;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.marvel.app.data.model.MarvelCharacter;
import com.marvel.app.data.remote.datasource.CharacterDataSourceFactory;
import com.marvel.app.ui.base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class CharactersViewModel extends BaseViewModel<CharactersNavigator> {

    private CharacterDataSourceFactory dataSourceFactory;
    private LiveData<PagedList<MarvelCharacter>> listLiveData;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    CharactersViewModel() {
        dataSourceFactory = new CharacterDataSourceFactory(this, compositeDisposable);
        initializePaging();
    }

    private void initializePaging() {
        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .build();

        listLiveData = new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig)
                .build();
    }

    public LiveData<PagedList<MarvelCharacter>> getListLiveData() {
        return listLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
