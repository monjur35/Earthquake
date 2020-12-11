package com.example.classtest.Repo;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.classtest.EarthQuakServices;
import com.example.classtest.pojo.ResponseModel;

import java.util.concurrent.TimeoutException;

import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthQuakRepo {

    private EarthQuakServices earthQuakServices;
    private Context context;
    private String TAG="monjur";

    public EarthQuakRepo(EarthQuakServices earthQuakServices, Context context) {
        this.earthQuakServices = earthQuakServices;
        this.context = context;
    }

    public MutableLiveData<ResponseModel> fetchResponseData(String endUrl){
        Log.e(TAG,"Fetch response Data ");
        MutableLiveData<ResponseModel> responseModelMutableLiveData=new MutableLiveData<>();
        earthQuakServices.getEarthQuakResponse(endUrl).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()){
                    final ResponseModel responseModels=response.body();

                    responseModelMutableLiveData.postValue(responseModels);
                    Log.e(TAG,"succesfull onResponse");
                }


            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e(TAG,"On throwable "+t.getLocalizedMessage());


            }

        });
        return responseModelMutableLiveData;

    }
}
