package com.example.libraryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.libraryadmin.Api.ApiInterface;
import com.example.libraryadmin.Api.ApiUtils;
import com.example.libraryadmin.model.Borrowreturn;
import com.example.libraryadmin.model.ReturnAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnBookListActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    ReturnAdapter returnAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book_list);

        Intent intent=getIntent();
        String rno=intent.getStringExtra("rno");



        apiInterface= ApiUtils.getApi();


        Call<List<Borrowreturn>> getBorrowBook=apiInterface.getBorrowBook(rno);

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
        recyclerView.setLayoutManager(new LinearLayoutManager(ReturnBookListActivity.this));

        returnAdapter =new ReturnAdapter(ReturnBookListActivity.this,borrowreturnList);
        recyclerView.setAdapter(returnAdapter);
    }
}
