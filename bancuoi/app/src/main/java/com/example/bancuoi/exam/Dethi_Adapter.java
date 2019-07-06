package com.example.bancuoi.exam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bancuoi.MainActivity;
import com.example.bancuoi.Model.DapAn_Model;
import com.example.bancuoi.Model.DeThi_Model;
import com.example.bancuoi.Model.LuaChon_Model;
import com.example.bancuoi.Model.SaveBaiThi_model;
import com.example.bancuoi.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.bancuoi.Service_API.BASE_URL;

public class Dethi_Adapter extends RecyclerView.Adapter<Dethi_Adapter.ViewHolder>{
    private Context mContext;
    private List<DeThi_Model> list;
    private int check =0;
    private int [] arr= new int[60];
    private  int made;
    DapAn_Model dapAn;
    private int ctl;
    SharedPreferences prefs;

    public Dethi_Adapter(List<DeThi_Model> _list, Context context) {
        this.list = _list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public Dethi_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter_dethi, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dethi_Adapter.ViewHolder viewHolder, int i) {
        viewHolder.tvCauhoi.setText(i+1 + ") " + list.get(i).getNOI_DUNG_CH());
        viewHolder.radioButton1.setText(list.get(i).getLuachon().get(0).getNOI_DUNG_LC());
        viewHolder.radioButton2.setText(list.get(i).getLuachon().get(1).getNOI_DUNG_LC());
        viewHolder.radioButton3.setText(list.get(i).getLuachon().get(2).getNOI_DUNG_LC());
        viewHolder.radioButton4.setText(list.get(i).getLuachon().get(3).getNOI_DUNG_LC());
        made= list.get(i).getMA_DT();

        if(i == list.size()-1)
        {
            viewHolder.btnKQ.setVisibility(View.VISIBLE);
        }


        viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radio_1:
                        check=list.get(i).getLuachon().get(0).getMA_LC();
                        break;
                    case R.id.radio_2:
                        check=list.get(i).getLuachon().get(1).getMA_LC();
                        break;
                    case R.id.radio_3:
                        check=list.get(i).getLuachon().get(2).getMA_LC();
                        break;
                    case R.id.radio_4:
                        check=list.get(i).getLuachon().get(3).getMA_LC();
                        break;
                    default:
                        break;
                }
                arr[i]=check;
            }

        });

        viewHolder.btnKQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int []ar= new int[list.size()];

                for(int k= 0 ; k <list.size();k++){
                        ar[k]=arr[k];
                }
                dapAn = new DapAn_Model(made,ar);

                String url= BASE_URL+"examtest";
                RequestQueue queue= Volley.newRequestQueue(mContext);

                // Post Bài làm của học sinh để kiểm tra xem số lượng câu trả lời đúng và số điểm
                Gson gson = new Gson();
                String jsonString = gson.toJson(dapAn);
                JSONObject bookJsonObj = new JSONObject();

                try {
                    bookJsonObj = new JSONObject(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,bookJsonObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    ctl = response.getInt("resu");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("Thông báo");
                                builder.setMessage("Số câu làm được là :"+ctl+"/"+ar.length);
                                builder.setCancelable(true);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int a= ar.length;
                                        int b=ctl;
                                        double diem =(double) b/a *10 ;
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
                                        builder1.setTitle("Thông báo");
                                        builder1.setMessage("Điểm bạn đạt được là :"+diem);
                                        builder1.setCancelable(true);
                                        builder1.setPositiveButton("OK", new
                                                DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent = new Intent(mContext, MainActivity.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                        mContext.startActivity(intent);
                                                    }
                                                });
                                        AlertDialog alertDialog = builder1.create();
                                        alertDialog.show();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("hdhhdhdhdh", "error: " + error.toString());
                    }
                });
                queue.add(stringRequest);
//
//
//                // post bài thi của học sinh
//
//                //Post chi tiết bài thi của học sinh
           }
       });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCauhoi,tvSophut,tvSoGiay;
        private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
        private Button btnKQ;
        private RadioGroup radioGroup;
        LinearLayout paren;

        public ViewHolder(View view) {
            super(view);
            tvCauhoi = (TextView)view.findViewById(R.id.tvCauhoi);
            tvSophut=(TextView)view.findViewById(R.id.txtsophutlambai) ;

            radioButton1 = (RadioButton)view.findViewById(R.id.radio_1);
            radioButton2= (RadioButton)view.findViewById(R.id.radio_2);
            radioButton3 = (RadioButton)view.findViewById(R.id.radio_3);
            radioButton4 = (RadioButton)view.findViewById(R.id.radio_4);
            radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
            btnKQ = (Button) view.findViewById(R.id.btnKQ);
            view.getScrollBarSize();

        }

    }
}