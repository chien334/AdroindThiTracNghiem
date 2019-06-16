package com.example.bancuoi.poinStudent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bancuoi.Model.monHoc;
import com.example.bancuoi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bancuoi.Login.MY_PREFS_NAME;

public class PointFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<monHoc> _liMonHoc = new ArrayList<>();
    private View v;
    private  int id;
    private String url="http://192.168.1.12/thitracnghiem/api/examTest/";
    SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.point_fragment, container,false);
        // ten ham dat tieng anh
        findViewById();

        loadJson();


        return v;
    }

    private void getSession() {
        prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        id= prefs.getInt("MA_HS",0);
    }

    private void findViewById() {
        recyclerView = (RecyclerView) v.findViewById(R.id.rvPoint);
    }
    private void loadJson() {
        getSession();
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url+id, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0 ; i<=response.length();i++){
                            try {
                                JSONObject objectRequest= response.getJSONObject(i);
                                int id= objectRequest.getInt("MA_MH");
                                String monhoc = objectRequest.getString("TEN_MH");
                                _liMonHoc.add(new monHoc(id,monhoc));
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
    private void customRCV(){
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new pointAdapter(getActivity(),_liMonHoc));
    }
    // nên code ro rang ntn, sau nay bao tri code hoac pha trien se de hon
    // neu trinh tot hon thi tim hieu mo hinh MVVM day la mo hinh chuan khi di lam o cty ngoai

}
