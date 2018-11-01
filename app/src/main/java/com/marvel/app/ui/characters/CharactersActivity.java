package com.marvel.app.ui.characters;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.marvel.app.BR;
import com.marvel.app.R;
import com.marvel.app.databinding.ActivityCharactersBinding;
import com.marvel.app.ui.base.BaseBindingActivity;


public class CharactersActivity extends BaseBindingActivity<ActivityCharactersBinding,
        CharactersViewModel> {

    private CharactersViewModel mCharactersViewModel;
    private ActivityCharactersBinding mCharactersBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_characters;
    }

    @Override
    public CharactersViewModel getViewModel() {
        return mCharactersViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCharactersViewModel = new CharactersViewModel();
        super.onCreate(savedInstanceState);
        mCharactersBinding = getViewDataBinding();


        CharactersAdapter adapter = new CharactersAdapter();
        setSupportActionBar(mCharactersBinding.toolbar);
        mCharactersBinding.recyclerView.setLayoutManager(new LinearLayoutManager(CharactersActivity.this));
        mCharactersBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mCharactersBinding.recyclerView.setAdapter(adapter);
        mCharactersViewModel.getListLiveData().observe(this, adapter::submitList);
    }
}
