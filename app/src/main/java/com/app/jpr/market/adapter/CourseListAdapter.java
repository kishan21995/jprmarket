package com.app.jpr.market.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.jpr.market.Activities.CategoryActivity;
import com.app.jpr.market.R;
import com.app.jpr.market.models.CatagoryResponse;

import java.util.List;



public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {
    private Context applicationContext;
    private List<CatagoryResponse> categoryResponse;

    public CourseListAdapter(Context applicationContext) {
        this.applicationContext = applicationContext;
    }



    public void setData(List<CatagoryResponse> CourseLists) {
        this.categoryResponse = CourseLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

holder.first.setText(categoryResponse.get(holder.getAdapterPosition()).getCatId());
holder.second.setText(categoryResponse.get(holder.getAdapterPosition()).getCatTitle());




    }

    @Override
    public int getItemCount() {

        if(categoryResponse!=null && categoryResponse.size()>0)
        {
            return categoryResponse.size();
        }
        else {
            return 0;
        }
    }


    /**
     * View Holder for CONFIG LIST
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView first, second;

        public ViewHolder(View view) {
            super(view);
            first = itemView.findViewById(R.id.text1);
            second = itemView.findViewById(R.id.text2);
        }
    }


}