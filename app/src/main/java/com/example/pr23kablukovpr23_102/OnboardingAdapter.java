package com.example.pr23kablukovpr23_102;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private final String[] titles = {"Анализы", "Уведомления", "Мониторинг"};
    private final String[] descriptions = {
            "Экспресс сбор и получение проб",
            "Вы быстро узнаете о результатах",
            "Наши врачи всегда наблюдают\nза вашими показателями здоровья"
    };
    private final int[] images = {
            R.drawable.picture_on_first_page,
            R.drawable.picture_on_second_page,
            R.drawable.picture_jn_third_page
    };

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onboarding, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.bind(titles[position], descriptions[position], images[position], position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView ivImage;
        View dot1, dot2, dot3;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
            dot1 = itemView.findViewById(R.id.dot1);
            dot2 = itemView.findViewById(R.id.dot2);
            dot3 = itemView.findViewById(R.id.dot3);
        }

        void bind(String title, String description, int imageRes, int position) {
            tvTitle.setText(title);
            tvDescription.setText(description);
            ivImage.setImageResource(imageRes);

            dot1.setBackgroundResource(position == 0 ? R.drawable.dot_active : R.drawable.dot_inactive);
            dot2.setBackgroundResource(position == 1 ? R.drawable.dot_active : R.drawable.dot_inactive);
            dot3.setBackgroundResource(position == 2 ? R.drawable.dot_active : R.drawable.dot_inactive);
        }
    }
}