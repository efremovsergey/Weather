package com.efremov.weather.base.model.binding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.efremov.weather.BR;
import com.efremov.weather.base.model.entities.Forecasts;
import com.efremov.weather.list.WeekListFragment;
import com.efremov.weather.list.WeekListFragmentVM;

import java.util.List;

public class RecyclerBindingAdapter extends RecyclerView.Adapter<RecyclerBindingAdapter.GenericViewHolder> {

    private int layoutId;
    private List<Forecasts> forecasts;
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
        return forecasts == null ? 0 : forecasts.size();
    }

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

    public void setForecasts(List<Forecasts> forecasts) {
        this.forecasts = forecasts;
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(WeekListFragmentVM viewModel, Integer position) {
            viewModel.getForecastAt(position);
            binding.setVariable(com.efremov.weather.BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}