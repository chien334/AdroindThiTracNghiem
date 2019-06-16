package com.example.bancuoi.poinStudent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bancuoi.Model.monHoc;
import com.example.bancuoi.R;

import java.util.List;

public class pointAdapter extends RecyclerView.Adapter<pointAdapter.PointHolder> {

    // Xem lai quy tac dat ten class. dat nv là sai nhe.
    // Quy tac dat ten ham, ten bien. lam cho co thoi quen dat nv la sai r
    private Context context;
    private List<monHoc> monHocs;

    public pointAdapter(Context context, List<monHoc> monHocs) {
        this.context = context;
        this.monHocs = monHocs;
    }

    @NonNull
    @Override
    public PointHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item;
        item= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_monhoc,viewGroup,false);
        return new PointHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PointHolder pointHolder, int i) {
        String nameMonHoc= monHocs.get(i).getTenMonHoc();
        pointHolder.txtmonhoc.setText(nameMonHoc);
        pointHolder.imgHinhanh.setImageResource(R.drawable.hinhanh);
//        Glide.with(context)
//                .load(hinhAnh)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(pointHolder.imgHinhanh);
    }

    @Override
    public int getItemCount() {
        return monHocs.size();
    }

    public class PointHolder extends RecyclerView.ViewHolder {
        private TextView txtmonhoc;
        private ImageView imgHinhanh;
        public PointHolder(@NonNull View itemView) {
            super(itemView);

            txtmonhoc=(TextView)itemView.findViewById(R.id.txtNameMonHoc);
            imgHinhanh=(ImageView)itemView.findViewById(R.id.imgMonhoc);
        }
    }

}
