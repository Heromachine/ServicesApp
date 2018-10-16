package com.example.jessi.servicesapp.category;

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

import com.example.jessi.servicesapp.R;
import com.example.jessi.servicesapp.subcategory.SubCategoryActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private static final String TAG = "CategoryAdapter";
    private List<CategoryModel> categoryModelList;
    private Context context;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList ) {
        Log.d(TAG, "CategoryAdapter: ");
        this.context = context;
        this.categoryModelList = categoryModelList;
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
            this.name = itemView.findViewById(R.id.tv_category_name);
            this.description = itemView.findViewById(R.id.tv_category_description);
            this.imageView = itemView.findViewById(R.id.iv_category);
            this.name.setAllCaps(true);
            //this.name.setAlpha(.80f);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position =  getAdapterPosition();
            CategoryModel  categoryModel = this.categoryModelList.get(position);
            Toast.makeText(context, "Clicked on" + categoryModel.getCategoryId(), Toast.LENGTH_SHORT).show();
            startNextActivity(
                    view.getContext(),
                    SubCategoryActivity.class,
                    categoryModel.getCategoryId(),
                    categoryModel.getCategoryName());
        }

        private void startNextActivity(Context context, Class next, String subCategory, String subCategoryTitle){

            Intent mIntent = new Intent(context, next);
            mIntent.putExtra("SUB_CATEGORIES", subCategory);
            mIntent.putExtra("SUB_CATEGORY_TITLE", subCategoryTitle);
            context.startActivity(mIntent);
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_category, viewGroup, false);
        CategoryAdapter.MyViewHolder holder = new CategoryAdapter.MyViewHolder(view,context, categoryModelList);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String temText = "Description: ... ";
        Log.d(TAG, "onBindViewHolder: category");
        holder.name.setText(categoryModelList.get(position).getCategoryName());
        holder.description.setText(temText + categoryModelList.get(position).getCategoryDescription());
        Picasso.get().load(categoryModelList.get(position).getCategoryImage()).into(holder.imageView);
        Log.d(TAG, "onBindViewHolder: Category ImageURL: "+ categoryModelList.get(position).getCategoryImage());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
}
