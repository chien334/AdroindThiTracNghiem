package com.example.bancuoi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bancuoi.Model.DoiMatKhau_Model;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bancuoi.Login.MY_PREFS_NAME;
import static com.example.bancuoi.Service_API.BASE_URL;

public class ReplayPass_Fragment extends Fragment {
    private TextView edNewPass, edReplancePass;
    private Button btnSubmit;
    private DoiMatKhau_Model doipass;
    private  int userID;
    SharedPreferences prefs;
    private  View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.replace_password_fragment,container,false);
        findviewbyid();
        event();
        return view;
    }

    private void getSession() {
        prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        userID=prefs.getInt("USER_ID",0);
    }

    private void event() {
        getSession();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass= edNewPass.getText().toString().trim();
                String replance= edReplancePass.getText().toString().trim();
                if(newpass.equals(replance)){
                    doipass= new DoiMatKhau_Model(userID,newpass);

                    Gson gson = new Gson();
                    String jsonString = gson.toJson(doipass);
                    JSONObject bookJsonObj = new JSONObject();
                    try {
                        bookJsonObj = new JSONObject(jsonString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL+"users?", bookJsonObj,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Thông báo");
                    builder1.setMessage("Đổi mật khẩu thành công");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            homeFragment fragment= new homeFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment, fragment);
                            fragmentTransaction.commit();
                        }
                    });
                    builder1.show();

                }else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("Thông báo");
                    builder1.setMessage("Vui lòng nhập chính xác mật khẩu !");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            edReplancePass.setText("");
                        }
                    });
                    builder1.show();
                }
            }
        }
        );
    }
    private void findviewbyid() {
        edNewPass= (TextView)view.findViewById(R.id.edNewPass);
        edReplancePass=(TextView)view.findViewById(R.id.edReplayPass);
        btnSubmit= (Button) view.findViewById(R.id.btnSubmitReplance);
    }
}