package com.example.asus.kurangcerdas.network;

import com.example.asus.kurangcerdas.model.ResponseBerita;
import com.example.asus.kurangcerdas.model.ResponseInsert;
import com.example.asus.kurangcerdas.model.ResponseNoUrut;
import com.example.asus.kurangcerdas.model.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    //read
    @FormUrlEncoded
    @POST("read/login_user.php")
    Call<ResponseUser> login_user(@Field("nama") String id, @Field("password") String password);

    @GET("read/no_user.php")
    Call<ResponseNoUrut> getNoUrut();

    @GET("read/berita.php")
    Call<ResponseBerita> getBerita();





    //create
    @FormUrlEncoded
    @POST("create/user.php")
    Call<ResponseInsert> insretUser(@Field("nik") String nik, @Field("nama") String nama,
                                       @Field("tempat_lahir") String tempat_lahir, @Field("tanggal_lahir") String tanggal_lahir,  @Field("jenkel") String jenkel,
                                       @Field("alamat") String alamat,  @Field("rt") String rt, @Field("desa") String desa,  @Field("kec") String kec,
                                        @Field("kabupaten") String kabupaten,  @Field("agama") String agama, @Field("password") String password);


    @FormUrlEncoded
    @POST("create/user.php")
    Call<ResponseInsert> insretLap(@Field("no_pelapor") String no_pelapor, @Field("hub_pelap") String hub_pelap,
                                   @Field("nama_korban") String nama_korban, @Field("jk_korban") String jk_korban, @Field("usia_korb") String usia_korb,
                                   @Field("alamat_korban") String alamat_korban, @Field("no_korban") String no_korban, @Field("nm_pelaku") String nm_pelaku, @Field("jk_pelaku") String jk_pelaku,
                                   @Field("alamat_pelaku") String alamat_pelaku, @Field("hub_pelaku") String hub_pelaku, @Field("no_pelaku") String no_pelaku, @Field("jenis_ks") String jenis_ks, @Field("tgl_kej") String tgl_kej, @Field("tempat") String tempat, @Field("kronologi") String kronologi);


    //update
    @FormUrlEncoded
    @POST("update/user.php")
    Call<ResponseInsert> updateUser(@Field("nik") String nik,@Field("password") String password);
}