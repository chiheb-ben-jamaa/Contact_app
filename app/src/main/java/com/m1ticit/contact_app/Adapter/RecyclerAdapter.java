package com.m1ticit.contact_app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.m1ticit.contact_app.Interfaces.RecyclerViewClickInterface;
import com.m1ticit.contact_app.R;
import com.m1ticit.contact_app.activites.contact;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private RecyclerViewClickInterface recyclerViewClickInterface;
    private Context mContext;
    ArrayList<contact> list = new ArrayList<>();




    public RecyclerAdapter( ArrayList<contact> list, RecyclerViewClickInterface recyclerViewClickInterface) {

        this.list = list;
        this.recyclerViewClickInterface = recyclerViewClickInterface;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.nom.setText(list.get(position).getNom()+" "+list.get(position).getPrenom());
        holder.profession.setText(list.get(position).getProfession());
        //TODO:Adding Some Logic to handle the color category selector :
        category_color_bg(holder.category_color,list.get(position).getProfession());
        String imageUri = "https://image.flaticon.com/icons/svg/1077/1077012.svg";
        //Picasso.with(ctx).load(imageUri).into(holder.icon);

    }

    private void category_color_bg(TextView category_color, String profession) {
        switch (profession){
            case "manager":
                category_color.setBackgroundResource(R.drawable.ic_color_category2);
                break;
            case  "friend":
                category_color.setBackgroundResource(R.drawable.ic_color_category4);
                break;
            case "designer":
                category_color.setBackgroundResource(R.drawable.ic_color_category3);
                break;
            case "family":
                category_color.setBackgroundResource(R.drawable.ic_color_category5);
                break;
            default :
                category_color.setBackgroundResource(R.drawable.ic_color_category1);
        }
    }

    public RecyclerAdapter(ArrayList<contact> getlist){
        this.list=getlist;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nom;
        public ImageView icon;
        public TextView profession;
        public TextView category_color;
        public ViewHolder(@NonNull final View itemView) {

            super(itemView);

            category_color=(TextView)itemView.findViewById(R.id.category_color);
            nom=(TextView)itemView.findViewById(R.id.nom);
            profession=(TextView)itemView.findViewById(R.id.profession);
            icon=(ImageView)itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recyclerViewClickInterface.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });



        }
    }
}
