package com.example.bancuoi.exam;

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
import com.example.bancuoi.Model.monHoc;
import com.example.bancuoi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.bancuoi.Login.MY_PREFS_NAME;
import static com.example.bancuoi.Service_API.BASE_URL;

public class BoDeThiActivity extends AppCompatActivity {
    SharedPreferences prefs;
    int mahs, mamh;
    private RecyclerView recyclerView;
    List<BoDeThi_Model> listbode= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);
        findviewbyid();
        loadJson();
    }

    private void findviewbyid() {
        recyclerView= (RecyclerView)findViewById(R.id.rcvBoDeThi);
    }

    private void getSession() {
        prefs = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        mahs= prefs.getInt("MA_HS",0);
        Intent intent = getIntent();
        mamh = intent.getIntExtra("MA_MH",0);
    }
    private void customRCV() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(new BoDeThi_Adapter(listbode,BoDeThiActivity.this));
    }
    private void loadJson() {
        RequestQueue queue= Volley.newRequestQueue(BoDeThiActivity.this);
        getSession();
        String url = BASE_URL+"examTest?idhs="+mahs+"&&idmh="+mamh;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0 ; i<=response.length();i++){
                            try {
                                SharedPreferences.Editor editor = prefs.edit();
                                JSONObject objectRequest= response.getJSONObject(i);
                                int ma_dt= objectRequest.getInt("MA_DT");
                                String tenbode = objectRequest.getString("TIEU_DE_DT");
                                String time = objectRequest.getString("MA_LOAI_DT");
                                int thoigian=0;
                                if(time.equals("1_TIET")){
                                    thoigian=45;
                                }else if(time.equals("15_PHUT")){
                                    thoigian=15;
                                }else  if(time.equals("GIUA_KY")){
                                    thoigian=45;
                                }
                                editor.putInt("TIME_BODE",thoigian);
                                editor.commit();
                                listbode.add(new BoDeThi_Model(ma_dt,tenbode,thoigian));
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
