package com.efremov.weather.core.model.binding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.efremov.weather.BR;
import com.efremov.weather.core.model.entities.Fact;
import com.efremov.weather.list.WeekListFragmentVM;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerBindingAdapter extends RecyclerView.Adapter<RecyclerBindingAdapter.GenericViewHolder> {

    private int layoutId;
    private List<Fact> factList;
    private WeekListFragmentVM viewModel;

    public RecyclerBindingAdapter(@LayoutRes int layoutId, WeekListFragmentVM viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return factList == null ? 0 : factList.size();
    }

    @NotNull
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    //TODO: rem
    public void setForecasts(List<Fact> forecasts) {
        this.factList = forecasts;
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(WeekListFragmentVM viewModel, Integer position) {
            viewModel.getHoursAt(position);
            binding.setVariable(com.efremov.weather.BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}