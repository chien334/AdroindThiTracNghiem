package com.example.bancuoi.exam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bancuoi.ItemClickListener;
import com.example.bancuoi.Model.BoDeThi_Model;
import com.example.bancuoi.Model.DeThi_Model;
import com.example.bancuoi.R;

import java.util.List;


public class BoDeThi_Adapter extends RecyclerView.Adapter<BoDeThi_Adapter.ViewHolder> {
    private Context mContext;
    private List<BoDeThi_Model> list;
    public BoDeThi_Adapter(List<BoDeThi_Model> _list, Context context) {
        this.list = _list;
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter_bodethi, viewGroup, false);
        return new BoDeThi_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String tendethi = list.get(i).getTenDeThi();
        String chuoi1 = tendethi.substring(0,18);
        int vitri = chuoi1.lastIndexOf(" ");
        String dethi = tendethi.substring(0,vitri)+"\n"+tendethi.substring(vitri+1,tendethi.length());

        viewHolder.txtNameBoDe.setText(dethi);
        viewHolder.imgHinhanh.setImageResource(R.drawable.hinhanh);
        viewHolder.txtThoiGianThi.setText(list.get(i).getTime() +"phút");

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                if(pos >= 0)
                {
                    Intent intent = new Intent(mContext,DeThiActivity.class);
                    intent.putExtra("MA_DE",list.get(i).getMade());
                    intent.putExtra("SO_LUONG",list.size());
                    intent.putExtra("THOI_GIAN",list.get(i).getTime());
                    mContext.startActivity(intent);
                }
                else
                {
                    Toast.makeText(mContext, "qưewq", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNameBoDe,txtThoiGianThi;
        private ImageView imgHinhanh;
        private ConstraintLayout constraintLayout;
        private ItemClickListener itemClickListener;
        public ViewHolder(View view) {
            super(view);
            txtNameBoDe=(TextView)view.findViewById(R.id.txtNameBoDe);
            txtThoiGianThi=(TextView) view.findViewById(R.id.txtSoPhut);
            imgHinhanh=(ImageView)view.findViewById(R.id.imgBoDeThi);
            constraintLayout=(ConstraintLayout)itemView.findViewById(R.id.constraintItemdethi);
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

