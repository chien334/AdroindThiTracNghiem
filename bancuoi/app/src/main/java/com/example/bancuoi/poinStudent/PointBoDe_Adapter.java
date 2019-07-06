package com.example.bancuoi.poinStudent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bancuoi.ItemClickListener;
import com.example.bancuoi.Model.point_BoDe;
import com.example.bancuoi.R;
import com.example.bancuoi.exam.BoDeThi_Adapter;

import java.util.List;

public class PointBoDe_Adapter extends RecyclerView.Adapter<PointBoDe_Adapter.ViewHolder>{
    private Context mContext;
    List<point_BoDe> list;

    public PointBoDe_Adapter(Context mContext, List<point_BoDe> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_point_bode, viewGroup, false);
        return new PointBoDe_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtNameBoDe.setText(list.get(i).getTieude());
        viewHolder.tvNgaylam.setText("Thi ngày :" + list.get(i).getNgaylam());
        viewHolder.tvDiem.setText("Điểm : "+list.get(i).getDiem() );
        viewHolder.txtThoigianlam.setText("Thời gian làm : " +list.get(i).getThoigianlam());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView txtNameBoDe,txtThoigianlam,tvNgaylam,tvDiem;
        private ItemClickListener itemClickListener;
        public ViewHolder(View view) {
            super(view);
            txtNameBoDe=(TextView)view.findViewById(R.id.tvNameBaiThi);
            txtThoigianlam=(TextView) view.findViewById(R.id.tvThoiGianLam);
            tvDiem=(TextView)view.findViewById(R.id.tvDiemSo) ;
            tvNgaylam =(TextView)view.findViewById(R.id.tvNgayLam);

        }

    }
}
