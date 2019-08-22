 package com.example.bancuoi;

import com.example.bancuoi.Model.DeThi_Model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service_API {
    String BASE_URL = "http://192.168.2.33/thitracnghiemserver/api/";

    @GET("dethi")
    Observable<List<DeThi_Model>>GetDeThi(@Query("madethi") int id);

}

