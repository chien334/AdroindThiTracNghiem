package com.example.bancuoi.exam;

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

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamHolder>{
    private Context context;
    private List<monHoc> monHocs;

    public ExamAdapter(Context context, List<monHoc> monHocs) {
        this.context = context;
        this.monHocs = monHocs;
    }


    @NonNull
    @Override
    public ExamHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_monhoc, viewGroup, false);
        return new ExamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamHolder examHolder, int i) {
        examHolder.txtmonhoc.setText(monHocs.get(i).getTenMonHoc());
        examHolder.imgHinhanh.setImageResource(R.drawable.hinhanh);
        examHolder.tvGiaoVien.setText("Giáo viên \n"+monHocs.get(i).getTenGV());

        examHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if(pos >= 0)
                {
                    Intent intent= new Intent(context,BoDeThiActivity.class);
                    intent.putExtra("MA_MH",monHocs.get(i).getId());
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "qưewq", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return monHocs.size();
    }

    public class ExamHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtmonhoc,tvGiaoVien;
        private ImageView imgHinhanh;
        private ConstraintLayout constraintLayout;
        private ItemClickListener itemClickListener;

        public ExamHolder(@NonNull View itemView) {
            super(itemView);
                txtmonhoc=(TextView)itemView.findViewById(R.id.txtNameBoDe);
                imgHinhanh=(ImageView)itemView.findViewById(R.id.imgBoDeThi);
                tvGiaoVien= (TextView) itemView.findViewById(R.id.tvGiaoVien);
                constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.constraintItem);
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
