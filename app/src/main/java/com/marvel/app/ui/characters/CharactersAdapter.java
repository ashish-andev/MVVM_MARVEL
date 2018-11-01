package com.marvel.app.ui.characters;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.marvel.app.data.model.MarvelCharacter;
import com.marvel.app.databinding.ItemCharacterBinding;
import com.marvel.app.ui.base.BaseViewHolder;

public class CharactersAdapter extends PagedListAdapter<MarvelCharacter, BaseViewHolder> {

    CharactersAdapter() {
        super(MarvelCharacter.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding itemCharacterBinding = ItemCharacterBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemCharacterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends BaseViewHolder {

        private final ItemCharacterBinding mBinding;

        ViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            mBinding.setViewModel(getItem(position));
            mBinding.executePendingBindings();
        }
    }
}
