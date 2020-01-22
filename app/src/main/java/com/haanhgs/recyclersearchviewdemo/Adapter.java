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
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private String query;
    private List<String> movieList;
    private List<String> filterList;

    public Adapter(List<String> movieList) {
        this.movieList = movieList;
        filterList = movieList;
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
        holder.tvRvContent.setText(filterList.get(position));
        Repo.highlightText(holder.tvRvContent, query);
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvRvContent)
        TextView tvRvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<String> searchByQuery(CharSequence constraint){
        List<String> temp = new ArrayList<>();
        if (constraint.length() == 0){
            temp = movieList;
        }else {
            query = constraint.toString().toLowerCase();
            for (String string:movieList){
                if (string.toLowerCase().contains(query))temp.add(string);
            }
        }
        return temp;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                results.values = searchByQuery(constraint);
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<String> temp = new ArrayList<>();
                List<?> filter = (List<?>)results.values;
                for (Object o:filter){
                    temp.add((String)o);
                }
                filterList = temp;
                notifyDataSetChanged();
            }
        };
    }
}
