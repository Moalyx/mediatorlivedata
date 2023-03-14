package com.tuto.taffmediator.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.tuto.taffmediator.R;
import com.tuto.taffmediator.data.Item;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ListAdapter<ItemViewState, MyAdapter.MyViewHolder> {



    public MyAdapter(){
        super (new ItemCallback());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(getItem(position));

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView unitprice;
        TextView quantity;
        TextView totalprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            unitprice = itemView.findViewById(R.id.unitprice);
            quantity = itemView.findViewById(R.id.quantity);
            totalprice = itemView.findViewById(R.id.totalprice);

        }

        public void onBind (ItemViewState item){
            name.setText(item.getName());
            unitprice.setText(item.getUnitPrice());
            quantity.setText(item.getQuantity());
            totalprice.setText(item.getTotal());

        }
    }

    private static class ItemCallback extends DiffUtil.ItemCallback<ItemViewState> {

        @Override
        public boolean areItemsTheSame(@NonNull ItemViewState oldItem, @NonNull ItemViewState newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ItemViewState oldItem, @NonNull ItemViewState newItem) {
            return oldItem.equals(newItem);
        }
    }
}
