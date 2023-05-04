package com.kd.appweather;

import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kd.appweather.beans.Elements;
import com.kd.appweather.beans.FengSu;
import com.kd.appweather.beans.JsonMapper;
import com.kd.appweather.beans.Oxy;
import com.kd.appweather.beans.ShiDu;
import com.kd.appweather.beans.Wea;
import com.kd.appweather.beans.WenDu;
import com.kd.appweather.beans.YuLiang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataModel {
    private Retrofit retrofit,download;
    private HttpApis apis,dapis;
    private DataModel() {
        retrofit = new Retrofit.Builder().baseUrl("http://61.153.246.242:8888/qxdata/QxService.svc/").build();//61.153.246.242:8888
        download = new Retrofit.Builder().baseUrl("http://115.220.4.68:8081/qxdata/QxService.svc/").build();
        apis = retrofit.create(HttpApis.class);
        dapis = download.create(HttpApis.class);
    }
    public  final String ldPath = MyApp.getApp().getFilesDir()+"/rad/";
    public final String ytPath = MyApp.getApp().getFilesDir()+"/cloud/";
    private static DataModel dataModel;

    public static DataModel getInstance() {
        if (dataModel == null) {
            synchronized (DataModel.class) {
                if (dataModel == null) {
                    dataModel = new DataModel();
                }
            }
        }
        return dataModel;
    }
    public Elements getSixELements(){
        String s = null;
        try {
            Response<ResponseBody> execute = dapis.getElements().execute();
            if(execute.isSuccessful()&&execute.code()==200){
               s =  execute.body().string();
                Log.i("TAG","Elements"+s);
               if(!TextUtils.isEmpty(s)&&s.length()>10){
                   return JsonMapper.getInstance().readValue(s, Elements.class);
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public List<WenDu> get12Wd(){
        String s = null;
        try {
            Response<ResponseBody> execute = apis.getWd().execute();
            if(execute.isSuccessful()&&execute.code()==200){
                s =  execute.body().string();
                Log.i("TAG","Elements"+s);
                if(!TextUtils.isEmpty(s)&&s.length()>10){
                    List<WenDu> wds= JsonMapper.getInstance().readValue(s,new TypeReference<List<WenDu>>(){});
                    for(WenDu wd:wds){
                        Log.i("TAG","wd:"+wd);
                    }
                    return wds;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  List<ShiDu> get12Shd(){
        String s = null;
        try {
            Response<ResponseBody> execute = apis.getSd().execute();
            if(execute.isSuccessful()&&execute.code()==200){
                s =  execute.body().string();
                Log.i("TAG","Elements"+s);
                if(!TextUtils.isEmpty(s)&&s.length()>10){
                    List<ShiDu> sds= JsonMapper.getInstance().readValue(s,new TypeReference<List<ShiDu>>(){});
                    for(ShiDu wd:sds){
                        Log.i("TAG","wd:"+wd);
                    }
                    return sds;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<YuLiang> get12Yl(){
        String s = null;
        try {
            Response<ResponseBody> execute = apis.getYl().execute();
            if(execute.isSuccessful()&&execute.code()==200){
                s =  execute.body().string();
                Log.i("TAG","Elements"+s);
                if(!TextUtils.isEmpty(s)&&s.length()>10){
                    List<YuLiang> sds= JsonMapper.getInstance().readValue(s,new TypeReference<List<YuLiang>>(){});
                    for(YuLiang wd:sds){
                        Log.i("TAG","wd:"+wd);
                    }
                    return sds;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<FengSu> get12Fs(){
        String s = null;
        try {
            Response<ResponseBody> execute = apis.getFs().execute();
            if(execute.isSuccessful()&&execute.code()==200){
                s =  execute.body().string();
                Log.i("TAG","Elements"+s);
                if(!TextUtils.isEmpty(s)&&s.length()>10){
                    List<FengSu> sds= JsonMapper.getInstance().readValue(s,new TypeReference<List<FengSu>>(){});
                    for(FengSu wd:sds){
                        Log.i("TAG","wd:"+wd);
                    }
                    return sds;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean getYunTu(int index){

        try {
            Response<ResponseBody> execute = dapis.getYunTu(index).execute();
            if(execute.isSuccessful()&&execute.code()==200){
               return writeResponseBodyToDisk(execute.body(),ytPath,index+".jpg");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean getLeiDaTu(int index){
        try {
            Response<ResponseBody> execute = dapis.getLeiDa(index).execute();
            if(execute.isSuccessful()&&execute.code()==200){
                return writeResponseBodyToDisk(execute.body(),ldPath,index+".gif");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public SevenWea getSevenWea(){
        String s = null;
        try {
            Response<ResponseBody> execute = apis.getSevenWea().execute();
            if(execute.isSuccessful()&&execute.code()==200){
                s =  execute.body().string();
                Log.i("TAG","seven"+s);
                if(!TextUtils.isEmpty(s)&&s.length()>10){

                    return JsonMapper.getInstance().readValue(s,SevenWea.class);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
    public String getWea(){
        String s = null;
        try {
            Response<ResponseBody> execute = dapis.getWeather("57962").execute();
            if(execute.isSuccessful()&&execute.code()==200){
                s =  execute.body().string();
                Log.i("TAG","wea:"+s);
                if(!TextUtils.isEmpty(s)&&s.length()>10){
                    Wea wea = JsonMapper.getInstance().readValue(s, Wea.class);
                    return wea.wea_txt1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;

    }
    public Oxy getOxy(){
        String s = null;
        try {
            Response<ResponseBody> execute = dapis.getOxy().execute();
            if(execute.isSuccessful()&&execute.code()==200){
                s =  execute.body().string();
                Log.i("TAG","wea:"+s);
                if(!TextUtils.isEmpty(s)&&s.length()>10){
                    Oxy oxy = JsonMapper.getInstance().readValue(s, Oxy.class);
                    return oxy;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String getTime(){
        return null;
    }
    private boolean writeResponseBodyToDisk(ResponseBody body,String path,String name) {
        try {
            //判断文件夹是否存在
            File files = new File(path);//跟目录一个文件夹
            if (!files.exists()) {
                //不存在就创建出来
                files.mkdirs();
            }
            //创建一个文件
            File futureStudioIconFile = new File(path+name);
            //初始化输入流
            InputStream inputStream = null;
            //初始化输出流
            OutputStream outputStream = null;
            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                //请求返回的字节流
                inputStream = body.byteStream();
                //创建输出流
                outputStream = new FileOutputStream(futureStudioIconFile);
                //进行读取操作
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    //进行写入操作
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }

                //刷新
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }
                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
