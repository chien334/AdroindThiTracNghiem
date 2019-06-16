package com.example.bancuoi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bancuoi.Model.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class Login extends AppCompatActivity {
    private Button btnLogin;
    private TextInputEditText txtUsername, txtPassWord;
    private List<Users> _listUser = new ArrayList<>();
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private String url = "http://192.168.1.12/thitracnghiem/api/users";
    SharedPreferences sharedPreferences;
    ConstraintLayout loginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findviewbyid();
        even();

    }

    private void even() {
        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            loginForm.setVisibility(View.VISIBLE);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUsername.getText().toString().trim();
                String passWord = txtPassWord.getText().toString().trim();

                // rat vai bo ngoai ham click, lay luon đi bỏ nó ở ngoai sao no lấy dc data
                String url = "http://192.168.1.12/thitracnghiem/api/users?user=" + userName + "&pass=" + passWord;
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                if(response.length()>0){
                                    SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
                                    for (int i = 0; i <= response.length(); i++) {
                                        try {
                                            JSONObject objectRequest = response.getJSONObject(i);
                                            sharedPreferences= getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putInt("USER_ID", objectRequest.getInt("USER_ID"));
                                            editor.putInt("MA_HS", objectRequest.getInt("MA_HS"));
                                            editor.putString("HO_TEN_HS", objectRequest.getString("HO_TEN_HS"));
                                            editor.putString("GIOI_TINH_HS", objectRequest.getString("GIOI_TINH_HS"));
                                            editor.putString("NGAY_SINH_HS", objectRequest.getString("NGAY_SINH_HS"));
                                            editor.putString("TEN_LOP", objectRequest.getString("TEN_LOP"));
                                            editor.putString("TEN_KHOI", objectRequest.getString("TEN_KHOI"));
                                            editor.putString("TEN_LOAI", objectRequest.getString("TEN_LOAI"));
                                            editor.putString("SO_NHA", objectRequest.getString("SO_NHA"));
                                            editor.putString("TEN_PHUONG_XA", objectRequest.getString("TEN_PHUONG_XA"));
                                            editor.putString("TEN_QUAN_HUYEN", objectRequest.getString("TEN_QUAN_HUYEN"));
                                            editor.putString("TEN_TINH_TP", objectRequest.getString("TEN_TINH_TP"));
                                            editor.putBoolean("TRANG_THAI_HS", objectRequest.getBoolean("TRANG_THAI_HS"));
                                            editor.apply();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    Intent intent= new Intent(Login.this,MainActivity.class);
                                    startActivity(intent);
                                }else {
                                    Log.d("ABCKXMX", "onResponse: ");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("PointFragment", "onErrorResponse: " + error);
                            }
                        });
                queue.add(jsonArrayRequest);
            }
        });
    }

    private void findviewbyid() {
        txtUsername = (TextInputEditText) findViewById(R.id.text_usersname);
        txtPassWord = (TextInputEditText) findViewById(R.id.text_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        loginForm=(ConstraintLayout)findViewById(R.id.loginForm);

    }
}

