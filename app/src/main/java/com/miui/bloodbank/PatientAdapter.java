package com.miui.bloodbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.pHolder> {

    ArrayList<String> pName, pHospital, pMobile, pBG;

    public PatientAdapter(ArrayList<String> pName, ArrayList<String> pHospital, ArrayList<String> pMobile, ArrayList<String> pBG) {
        this.pName = pName;
        this.pHospital = pHospital;
        this.pMobile = pMobile;
        this.pBG = pBG;
    }

    @NonNull
    @Override
    public pHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.patient_row, parent, false);
        return new pHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pHolder pholder, int position) {
        pholder.patientName.setText(pName.get(position));
        pholder.patientHospital.setText(pHospital.get(position));
        pholder.patientMobile.setText(pMobile.get(position));
        pholder.patientBG.setText(pBG.get(position));
    }

    @Override
    public int getItemCount() {
        return pName.size();
    }


    class pHolder extends RecyclerView.ViewHolder {

        TextView patientName, patientHospital, patientMobile, patientBG;

            public pHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientName);
            patientHospital = itemView.findViewById(R.id.patientHospital);
            patientMobile = itemView.findViewById(R.id.patientMobile);
            patientBG = itemView.findViewById(R.id.patientBloodGroup);
        }
    }

}
