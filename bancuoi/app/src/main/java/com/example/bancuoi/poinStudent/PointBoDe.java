package com.example.bancuoi.poinStudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bancuoi.Model.BoDeThi_Model;
import com.example.bancuoi.Model.point_BoDe;
import com.example.bancuoi.R;
import com.example.bancuoi.exam.BoDeThiActivity;
import com.example.bancuoi.exam.BoDeThi_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.bancuoi.Login.MY_PREFS_NAME;
import static com.example.bancuoi.Service_API.BASE_URL;

public class PointBoDe extends AppCompatActivity {
    SharedPreferences prefs;
    int mahs, mamh;
    private RecyclerView recyclerView;
    List<point_BoDe> listbode= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_bo_de);
        findviewbyid();
        loadJson();
    }
    private void findviewbyid() {
        recyclerView= (RecyclerView)findViewById(R.id.rcvPointBoDeThi);
    }

    private void getSession() {
        prefs = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        mahs= prefs.getInt("MA_HS",0);

        Intent intent = getIntent();
        mamh=intent.getIntExtra("MA_MH",0);
    }
    private void customRCV() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(new PointBoDe_Adapter(PointBoDe.this,listbode));
    }
    private void loadJson() {
        RequestQueue queue= Volley.newRequestQueue(PointBoDe.this);
        getSession();
        String url = BASE_URL+"point?idhs="+mahs+"&&idmh="+mamh;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0 ; i<=response.length();i++){
                            try {
                                SharedPreferences.Editor editor = prefs.edit();
                                JSONObject objectRequest= response.getJSONObject(i);
                                String tieuDeDt= objectRequest.getString("TIEU_DE_DT");
                                String ngay = objectRequest.getString("NGAY_THI");
                                String time = objectRequest.getString("THOI_GIAN_LAM");
                                double Diem= objectRequest.getDouble("DIEM");

                                String ngaythi = ngay.substring(8,10)+"/"+ngay.substring(5,7)+"/"+ngay.substring(0,4);
                                listbode.add(new point_BoDe(tieuDeDt,ngaythi,time,Diem));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        customRCV();
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("PointFragment", "onErrorResponse: "+error);
                    }
                });
        queue.add(jsonArrayRequest);
    }
}
