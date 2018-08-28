package com.wmtbr.revisao.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wmtbr.revisao.R;
import com.wmtbr.revisao.adapter.BookAdapter;
import com.wmtbr.revisao.adapter.WillAdapter;
import com.wmtbr.revisao.helpers.RecyclerViewClickListener;
import com.wmtbr.revisao.helpers.RecyclerViewTouchListener;
import com.wmtbr.revisao.models.Book;
import com.wmtbr.revisao.models.Posts;
import com.wmtbr.revisao.network.ApiService;
import com.wmtbr.revisao.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Book> bookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Posts> postsList = new ArrayList<>();
    private WillAdapter postAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("CARREGANDO");
        progressDialog.show();

        initRetrofit();
    }


    private void initRetrofit() {

        ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<List<Posts>> call = service.getAllPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                progressDialog.dismiss();
                genarateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }




 private void genarateDataList(final List<Posts>postsList) {

     recyclerView = (RecyclerView) findViewById(R.id.recycler_main);

     recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
         @Override
         public void onClick(View view, int position) {
             Toast.makeText(getApplicationContext(), postsList.get(position).getId() + " is clicked!", Toast.LENGTH_SHORT).show();

         }

         @Override
         public void onLongClick(View view, int position) {
             Toast.makeText(getApplicationContext(), postsList.get(position).getTitle() + " is long pressed!", Toast.LENGTH_SHORT).show();

         }
     }));

     postAdapter = new WillAdapter(postsList);
     RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
     recyclerView.setLayoutManager(mLayoutManager);
     recyclerView.setItemAnimator(new DefaultItemAnimator());
     recyclerView.setAdapter(postAdapter);



 }






/*

     bookAdapter = new BookAdapter(bookList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bookAdapter);



        initBookData();


    }

    private void initBookData() {
        Book book = new Book("Hello Android", "Ed Burnette");
        bookList.add(book);

        book = new Book("Beginning Android 3", "Mark Murphy");
        bookList.add(book);

        book = new Book("Unlocking Android", " W. Frank Ableson");
        bookList.add(book);

        book = new Book("Android Tablet Development", "Wei Meng Lee");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        book = new Book("Android Apps Security", "Sheran Gunasekera");
        bookList.add(book);

        bookAdapter.notifyDataSetChanged();
    }*/
}
