package com.example.bancuoi.exam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bancuoi.Model.DeThi_Model;
import com.example.bancuoi.R;
import com.example.bancuoi.Service_API;
import com.example.bancuoi.exam.Dethi_Adapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.bancuoi.Login.MY_PREFS_NAME;
import static com.example.bancuoi.Service_API.BASE_URL;

public class DeThiActivity extends AppCompatActivity {
    private CompositeDisposable mCompositeDisposable;
    private RecyclerView recyclerView;
    private TextView tvPhut, tvGiay;
    SharedPreferences prefs;
    private int made,time;
    private int giay=0;
    private int phut ;
    private int soluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);
        mCompositeDisposable = new CompositeDisposable();

        findViewById();
        setRCV();
        getSession();
        loadJSONDeThi(made);
        countTime();
    }

    private void countTime() {
        phut= time;
        CountDownTimer countDownTimer= new CountDownTimer((time*60*1000),1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                tvPhut.setText(phut+"");
                tvGiay.setText(giay+"");
                giay--;
                if(giay<=0){
                    giay=59;
                    phut =phut-1;
                }
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    private void getSession() {
        prefs = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Intent intent = getIntent();
        made = intent.getIntExtra("MA_DE",0);
        soluong= intent.getIntExtra("SO_LUONG",0);
        time= intent.getIntExtra("THOI_GIAN",0);
    }
    private void findViewById() {
        recyclerView = (RecyclerView)findViewById(R.id.rcvDeThi);
        tvGiay= (TextView)findViewById(R.id.txtsoGiaylambai);
        tvPhut=(TextView)findViewById(R.id.txtsophutlambai);
    }
    private void setRCV(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadJSONDeThi(int id) {
        Service_API requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service_API.class);

        mCompositeDisposable.add(requestInterface.GetDeThi(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(List<DeThi_Model> deThi_model) {
        Log.d("DethiActivity", "deThi_model " + deThi_model.size());
        Dethi_Adapter dethi_adapter = new Dethi_Adapter(deThi_model, this);
        recyclerView.setAdapter(dethi_adapter);

    }
    /**
     * Trả về lỗi
     * @param error (Lỗi)
     */
    private void handleError(Throwable error) {
        Log.d("DethiActivity", "error" + error);
    }
}