package com.example.datastores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public abstract class GenAdapter<MODEL, BINDING> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<MODEL> mArrayList=new ArrayList<>();
    private ArrayList<MODEL> mArrayListFilter = new ArrayList<>();





    public GenAdapter(Context context, ArrayList<MODEL> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
        this.mArrayListFilter.addAll(arrayList);
    }

    public abstract int getLayoutResId();

    public abstract void onBindData(MODEL model, int position, BINDING dataBinding);

    public abstract void onItemClick(MODEL model, int position);
    public abstract ArrayList<MODEL> performFilter(String searchText, ArrayList<MODEL> originalList);


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutResId(), parent, false);
        RecyclerView.ViewHolder holder = new ItemViewHolder(dataBinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        onBindData(mArrayList.get(position), position, ((ItemViewHolder) holder).mDataBinding);

        ((ViewDataBinding) ((ItemViewHolder) holder).mDataBinding).getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(mArrayList.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public void addItems(ArrayList<MODEL> arrayList) {
        mArrayList = arrayList;
        this.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mArrayList.remove(position);
        this.notifyDataSetChanged();
    }
    public ArrayList<MODEL> getItems() {
        return mArrayList;
    }

    public MODEL getItem(int position) {
        return mArrayList.get(position);
    }

    public Context getContext() {
        return mContext;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        protected BINDING mDataBinding;

        public ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mDataBinding = (BINDING) binding;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                ArrayList<MODEL> arrayList = new ArrayList<>();

                try {
                    if (charSequence.toString().isEmpty()) {
                        arrayList = mArrayListFilter;
                    } else {
                        arrayList = performFilter(charSequence.toString().trim(), mArrayListFilter);
                        if (arrayList == null) {
                            arrayList.addAll(mArrayListFilter);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mArrayList = (ArrayList<MODEL>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
