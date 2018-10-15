package com.example.jessi.servicesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";
    private List<CategoryModel> categoryModelList;
    private Context context;

    public MyAdapter(Context context,List<CategoryModel> categoryModelList ) {
        Log.d(TAG, "MyAdapter: ");
        this.context = context;
        this.categoryModelList = categoryModelList;
        Log.d(TAG, "MyAdapter: CatModList = " + categoryModelList.size());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "MyViewHolder";
        public TextView name;
        public TextView description;
        ImageView imageView;
        List<CategoryModel> categoryModelList;
        Context context;

        public MyViewHolder(@NonNull View itemView, Context context, List<CategoryModel> categoryModels) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: ");
            categoryModelList = categoryModels;
            this.context = context;

            itemView.setOnClickListener(this);
            this.name = itemView.findViewById(R.id.tv_categoryname);
            this.description = itemView.findViewById(R.id.tv_categorydescription);
            this.imageView = itemView.findViewById(R.id.iv_category);
            //this.name.setAlpha(.80f);
            this.name.setAllCaps(true);
        }

        @Override
        public void onClick(View view) {
            int position =  getAdapterPosition();
            CategoryModel  categoryModel = this.categoryModelList.get(position);
            Toast.makeText(context, "Clicked on" + categoryModel.getName(), Toast.LENGTH_SHORT).show();


        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_category, viewGroup, false);
        MyAdapter.MyViewHolder holder = new MyAdapter.MyViewHolder(view,context, categoryModelList);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String temText = "Description: ... ";
        Log.d(TAG, "onBindViewHolder: ");
        holder.name.setText(categoryModelList.get(position).getName());
        holder.description.setText(temText + categoryModelList.get(position).getDescription());
        Picasso.get().load(categoryModelList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
}
