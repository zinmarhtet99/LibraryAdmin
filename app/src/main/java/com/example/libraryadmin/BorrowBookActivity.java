package com.example.libraryadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryadmin.Api.ApiInterface;
import com.example.libraryadmin.Api.ApiUtils;
import com.example.libraryadmin.Utils.FontEmbedder;
import com.example.libraryadmin.model.Book1;
import com.example.libraryadmin.model.Member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowBookActivity extends AppCompatActivity {

    EditText edtrollno,edtAuthor,edtDate,edtmember_id,edtbook_id;
    Spinner spSname,spborrowTitle;
    Button btnBorrow;
    ApiInterface apiInterface;
    List<Member>memberList;
    List<Book1>book1List;
    ArrayAdapter<Member>memberArrayAdapter;
    ArrayAdapter<Book1>book1ArrayAdapter;
    String stName,stTitle,srollno,sauthor;
    int memberid,bookid;
    TextView ssi,ssdi;
    String bdate;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_list);

        spSname=findViewById(R.id.spnStuName);
        edtrollno=findViewById(R.id.rNo);
        spborrowTitle=findViewById(R.id.spnborrowtitle);
        edtAuthor=findViewById(R.id.borrowauthor);
        edtDate=findViewById(R.id.date);
        btnBorrow=findViewById(R.id.borrow);
        edtmember_id=findViewById(R.id.txtmemberid);
        edtbook_id=findViewById(R.id.book_id);

        SimpleDateFormat sdf=new SimpleDateFormat("yyy.MM.dd");
        String currentDateandTime=sdf.format(new Date());
        edtDate.setText(currentDateandTime);



        apiInterface=ApiUtils.getApi();
        Call<List<Member>> getMember=apiInterface.getAllMember();

        getMember.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {


                memberList=response.body();

                memberArrayAdapter=new ArrayAdapter(BorrowBookActivity.this, android.R.layout.simple_spinner_item,memberList);
                memberArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSname.setAdapter(memberArrayAdapter);

                spSname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Member member=(Member) spSname.getSelectedItem();
                        stName=member.getStudentName();
                        srollno=member.getRollno();
                        memberid=member.getId();
                       edtrollno.setText(srollno);
                       edtmember_id.setText(memberid+"");

                        Toast.makeText(getApplicationContext(),stName+srollno+memberid,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {

            }
        });

        Call<List<Book1>> getBook=apiInterface.getBook1();

        getBook.enqueue(new Callback<List<Book1>>() {
            @Override
            public void onResponse(Call<List<Book1>> call, Response<List<Book1>> response) {


                book1List=response.body();

                book1ArrayAdapter=new ArrayAdapter(BorrowBookActivity.this, android.R.layout.simple_spinner_item,book1List);
                book1ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spborrowTitle.setAdapter(book1ArrayAdapter);





                spborrowTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Book1 book1=(Book1) spborrowTitle.getSelectedItem();
                        stTitle=book1.getTitle();
                        sauthor=book1.getAuthor().getName();
                        bookid=book1.getId();
                      //   edtAuthor.setText(sauthor);
                        FontEmbedder.force(edtAuthor,sauthor);
                       edtbook_id.setText(bookid+"");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


            }

            @Override
            public void onFailure(Call<List<Book1>> call, Throwable t) {

            }
        });


        edtDate.setOnClickListener(new View.OnClickListener() {
            Calendar c;
            DatePickerDialog datePickerDialog;
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_YEAR);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(BorrowBookActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        edtDate.setText(day + "/" + month+ "/" + year);

                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });



        btnBorrow.setOnClickListener(new View.OnClickListener() {
            String stuName,rollNo,title,author,date;
            int member_id,book_id;

            @Override

            public void onClick(View view) {

                rollNo=edtrollno.getText().toString();
                title=spborrowTitle.getSelectedItem().toString();
                stuName=spSname.getSelectedItem().toString();
                member_id=Integer.parseInt(edtmember_id.getText().toString());
                book_id=Integer.parseInt(edtbook_id.getText().toString());
                author=edtAuthor.getText().toString();
                date=edtDate.getText().toString();

               // Toast.makeText(getApplicationContext(),rollNo+author+date+title+member_id+book_id+stuName,Toast.LENGTH_LONG).show();
                apiInterface= ApiUtils.getApi();
                Call<String>EntryBorrowBook=apiInterface.EntryBorrowBook(stuName,rollNo,title,author,member_id,book_id,date);

                EntryBorrowBook.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(getApplicationContext(),response.body()+"",Toast.LENGTH_LONG).show();
                        edtrollno.setText("");
                        edtAuthor.setText("");
                        edtDate.setText("");
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });






    }
}
