package com.example.complainhere;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {

    ArrayList<complain> complainList;

    public RVAdapter(ArrayList<complain> complainList) {
        this.complainList = complainList;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RVViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int i) {
              complain complain =complainList.get(i);
              holder.category.setText(complain.getCategory());
              holder.personview.setText(complain.getPersonName());
              holder.objectimageview.setImageBitmap(complain.getCaptureimage());


    }

    @Override
    public int getItemCount() {
        return complainList.size();
    }

    public static class RVViewHolder extends RecyclerView.ViewHolder{
     List<complain> complainid ;

        TextView category , personview;
        ImageView objectimageview;
       public RVViewHolder(@NonNull View itemView) {
           super(itemView);
           category =itemView.findViewById(R.id.categoryinView);
           personview =itemView.findViewById(R.id.person);
           objectimageview= itemView.findViewById(R.id.imageinview);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //pass id to next activity to show detail
                   Intent i = new Intent(v.getContext(),Details.class);
                   i.putExtra("ID",complainid.get(getAdapterPosition()).getId() );
                   v.getContext().startActivity(i);
               }
           });





        }
    }
}
