package com.app.jpr.market.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.app.jpr.market.R;
import com.app.jpr.market.adapter.CourseListAdapter;
import com.app.jpr.market.models.CatagoryResponse;
import com.app.jpr.market.retrofit.RestClient;
import com.app.jpr.market.util.AppUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    private List<CatagoryResponse> categoryResponse=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.recycler);
        getCourse();
    }

    private void getCourse() {


        AppUtils.showProgressDialog(CategoryActivity.this);
        RestClient.getCourses(new Callback<CatagoryResponse>() {
            @Override
            public void onResponse(Call<CatagoryResponse> call, Response<CatagoryResponse> response) {


                Log.d("Api Response :", "Got Success from Api");
                if (response.isSuccessful()) {
                    AppUtils.dismisDialog();

                    if (categoryResponse != null && categoryResponse.size() > 0) {
                    Log.d("Api Response :", "Got Success from Api");
                        CourseListAdapter courseListAdapter = new CourseListAdapter(getApplicationContext());
                        courseListAdapter.setData(categoryResponse);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(courseListAdapter);

                        Log.d("Api Response :", "Got Success from Api");
                        // noInternet.setVisibility(View.GONE);
                           /* RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2) {
                                @Override
                                public boolean canScrollVertically() {
                                    return true;
                                }

                            };
*/
                    } else {
                        Log.d("Api Response :", "Got Success from Api");

                        Toast.makeText(CategoryActivity.this, "No data", Toast.LENGTH_SHORT).show();
                        // noInternet.setVisibility(View.VISIBLE);
                        // noInternet.setText(getString(R.string.no_project));

                    }
                } else {

                }


            }

            @Override
            public void onFailure(Call<CatagoryResponse> call, Throwable t) {
                AppUtils.dismisDialog();
                Toast.makeText(CategoryActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
