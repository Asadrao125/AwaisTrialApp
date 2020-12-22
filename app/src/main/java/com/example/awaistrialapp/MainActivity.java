package com.example.awaistrialapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = findViewById(R.id.tv_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        Call<List<User>> call = jsonPlaceholderApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code: " + response.code());
                    return;
                }
                List<User> users = response.body();
                for (User user : users) {
                    String content = "";
                    content += "ID: " + user.getId() + "\n\n";
                    content += "Name: " + user.getName() + "\n\n";
                    content += "Username: " + user.getUsername() + "\n\n";
                    content += "Email: " + user.getEmail();

                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }
}