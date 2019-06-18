package com.example.asus.kurangcerdas;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.asus.kurangcerdas.helper.FunctionError;
import com.example.asus.kurangcerdas.model.ResponseInsert;
import com.example.asus.kurangcerdas.network.ApiService;
import com.example.asus.kurangcerdas.network.RetroClient;
import com.example.asus.kurangcerdas.shared.SharedLogin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.asus.kurangcerdas.helper.ConvertDate.ubahTanggal2;
import static com.example.asus.kurangcerdas.helper.ConvertDate.ubahTanggal3;
import static com.example.asus.kurangcerdas.helper.FunctionError.cekEditText;
import static com.example.asus.kurangcerdas.helper.FunctionError.getTextEditText;
import static com.example.asus.kurangcerdas.helper.FunctionError.getTextView;
import static com.example.asus.kurangcerdas.helper.FunctionError.setErrorEditText;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_AGAMA;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_ALAMAT;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_DESA;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_JENKEL;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_KAB;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_KEC;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_NAMA;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_NIK;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_RT;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_TANGGAL;
import static com.example.asus.kurangcerdas.shared.SharedLogin.SP_TEMPAT;

public class Lappribadi extends AppCompatActivity {
    ProgressDialog progressDialog;
    SharedLogin sharedLogin;
    @BindView(R.id.txt_lahir)
    TextView edtTanggal;
    @BindView(R.id.tx1)
    TextView tx1;
    @BindView(R.id.tx2)
    TextView tx2;
    @BindView(R.id.tx3)
    TextView tx3;
    @BindView(R.id.tx4)
    TextView tx4;
    @BindView(R.id.tx5)
    TextView tx5;
    @BindView(R.id.tx6)
    TextView tx6;
    @BindView(R.id.tx7)
    TextView tx7;
    @BindView(R.id.tx8)
    TextView tx8;
    @BindView(R.id.tx9)
    TextView tx9;
    @BindView(R.id.tx10)
    TextView tx10;
    @BindView(R.id.tx11)
    TextView tx11;
    @BindView(R.id.tx12)
    EditText tx12;
    @BindView(R.id.edt_hub)
    Spinner edtHub;
    @BindView(R.id.tx13)
    EditText tx13;
    @BindView(R.id.laki)
    RadioButton laki;
    @BindView(R.id.perempuan)
    RadioButton perempuan;
    @BindView(R.id.edt_jenkel)
    RadioGroup edtJenkel;
    @BindView(R.id.tx14)
    EditText tx14;
    @BindView(R.id.tx15)
    EditText tx15;
    @BindView(R.id.tx16)
    EditText tx16;
    @BindView(R.id.tx17)
    EditText tx17;
    @BindView(R.id.laki1)
    RadioButton laki1;
    @BindView(R.id.perempuan2)
    RadioButton perempuan2;
    @BindView(R.id.edt_jenkel1)
    RadioGroup edtJenkel1;
    @BindView(R.id.tx18)
    EditText tx18;
    @BindView(R.id.edt_hub1)
    Spinner edtHub1;
    @BindView(R.id.tx20)
    EditText tx20;
    @BindView(R.id.edt_ks)
    Spinner edtKs;
    @BindView(R.id.tx21)
    EditText tx21;
    @BindView(R.id.tx22)
    EditText tx22;
    @BindView(R.id.btn_buatlap)
    Button btnBuatlap;
    @BindView(R.id.open_image)
    Button openImage;
    @BindView(R.id.txt_view)
    TextView txtView;
    @BindView(R.id.showImg)
    ImageView showImg;
    private Calendar myCalendar;
    String hub, hub1, ks, jk, jk1;

    //Request Code Digunakan Untuk Menentukan Permintaan dari User
    public static final int REQUEST_CODE_CAMERA = 001;
    public static final int REQUEST_CODE_GALLERY = 002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lappribadi);
        ButterKnife.bind(this);
        sharedLogin = new SharedLogin(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Tunggu Sebentar...");

        edtJenkel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                jk = rb.getText().toString();
            }
        });

        edtJenkel1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                jk1 = rb.getText().toString();
            }
        });


        //spiner status
        final ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(this, R.array.Hub,
                R.layout.support_simple_spinner_dropdown_item);
        edtHub.setAdapter(adapterStatus);

        //select sp
        edtHub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hub = adapterStatus.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spiner status
        final ArrayAdapter<CharSequence> adapterStatus1 = ArrayAdapter.createFromResource(this, R.array.Hub,
                R.layout.support_simple_spinner_dropdown_item);
        edtHub1.setAdapter(adapterStatus);

        //select sp
        edtHub1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hub1 = adapterStatus1.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spiner status
        final ArrayAdapter<CharSequence> adapterStatus2 = ArrayAdapter.createFromResource(this, R.array.Kasus,
                R.layout.support_simple_spinner_dropdown_item);
        edtKs.setAdapter(adapterStatus);

        //select sp
        edtKs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ks = adapterStatus2.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (sharedLogin.getSharedSudahLogin()) {
            //cache
            tx1.setText(sharedLogin.getSharedString(SP_NIK));
            tx2.setText(sharedLogin.getSharedString(SP_NAMA));
            tx3.setText(sharedLogin.getSharedString(SP_TEMPAT));
            tx4.setText(sharedLogin.getSharedString(SP_TANGGAL));
            tx5.setText(sharedLogin.getSharedString(SP_JENKEL));
            tx6.setText(sharedLogin.getSharedString(SP_ALAMAT));
            tx7.setText(sharedLogin.getSharedString(SP_RT));
            tx8.setText(sharedLogin.getSharedString(SP_DESA));
            tx9.setText(sharedLogin.getSharedString(SP_KEC));
            tx10.setText(sharedLogin.getSharedString(SP_KAB));
            tx11.setText(sharedLogin.getSharedString(SP_AGAMA));

        }
        myCalendar = Calendar.getInstance();

    }

    private void kosongkan() {
        FunctionError.kosongkan(tx12);
        FunctionError.kosongkan(tx13);
        FunctionError.kosongkan(tx14);
        FunctionError.kosongkan(tx14);
        FunctionError.kosongkan(tx15);
        FunctionError.kosongkan(tx16);
        FunctionError.kosongkan(tx17);
        FunctionError.kosongkan(tx18);
        FunctionError.kosongkan(tx20);
        FunctionError.kosongkan(tx21);
        FunctionError.kosongkan(tx22);
    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @OnClick({R.id.txt_lahir, R.id.open_image, R.id.txt_view, R.id.btn_buatlap})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_image:
                setRequestImage();
                break;
            case R.id.txt_view:
                if (showImg.getVisibility() == View.GONE) {
                    showImg.setVisibility(View.VISIBLE);
                    Drawable showImg = getResources().getDrawable(R.drawable.ic_visibility_grey_900_24dp);
                    //sesuaikan image sesuai isian bisa di start/awal, top/atas,end/akhir, bottom/bawah
                    txtView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, showImg, null);
                } else {
                    showImg.setVisibility(View.GONE);
                    Drawable showImg = getResources().getDrawable(R.drawable.ic_eye_hide
                    );
                    //sesuaikan image sesuai isian bisa di start/awal, top/atas,end/akhir, bottom/bawah
                    txtView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, showImg, null);
                }
                break;
            case R.id.txt_lahir:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "yyyy-MM-dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        Toast.makeText(Lappribadi.this, "" + ubahTanggal2(sdf.format(myCalendar.getTime())), Toast.LENGTH_SHORT).show();
                        edtTanggal.setText(ubahTanggal2(sdf.format(myCalendar.getTime())));
                    }
                },
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_buatlap:
                switch (view.getId()) {
                    case R.id.btn_buatlap:
                        if (cekEditText(tx12)) {
                            setErrorEditText(tx12, "Kosong");
                        } else if (cekEditText(tx13)) {
                            setErrorEditText(tx13, "Kosong");
                        } else if (cekEditText(tx14)) {
                            setErrorEditText(tx14, "Kosong");
                        } else if (cekEditText(tx15)) {
                            setErrorEditText(tx15, "Kosong");
                        } else if (cekEditText(tx16)) {
                            setErrorEditText(tx16, "Kosong");
                        } else if (cekEditText(tx17)) {
                            setErrorEditText(tx17, "Kosong");
                        } else if (cekEditText(tx18)) {
                            setErrorEditText(tx18, "Kosong");
                        } else if (cekEditText(tx20)) {
                            setErrorEditText(tx20, "Kosong");
                        } else if (cekEditText(tx21)) {
                            setErrorEditText(tx21, "Kosong");
                        } else if (cekEditText(tx22)) {
                            setErrorEditText(tx22, "Kosong");
                        } else {
                            simpan(getTextEditText(tx12), hub,
                                    getTextEditText(tx13), jk, getTextEditText(tx14), getTextEditText(tx15), getTextEditText(tx16),
                                    getTextEditText(tx17), jk1, getTextEditText(tx18), hub1, getTextEditText(tx20),
                                    ks, ubahTanggal3(getTextView(edtTanggal)), getTextEditText(tx22), getTextEditText(tx22));
                        }
                        break;
                }
        }
    }

    //Method Ini Digunakan Untuk Membuka Image dari Galeri atau Kamera
    private void setRequestImage(){
        CharSequence[] item = {"Kamera", "Galeri"};
        AlertDialog.Builder request = new AlertDialog.Builder(this)
                .setTitle("Add Image")
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                //Membuka Kamera Untuk Mengambil Gambar
                                EasyImage.openCamera(Lappribadi.this, REQUEST_CODE_CAMERA);
                                break;
                            case 1:
                                //Membuaka Galeri Untuk Mengambil Gambar
                                EasyImage.openGallery(Lappribadi.this, REQUEST_CODE_GALLERY);
                                break;
                        }
                    }
                });
        request.create();
        request.show();
    }


    //Method Ini Digunakan Untuk Menapatkan Hasil pada Activity, dari Proses Yang kita buat sebelumnya
    //Dan Mendapatkan Hasil File Photo dari Galeri atau Kamera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Method Ini Digunakan Untuk Menghandle Error pada Image
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //Method Ini Digunakan Untuk Menghandle Image
                switch (type) {
                    case REQUEST_CODE_CAMERA:
                        Glide.with(Lappribadi.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(showImg);
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(Lappribadi.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(showImg);
                        break;
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Batalkan penanganan, Anda mungkin ingin menghapus foto yang diambil jika dibatalkan
            }
        });
    }


    private void simpan(final String no_pelapor, final String hub_pelap, final String nama_korban, final String jk_korban, final String usia_korb, final String alamat_korban, final String no_korban, final String nm_pelaku, final String jk_pelaku, final String alamat_pelaku, final String hub_pelaku, final String no_pelaku, final String jenis_ks, final String tgl_kej, final String tempat, final String kronologi) {
        progressDialog.show();
        ApiService service = RetroClient.getApiService();
        Call<ResponseInsert> call = service.insretLap(no_pelapor, hub_pelap, nama_korban, jk_korban, usia_korb, alamat_korban, no_korban, nm_pelaku, jk_pelaku, alamat_pelaku, hub_pelaku, no_pelaku, jenis_ks, tgl_kej, tempat, kronologi);
        call.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                progressDialog.dismiss();
                if (response.body().getCode() == 200) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    kosongkan();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Log.e("error", t.getMessage());
            }
        });
    }

    }
