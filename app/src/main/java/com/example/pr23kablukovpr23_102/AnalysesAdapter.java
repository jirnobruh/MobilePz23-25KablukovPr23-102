package com.example.pr23kablukovpr23_102;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AnalysesAdapter extends RecyclerView.Adapter<AnalysesAdapter.AnalysesViewHolder> {
    private List<AnalysisModel> allAnalyses;
    private List<AnalysisModel> filteredAnalyses;

    public AnalysesAdapter(List<AnalysisModel> analyses) {
        this.allAnalyses = analyses;
        this.filteredAnalyses = new ArrayList<>(analyses);
    }

    public void filter(String category, String query) {
        filteredAnalyses.clear();
        for (AnalysisModel item : allAnalyses) {
            boolean matchesCategory = category.equals("Популярные") || item.getCategory().equalsIgnoreCase(category);
            boolean matchesQuery = item.getTitle().toLowerCase().contains(query.toLowerCase());
            if (matchesCategory && matchesQuery) {
                filteredAnalyses.add(item);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnalysesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnalysesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_analysis, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnalysesViewHolder holder, int position) {
        AnalysisModel analysis = filteredAnalyses.get(position);
        holder.tvTitle.setText(analysis.getTitle());
        holder.tvDays.setText(analysis.getDays());
        holder.tvPrice.setText(analysis.getPrice());
    }

    @Override
    public int getItemCount() { return filteredAnalyses.size(); }

    static class AnalysesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDays, tvPrice;
        public AnalysesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvAnalysisTitle);
            tvDays = itemView.findViewById(R.id.tvAnalysisDays);
            tvPrice = itemView.findViewById(R.id.tvAnalysisPrice);
        }
    }
}