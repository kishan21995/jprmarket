package com.app.jpr.market.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.jpr.market.R;
import com.app.jpr.market.adapter.CourseListAdapter;
import com.app.jpr.market.models.CatagoryResponse;
import com.app.jpr.market.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.app.jpr.market.R;
import com.app.jpr.market.adapter.CourseListAdapter;
import com.app.jpr.market.models.CatagoryResponse;
import com.app.jpr.market.retrofit.RestClient;
import com.app.jpr.market.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

        public class SeeAllActivity extends AppCompatActivity {


            RecyclerView recyclerView;
            private List<CatagoryResponse> categoryResponse = new ArrayList<>();

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_see_all);
                recyclerView = findViewById(R.id.recycler);
                getCourse();

                ///show back button
                if(getSupportActionBar()!=null){                                                                                  ///
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                                       ///
                    getSupportActionBar().setDisplayShowHomeEnabled(true);                                                       ///

                }
            }

            private void getCourse() {

                //show progress dialog
               // AppUtils.showProgressDialog(com.app.jpr.market.Activities.SeeAllActivity.this);
                Utils.showProgressDialog(SeeAllActivity.this,"Please wait...");



                RestClient.getCourses(new Callback<List<CatagoryResponse>>() {
                    @Override
                    public void onResponse(Call<List<CatagoryResponse>> call, Response<List<CatagoryResponse>> response) {


                        categoryResponse = response.body();
                        if (response.isSuccessful()) {
                            if (categoryResponse != null && categoryResponse.size() > 0) {
                                Utils.dismissProgressDialog(); //dismiss progress dialog

                                Log.d("Api Response :", "Got Success from Api");
                                CourseListAdapter courseListAdapter = new CourseListAdapter(getApplicationContext());
                                courseListAdapter.setData(categoryResponse);

                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setAdapter(courseListAdapter);

                                Log.d("Api Response :", "Got Success from Api");

                            }
                            ;
                        } else {
                            Log.d("Api Response :", "Got Success from Api");

                            Toast.makeText(com.app.jpr.market.Activities.SeeAllActivity.this, "No data", Toast.LENGTH_SHORT).show();
                            // noInternet.setVisibility(View.VISIBLE);
                            // noInternet.setText(getString(R.string.no_project));
                        }
                    }


                    @Override
                    public void onFailure(Call<List<CatagoryResponse>> call, Throwable t) {
                        Utils.dismissProgressDialog();

                    }
                });

            }


          /*  @Override  //add cart code
            public boolean onCreateOptionsMenu(Menu menu) {                                             //
                // Inflate the menu; this adds items to the action bar if it is present.               //
                getMenuInflater().inflate(R.menu.main, menu);                                         //
                return true;                                                                          //
            }
*/

    /*        @Override
            public boolean onOptionsItemSelected(MenuItem item) {                                                          //
                int id = item.getItemId();                                                                                 //
                switch (id) {                                                                                               //
                    case R.id.action_settings:                                                                               //
                        //Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(com.app.jpr.market.Activities.CategoryActivity.this, AddCard.class);
                        startActivity(intent);//
                        return true;

                }

                ///show back button
                if(item.getItemId()==android.R.id.home)                                                                     ///
                {                                                                                                           ///
                    finish();                                                                                               ///
                    return super.onOptionsItemSelected(item);                                                               ///
                }                                                                                                          ///
                return true;
            }*/

        }





