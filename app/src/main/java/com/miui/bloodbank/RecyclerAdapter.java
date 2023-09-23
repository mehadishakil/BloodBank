package com.miui.bloodbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.holder> {

    ArrayList<String> dData, mbData, bgData;

    public RecyclerAdapter(ArrayList<String> dData, ArrayList<String> mbData, ArrayList<String> bgData) {
        this.dData = dData;
        this.mbData = mbData;
        this.bgData = bgData;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.donarName.setText(dData.get(position));
        holder.donarMobile.setText(mbData.get(position));
        holder.donarBG.setText(bgData.get(position));
    }

    @Override
    public int getItemCount() {
        return dData.size();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView donarName, donarMobile, donarBG;

        public holder(@NonNull View itemView) {
            super(itemView);
            donarName = itemView.findViewById(R.id.donarName);
            donarMobile = itemView.findViewById(R.id.donarMobile);
            donarBG = itemView.findViewById(R.id.donarBloodGroup);
        }
    }


}
