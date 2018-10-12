package com.example.jessi.servicesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "MyViewHolder";
        public TextView name;
        public TextView description;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: ");
            this.name = itemView.findViewById(R.id.tv_categoryname);
            this.description = itemView.findViewById(R.id.tv_categorydescription);
            this.imageView = itemView.findViewById(R.id.iv_category);
        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_category, viewGroup, false);
        MyAdapter.MyViewHolder holder = new MyAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.name.setText(categoryModelList.get(position).getName());
        holder.description.setText(categoryModelList.get(position).getDescription());
        Picasso.get().load(categoryModelList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
}
