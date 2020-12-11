package com.example.classtest.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.classtest.Adapter.Displayadapter;
import com.example.classtest.R;
import com.example.classtest.ViewModels.EarthQuakViewModel;
import com.example.classtest.databinding.FragmentDisplayBinding;
import com.example.classtest.pojo.Feature;
import com.example.classtest.pojo.ResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public class DisplayFragment extends Fragment {

    private EarthQuakViewModel earthQuakViewModel;
    private Displayadapter adapter;
    private FragmentDisplayBinding binding;
    private  String TAg="monjur";


    private String start="";
    private String end="";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        binding=FragmentDisplayBinding.inflate(inflater);
        earthQuakViewModel=new ViewModelProvider(requireActivity()).get(EarthQuakViewModel.class);
        Bundle bundle=this.getArguments();
        if (bundle!=null){
            start=bundle.getString("sDate");
            end=bundle.getString("eDate");
            Log.e(TAg,"on Create View  "+ start  + end );
        }


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getData();



    }

    private void getData(){
        Log.e(TAg,"getData"+start+end);
        final String endUrl=String.format("1/query?format=geojson&starttime=%s&endtime=%s",start,end);
        earthQuakViewModel.getEarthQuakData(endUrl).observe(getViewLifecycleOwner(), new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                final List<Feature> featureList=responseModel.getFeatures();
                adapter=new Displayadapter(getActivity(),featureList);
                final LinearLayoutManager llm =new LinearLayoutManager(getActivity());
                binding.rvId.setLayoutManager(llm);

                binding.rvId.setAdapter(adapter);
                binding.loadingmsg.setVisibility(View.INVISIBLE);
            }
        });
    }
}