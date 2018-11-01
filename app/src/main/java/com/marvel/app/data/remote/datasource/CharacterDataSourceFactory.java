package com.marvel.app.data.remote.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.marvel.app.data.model.MarvelCharacter;
import com.marvel.app.ui.base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;


public class CharacterDataSourceFactory extends DataSource.Factory<Integer, MarvelCharacter> {

    private MutableLiveData<CharacterDataSource> liveData;
    private BaseViewModel viewModel;
    private CompositeDisposable compositeDisposable;

    public CharacterDataSourceFactory(BaseViewModel viewModel, CompositeDisposable compositeDisposable) {
        this.viewModel = viewModel;
        this.compositeDisposable = compositeDisposable;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<CharacterDataSource> getMutableLiveData() {
        return liveData;
    }

    @Override
    public DataSource<Integer, MarvelCharacter> create() {
        CharacterDataSource dataSourceClass = new CharacterDataSource(viewModel, compositeDisposable);
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
