package com.example.classtest.ViewModels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.classtest.EarthQuakServices;
import com.example.classtest.Repo.EarthQuakRepo;
import com.example.classtest.RetrofitClient;
import com.example.classtest.pojo.ResponseModel;

import java.util.List;

import retrofit2.Retrofit;

public class EarthQuakViewModel extends AndroidViewModel {
    private EarthQuakServices earthQuakServices;
    private EarthQuakRepo earthQuakRepo;
    private LiveData<List<ResponseModel>> mutableLiveData;
    Context context;
    private String TAg="monjur";



    public EarthQuakViewModel(@NonNull Application application) {
        super(application);
        earthQuakServices=RetrofitClient.getClient().create(EarthQuakServices.class);
        earthQuakRepo=new EarthQuakRepo(earthQuakServices,context);
    }


    public MutableLiveData<ResponseModel> getEarthQuakData(String endUrl){
        Log.e(TAg,"get eartquakDAta");
        return earthQuakRepo.fetchResponseData(endUrl);
    }


}
