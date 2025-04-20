package com.example.oop_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.oop_project.model.Lutemon;
import java.util.List;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.LutemonViewHolder> {
    private final List<Lutemon> lutemonList;

    public LutemonAdapter(List<Lutemon> list) {
        this.lutemonList = list;
    }

    @Override
    public LutemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lutemon, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder( LutemonViewHolder holder, int position) {
        Lutemon l = lutemonList.get(position);
        holder.name.setText(l.getName());
        holder.stats.setText("XP: " + l.getXp() + ", HP: " + l.getHp());
    }


    @Override
    public int getItemCount() {
        return lutemonList.size();
    }

    static class LutemonViewHolder extends RecyclerView.ViewHolder {
        TextView name, stats;

        public LutemonViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lutemon_name);
            stats = itemView.findViewById(R.id.lutemon_stats);
        }
    }
}
