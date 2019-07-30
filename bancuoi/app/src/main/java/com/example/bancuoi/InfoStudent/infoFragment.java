package com.example.bancuoi.InfoStudent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

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

import static android.content.Context.MODE_PRIVATE;
import static com.example.bancuoi.Login.MY_PREFS_NAME;

public class infoFragment extends Fragment {
    private  View v;
    TextInputEditText txtName, txtAdress,txtPhuong_xa,txtQuan_huyen,txtTinh,txtInputDate,txtLop,txtKhoi;
    RadioButton rdBoy, rdGirl;
    ImageView imgInfor;
    SharedPreferences prefs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.info_fragment,container,false);
        findViewById();
        getSession();
        return  v;
    }

    private void getSession() {
        prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            txtName.setText(prefs.getString("HO_TEN_HS",""));
            String date =prefs.getString("NGAY_SINH_HS","");
            txtInputDate.setText(date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4));
            String gt=prefs.getString("GIOI_TINH_HS","");
            if(gt.equals("Nữ")){
                rdGirl.setChecked(true);
                imgInfor.setImageResource(R.drawable.girl);
            }else {
                rdBoy.setChecked(true);
                imgInfor.setImageResource(R.drawable.boy);
            }
            txtAdress.setText(prefs.getString("SO_NHA",""));
            txtKhoi.setText(prefs.getString("TEN_KHOI",""));
            txtLop.setText(prefs.getString("TEN_LOP",""));
            txtPhuong_xa.setText(prefs.getString("TEN_PHUONG_XA",""));
            txtQuan_huyen.setText(prefs.getString("TEN_QUAN_HUYEN",""));
            txtTinh.setText(prefs.getString("TEN_TINH_TP",""));
    }


    private void findViewById() {
        txtName = (TextInputEditText)v.findViewById(R.id.text_input_name);
        txtAdress = (TextInputEditText)v.findViewById(R.id.text_input_birthplance);
        txtInputDate = (TextInputEditText)v.findViewById(R.id.text_input_birthdate);
        txtPhuong_xa = (TextInputEditText)v.findViewById(R.id.text_Phuong_xa);
        txtQuan_huyen = (TextInputEditText)v.findViewById(R.id.text_Quan_Huyen);
        txtTinh = (TextInputEditText)v.findViewById(R.id.text_Tinh_tp);
        txtKhoi = (TextInputEditText)v.findViewById(R.id.text_Class);
        txtLop=(TextInputEditText)v.findViewById(R.id.text_KhoiLop);
        rdBoy = (RadioButton)v.findViewById(R.id.rdBoy);
        rdGirl = (RadioButton)v.findViewById(R.id.rdGirl);
        imgInfor =(ImageView)v.findViewById(R.id.imgInfo);
    }


}
