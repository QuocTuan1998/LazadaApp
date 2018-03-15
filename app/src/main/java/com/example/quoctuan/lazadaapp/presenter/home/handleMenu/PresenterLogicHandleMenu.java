package com.example.quoctuan.lazadaapp.presenter.home.handleMenu;

import com.example.quoctuan.lazadaapp.connect.DownloadJSON;
import com.example.quoctuan.lazadaapp.model.home.handlemenu.HandleJSONMenu;
import com.example.quoctuan.lazadaapp.model.object.LoaiSanPham;
import com.example.quoctuan.lazadaapp.presenter.home.view.ViewHandleMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by quoctuan on 09/03/2018.
 * url local virtualBox android is 10.0.2.2
 */

public class PresenterLogicHandleMenu implements IPresenterHandleMenu{

    ViewHandleMenu viewHandleMenu;

    public PresenterLogicHandleMenu(ViewHandleMenu viewHandleMenu) {
        this.viewHandleMenu = viewHandleMenu;
    }

    @Override
    public void getListMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String,String> > attr = new ArrayList<>();
        //GET method
        //String url = "http://10.0.2.2/weblazada/loaisanpham.php?maloaicha=0";

        //POST method

        String url = "http://10.0.2.2/weblazada/loaisanpham.php";
        HashMap<String, String> hashMapParentCode = new HashMap<>();
        hashMapParentCode.put("maloaicha", "0");
        attr.add(hashMapParentCode);
        DownloadJSON downloadJSON = new DownloadJSON(url, attr);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            HandleJSONMenu handleJSONMenu = new HandleJSONMenu();
            loaiSanPhamList = handleJSONMenu.parserJSONMenu(dataJSON);
            viewHandleMenu.showMenu(loaiSanPhamList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
