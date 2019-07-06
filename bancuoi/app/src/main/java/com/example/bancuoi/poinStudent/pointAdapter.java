package com.example.bancuoi.poinStudent;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bancuoi.ItemClickListener;
import com.example.bancuoi.Model.monHoc;
import com.example.bancuoi.R;
import com.example.bancuoi.exam.DeThiActivity;

import java.util.List;

public class pointAdapter extends RecyclerView.Adapter<pointAdapter.PointHolder> {
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
        pointHolder.tvGiaoVien.setText("Giáo viên \n"+monHocs.get(i).getTenGV());
        pointHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if(pos >= 0)
                {
                    Intent intent = new Intent(context, PointBoDe.class);
                    intent.putExtra("MA_MH",monHocs.get(i).getId());
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "Không có đề thi nào", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return monHocs.size();
    }

    public class PointHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtmonhoc,tvGiaoVien;
        private ImageView imgHinhanh;
        private ItemClickListener itemClickListener;
        private ConstraintLayout constraintLayout;
        public PointHolder(@NonNull View itemView) {
            super(itemView);

            txtmonhoc=(TextView)itemView.findViewById(R.id.txtNameBoDe);
            imgHinhanh=(ImageView)itemView.findViewById(R.id.imgBoDeThi);
            tvGiaoVien=(TextView)itemView.findViewById(R.id.tvGiaoVien);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }

}
