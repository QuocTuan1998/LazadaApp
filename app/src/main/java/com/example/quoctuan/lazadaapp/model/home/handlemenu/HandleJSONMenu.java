package com.example.quoctuan.lazadaapp.model.home.handlemenu;

import com.example.quoctuan.lazadaapp.connect.DownloadJSON;
import com.example.quoctuan.lazadaapp.model.object.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by quoctuan on 09/03/2018.
 */

public class HandleJSONMenu {

    public List<LoaiSanPham> parserJSONMenu(String dataJSON) {
        List<LoaiSanPham> listLoaiSP = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(dataJSON);
            JSONArray loaisanpham = object.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = loaisanpham.getJSONObject(i);

                LoaiSanPham dataLoaiSanPham = new LoaiSanPham();
                dataLoaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataLoaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataLoaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));

                listLoaiSP.add(dataLoaiSanPham);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listLoaiSP;
    }

    public List<LoaiSanPham> getLoaiSPWithMaLoai(int maLoaiSP) {

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String,String> > attr = new ArrayList<>();
        String url = "http://10.0.2.2/weblazada/loaisanpham.php";
        String dataJSON = "";

        HashMap<String, String> hashMapParentCode = new HashMap<>();
        hashMapParentCode.put("maloaicha", String.valueOf(maLoaiSP));
        attr.add(hashMapParentCode);
        DownloadJSON downloadJSON = new DownloadJSON(url, attr);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            HandleJSONMenu handleJSONMenu = new HandleJSONMenu();
            loaiSanPhamList = handleJSONMenu.parserJSONMenu(dataJSON);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return  loaiSanPhamList;
    }

}
