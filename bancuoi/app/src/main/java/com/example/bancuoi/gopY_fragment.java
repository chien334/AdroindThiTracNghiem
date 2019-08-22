package com.example.bancuoi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bancuoi.Model.GopY;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.bancuoi.Service_API.BASE_URL;

public class gopY_fragment extends Fragment {
    MultiAutoCompleteTextView mtTVGopY;
    Button btnGopY;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.gop_y_fragment, container,false);
        findViewById();
        btnGopY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJson();
                mtTVGopY.setText("");
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Thông báo");
                builder1.setMessage("Cảm ơn bạn đã đóng góp ý kiến này cho chúng tôi.");
                builder1.setCancelable(true);
                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder1.show();
            }
        });
        return v;
    }

    private void loadJson() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        String newpass= mtTVGopY.getText().toString().trim();
            GopY doipass = new GopY(newpass);

            Gson gson = new Gson();
            String jsonString = gson.toJson(doipass);
            JSONObject bookJsonObj = new JSONObject();
            try {
                bookJsonObj = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL + "gopies?", bookJsonObj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);
        }

    private void findViewById() {
        mtTVGopY= (MultiAutoCompleteTextView)v.findViewById(R.id.mtTVGopY);
        btnGopY=(Button)v.findViewById(R.id.btnGopY);

    }
}
