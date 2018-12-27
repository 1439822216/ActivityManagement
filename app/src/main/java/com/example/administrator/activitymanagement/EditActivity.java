package com.example.administrator.activitymanagement;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditActivity extends Fragment {
EditText ed_edit_aName,ed_edit_aOpenTime,ed_edit_aEndTime,ed_edit_aPlace,ed_edit_aInfo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_edit, container,false);
        return view;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       ed_edit_aName=getActivity().findViewById(R.id.ed_edit_aName);
       ed_edit_aOpenTime=getActivity().findViewById(R.id.ed_edit_aOpenTime);
       ed_edit_aEndTime=getActivity().findViewById(R.id.ed_edit_aEndTime);
       ed_edit_aPlace=getActivity().findViewById(R.id.ed_edit_aPlace);
       ed_edit_aInfo=getActivity().findViewById(R.id.ed_edit_aInfo);
    }



}
