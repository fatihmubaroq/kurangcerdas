package com.example.asus.kurangcerdas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.kurangcerdas.adapter.BeritaAdapter;
import com.example.asus.kurangcerdas.model.BeritaItem;
import com.example.asus.kurangcerdas.model.ResponseBerita;
import com.example.asus.kurangcerdas.network.ApiService;
import com.example.asus.kurangcerdas.network.RetroClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Berita extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wrap)
    AppBarLayout wrap;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);
        ButterKnife.bind(this);setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Berita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvList.setLayoutManager(new LinearLayoutManager(this));

        getBerita();
    }

    private void getBerita() {
        pb.setVisibility(View.VISIBLE);
        ApiService apiService = RetroClient.getApiService();
        Call<ResponseBerita> call = apiService.getBerita();
        call.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                List<BeritaItem> hasilPesan = response.body().getBerita();
                Log.e("Tag", "Hasil List :" + new Gson().toJson(hasilPesan));

                if (response.body().getSukses().equalsIgnoreCase("true")) {
                    try {
                        rvList.setVisibility(View.VISIBLE);
                        ArrayList<BeritaItem> list = new ArrayList<>();
                        list.addAll(hasilPesan);

                        BeritaAdapter adapterPesan1 = new BeritaAdapter(Berita.this, list);
                        //  swipeMain.setRefreshing(false);
                        rvList.setAdapter(adapterPesan1);
                        adapterPesan1.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    } catch (Exception e) {
                        Toast.makeText(Berita.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                } else {
                    Log.e("Tag", "Gagal req data ");
                    Toast.makeText(Berita.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                    rvList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(Berita.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
                rvList.setVisibility(View.GONE);
            }
        });
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}

