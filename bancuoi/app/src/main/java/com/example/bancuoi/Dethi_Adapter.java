package com.example.bancuoi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.bancuoi.Model.DeThi_Model;

import java.util.List;

public class Dethi_Adapter extends RecyclerView.Adapter<Dethi_Adapter.ViewHolder>{
    private Context mContext;
    private List<DeThi_Model> list;

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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCauhoi;
        private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;

        public ViewHolder(View view) {
            super(view);
            tvCauhoi = (TextView)view.findViewById(R.id.tvCauhoi);

            radioButton1 = (RadioButton)view.findViewById(R.id.radio_1);
            radioButton2= (RadioButton)view.findViewById(R.id.radio_2);
            radioButton3 = (RadioButton)view.findViewById(R.id.radio_3);
            radioButton4 = (RadioButton)view.findViewById(R.id.radio_4);

        }

    }
}
