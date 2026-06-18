package com.example.pr23kablukovpr23_102;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsModel> newsList;

    public NewsAdapter(List<NewsModel> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel news = newsList.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDesc.setText(news.getDescription());
        holder.tvPrice.setText(news.getPrice());
        holder.itemView.findViewById(R.id.clNewsBg).setBackgroundColor(news.getBackgroundColor());
    }

    @Override
    public int getItemCount() { return newsList.size(); }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, tvPrice;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvDesc = itemView.findViewById(R.id.tvNewsDesc);
            tvPrice = itemView.findViewById(R.id.tvNewsPrice);
        }
    }
}