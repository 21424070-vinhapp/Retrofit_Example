package com.example.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofit_example.api.ApiService;
import com.example.retrofit_example.databinding.ActivityMainBinding;
import com.example.retrofit_example.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    //link api: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    private final String access_key = "843d4d34ae72b3882e3db642c51e28e6";
    private final String currencies = "VND";
    private final String source = "USD";
    private final int format = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addControll();

        mBinding.btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCallApi();
            }
        });
    }

    private void onClickCallApi() {
        ApiService.apiService
                .convertUsdToVnd(access_key, currencies, source, format)
                .enqueue(new Callback<Currency>() {
                    @Override
                    public void onResponse(Call<Currency> call, Response<Currency> response) {
                        Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_SHORT).show();

                        Currency currency=response.body();
                        if(currency!=null && currency.isSuccess())
                        {
                            mBinding.txtTerms.setText(currency.getTerms());
                            mBinding.txtSource.setText(currency.getSource());
                            mBinding.txtUsdVnd.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Currency> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Call API error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addControll() {
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }
}