package com.andres.personaltrainer.data.userProgress.progressList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.andres.personaltrainer.R;
import com.andres.personaltrainer.customersView.customersRecViewAdapter;
import com.andres.personaltrainer.data.userData;

import java.util.ArrayList;

public class progressRecViewAdapter extends RecyclerView.Adapter<progressRecViewAdapter.ViewHolder> {

    private ArrayList<weeks> weeksArrayList = new ArrayList<>();

    private Context context;

    public progressRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_list_items,
                parent, false);
        progressRecViewAdapter.ViewHolder holder = new progressRecViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.weekTittle.setText(weeksArrayList.get(position).getNumberWeek());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semanaN = weeksArrayList.get(position).getNumberWeek();
                String user = weeksArrayList.get(position).getUser();
                showWeekProgressView(user, semanaN);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weeksArrayList.size();
    }

    public void setWeeksArrayList(ArrayList<weeks> weeksArrayList) {
        this.weeksArrayList = weeksArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView weekTittle;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekTittle = itemView.findViewById(R.id.numberWeek);
            parent = itemView.findViewById(R.id.progress_parent);
        }
    }

    private void showWeekProgressView(String usuario, String week){
        Intent userIntent = new Intent(context, weekProgress.class);
        userIntent.putExtra("Usuario", usuario);
        userIntent.putExtra("Semana", week);
        context.startActivity(userIntent);
    }

}
