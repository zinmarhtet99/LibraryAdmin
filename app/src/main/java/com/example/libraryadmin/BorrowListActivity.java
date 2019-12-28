package com.example.libraryadmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryadmin.Api.ApiInterface;
import com.example.libraryadmin.Api.ApiUtils;
import com.example.libraryadmin.model.Borrowreturn;
import com.example.libraryadmin.model.ReturnAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowListActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    ReturnAdapter returnAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book_list);

        apiInterface= ApiUtils.getApi();
        Call<List<Borrowreturn>> getBorrowBook=apiInterface.getBorrowBook();

        getBorrowBook.enqueue(new Callback<List<Borrowreturn>>() {
            @Override
            public void onResponse(Call<List<Borrowreturn>> call, Response<List<Borrowreturn>> response) {
                // Toast.makeText(getApplicationContext(),response.body().size()+"hhhhh",Toast.LENGTH_LONG).show();

                LoadBorrowBookList(response.body());
            }

            @Override
            public void onFailure(Call<List<Borrowreturn>> call, Throwable t) {

            }
        });


    }
    public void LoadBorrowBookList(List<Borrowreturn>borrowreturnList){

        recyclerView=findViewById(R.id.returnrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(BorrowListActivity.this));

        returnAdapter =new ReturnAdapter(BorrowListActivity.this,borrowreturnList);
        recyclerView.setAdapter(returnAdapter);
    }
}