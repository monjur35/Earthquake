package com.example.classtest.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.classtest.R;
import com.example.classtest.databinding.FragmentInputBinding;

import java.util.Calendar;


public class inputFragment extends Fragment {

    DatePickerDialog pickerDialog;
    EditText startdate,endDate;
    Button button;
    FragmentInputBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentInputBinding.inflate(inflater);
        startdate= (EditText) binding.startDateId;
        endDate= (EditText) binding.endDateID;
        startdate.setInputType(InputType.TYPE_NULL);
        endDate.setInputType(InputType.TYPE_NULL);
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr=Calendar.getInstance();
                int day=cldr.get(Calendar.DAY_OF_MONTH);
                int month=cldr.get(Calendar.MONTH)+1;
                int year=cldr.get(Calendar.YEAR);

                pickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        startdate.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                },day,month+1,year);
                pickerDialog.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr=Calendar.getInstance();
                int day=cldr.get(Calendar.DAY_OF_MONTH);
                int month=cldr.get(Calendar.MONTH)+1;
                int year=cldr.get(Calendar.YEAR);

                pickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        endDate.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                },day,month+1,year);
                pickerDialog.show();
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("sDate",startdate.getText().toString().trim());
                bundle.putString("eDate",endDate.getText().toString().trim());
                Navigation.findNavController(view).navigate(R.id.action_inputFragment_to_displayFragment,bundle);
            }
        });
    }
}