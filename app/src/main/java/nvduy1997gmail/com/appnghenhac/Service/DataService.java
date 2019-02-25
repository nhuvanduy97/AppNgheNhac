package nvduy1997gmail.com.appnghenhac.Service;

import java.util.List;

import nvduy1997gmail.com.appnghenhac.Model.Album;
import nvduy1997gmail.com.appnghenhac.Model.BaiHat;
import nvduy1997gmail.com.appnghenhac.Model.ChuDe;
import nvduy1997gmail.com.appnghenhac.Model.ChuDeTheLoai;
import nvduy1997gmail.com.appnghenhac.Model.PlayList;
import nvduy1997gmail.com.appnghenhac.Model.QuangCao;
import nvduy1997gmail.com.appnghenhac.Model.TheLoai;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("songbaner.php")
    Call<List<QuangCao>> getDataBanner();

    @GET("playList.php")
    Call<List<PlayList>> getDataPlayList();

    @GET("theloaiandchude.php")
    Call<ChuDeTheLoai> getChuDeTheLoai();

    @GET("albumhot.php")
    Call<List<Album>> getAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> getListBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDSBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<PlayList>> getDanhSachPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("allofchude.php")
    Call<List<ChuDe>> getAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getTheLoaiTheChuDe(@Field("idChude") String idChude);

    @GET("allofalbum.php")
    Call<List<Album>> getAlOfAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updatelike.php")
    Call<String> upDateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);
}
