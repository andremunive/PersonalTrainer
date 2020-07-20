package com.andres.personaltrainer.customersView;

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
import com.andres.personaltrainer.data.userData;

import java.util.ArrayList;

public class customersRecViewAdapter extends RecyclerView.Adapter<customersRecViewAdapter.ViewHolder>{

    private ArrayList<customer> customerArrayList =new ArrayList<>();

    private Context context;

    public customersRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customers_list_items,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(customerArrayList.get(position).getName());
        holder.user.setText(customerArrayList.get(position).getUser());
        holder.age.setText(customerArrayList.get(position).getAge());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        showUserDataView(customerArrayList.get(position).getUser());
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerArrayList.size();
    }

    public void setCustomerArrayList(ArrayList<customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //Views
        private TextView name;
        private TextView user;
        private TextView age;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Init Views
            name = itemView.findViewById(R.id.nameView);
            age = itemView.findViewById(R.id.ageView);
            user = itemView.findViewById(R.id.userView);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    private void showUserDataView(String usuario){
        Intent userIntent = new Intent(context, userData.class);
        userIntent.putExtra("Usuario", usuario);
        context.startActivity(userIntent);
    }

}
