package com.haanhgs.recyclersearchviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private List<String> list;
    private List<String> filterList;

    public Adapter(List<String> list){
        this.list = list;
        filterList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRv.setText(filterList.get(position));
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRv = itemView.findViewById(R.id.tvRvContent);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence query) {
                List<String> temp = new ArrayList<>();
                if (query.length() == 0){
                    temp = list;
                }else {
                    for (int i = 0; i < list.size(); i++){
                        if (list.get(i).toLowerCase().contains(query.toString().toLowerCase())){
                            temp.add(list.get(i));
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = temp;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<String>temp = new ArrayList<>();
                for (Object object: (List<?>)results.values){
                    temp.add((String)object);
                }
                filterList = temp;
                notifyDataSetChanged();
            }
        };
    }
}
