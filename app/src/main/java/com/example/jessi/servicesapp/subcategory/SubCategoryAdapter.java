package com.example.jessi.servicesapp.subcategory;

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
import com.example.jessi.servicesapp.services.ServicesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    private static final String TAG = "SubCategoryAdapter";
    private List<SubCategoryModel> subCategoryModelList;
    private Context context;

    public SubCategoryAdapter(Context context, List<SubCategoryModel> subCategoryModelList) {
        Log.d(TAG, "SubCategoryAdapter: ");
        this.subCategoryModelList = subCategoryModelList;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "MyViewHolder";
        public TextView name;
        public TextView description;
        public ImageView imageView;
        List<SubCategoryModel> subCategoryModelList;
        Context context;

        public MyViewHolder
                (@NonNull View itemView, Context context, List<SubCategoryModel> subCategoryModelList) {
            super(itemView);
            Log.d(TAG, "MyViewHolder: ");
            this.subCategoryModelList = subCategoryModelList;
            this.context = context;
            this.name = itemView.findViewById(R.id.tv_subcategory_name);
            this.description = itemView.findViewById(R.id.tv_subcategory_description);
            this.imageView = itemView.findViewById(R.id.iv_subcategory);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            SubCategoryModel subCategoryModel = this.subCategoryModelList.get(position);
            Toast.makeText(context, "Clicked on: "+ subCategoryModel.getSubCategoryName(), Toast.LENGTH_SHORT).show();
            startNextActivity(view.getContext()
                    , ServicesActivity.class
                    , subCategoryModel.getSubCategoryName()
                    , subCategoryModel.getSubCategoryDescription()
                    , subCategoryModel.getSubCategoryImage());
        }

        private void startNextActivity(Context context, Class next, String name, String description, String ImageUrl){

            Intent mIntent = new Intent(context, next);
            mIntent.putExtra("SERVICES", name);
            mIntent.putExtra("DESCRIPTION", description);
            mIntent.putExtra("IMAGEURL", ImageUrl);
            context.startActivity(mIntent);
        }
    }

    @NonNull
    @Override
    public SubCategoryAdapter.MyViewHolder onCreateViewHolder
            (@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_subcategory, viewGroup, false);
        SubCategoryAdapter.MyViewHolder holder
                = new SubCategoryAdapter.MyViewHolder(view, context, subCategoryModelList);
        return holder;
    }

    @Override
    public void onBindViewHolder
            (@NonNull SubCategoryAdapter.MyViewHolder myViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        final String BASE_URL = "http://servdoservice.com/api/rest/v1/images";
        myViewHolder
                .name
                .setText(subCategoryModelList
                        .get(position)
                        .getSubCategoryName());
//        myViewHolder
//                .description
//                .setText(subCategoryModelList
//                        .get(position)
//                        .getSubCategoryDescription());
        Picasso
                .get()
                .load( subCategoryModelList
                        .get(position)
                        .getSubCategoryImage())
                .placeholder(R.drawable.banner)
                .into(myViewHolder.imageView);



       // Log.d(TAG, "onBindViewHolder: ImageURL: "+ subCategoryModelList.get(position).getSubCategoryImage());

    }

    @Override
    public int getItemCount() {
        return subCategoryModelList.size();
    }
}
