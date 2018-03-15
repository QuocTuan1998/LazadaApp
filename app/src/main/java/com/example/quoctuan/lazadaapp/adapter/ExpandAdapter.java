package com.example.quoctuan.lazadaapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoctuan.lazadaapp.R;
import com.example.quoctuan.lazadaapp.model.home.handlemenu.HandleJSONMenu;
import com.example.quoctuan.lazadaapp.model.object.LoaiSanPham;

import java.util.List;

/**
 * Created by quoctuan on 10/03/2018.
 */

public class ExpandAdapter extends BaseExpandableListAdapter{

    Context context;
    List<LoaiSanPham> loaiSanPhamList;
    ViewHolderMenu menu;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhamList) {
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;

        HandleJSONMenu handleJSONMenu = new HandleJSONMenu();

        int count = loaiSanPhamList.size();
        for (int i =0; i < count; i++) {
            int maLoaiSP = loaiSanPhamList.get(i).getMALOAISP();
            loaiSanPhamList.get(i).setList_child( handleJSONMenu.getLoaiSPWithMaLoai(maLoaiSP));
        }


    }

    @Override
    public int getGroupCount() {
        return loaiSanPhamList.size();
    }

    @Override
    public int getChildrenCount(int groupParentPos) {
        if (loaiSanPhamList.get(groupParentPos).getList_child().size() != 0) {
            return 1;
        }else {
            return 0;
        }
//        Log.d("list ", loaiSanPhamList.get(groupParentPos).getList_child().size() + "");
//        return 1;
    }

    @Override
    public Object getGroup(int groupParentPos) {
        return loaiSanPhamList.get(groupParentPos);
    }

    @Override
    public Object getChild(int groupParentPos, int groupChildPos) {
        return loaiSanPhamList.get(groupParentPos).getList_child().get(groupChildPos);
    }

    @Override
    public long getGroupId(int groupParentPos) {
        return loaiSanPhamList.get(groupParentPos).getMALOAISP();
    }

    @Override
    public long getChildId(int groupParentPos, int groupChildPos) {
        return loaiSanPhamList.get(groupParentPos).getList_child().get(groupChildPos).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu {
        TextView txt_name_loaiSP;
        ImageView image_menu;

    }

    @Override
    public View getGroupView(final int groupParentPos, boolean isExpand, View view, ViewGroup viewGroup) {

        View viewParent = view;
        if (viewParent == null) {
            menu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewParent = layoutInflater.inflate(R.layout.custom_layout_group_parent,viewGroup,false);

            menu.image_menu = viewParent.findViewById(R.id.image_menu);
            menu.txt_name_loaiSP = viewParent.findViewById(R.id.txt_name_loaiSP);

            viewParent.setTag(menu);
        }else {
           menu = (ViewHolderMenu) viewParent.getTag();
        }


        menu.txt_name_loaiSP.setText(loaiSanPhamList.get(groupParentPos).getTENLOAISP());

        int countListChild = loaiSanPhamList.get(groupParentPos).getList_child().size();

        if (countListChild > 0) {
            menu.image_menu.setVisibility(View.VISIBLE);
        }else {
            menu.image_menu.setVisibility(View.INVISIBLE);
        }

        if (isExpand) {
            menu.image_menu.setImageResource(R.drawable.ic_remove_black_24dp);
            viewParent.setBackgroundColor(Color.GRAY);
        }else {
            menu.image_menu.setImageResource(R.drawable.ic_add_black_24dp);
        }

        viewParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("loai san pham", loaiSanPhamList.get(groupParentPos).getTENLOAISP() +
                        loaiSanPhamList.get(groupParentPos).getMALOAISP());

                return false;
            }
        });

        return viewParent;
    }

    @Override
    public View getChildView(int groupParentPos, int groupChildPos, boolean isExpand, View view, ViewGroup viewGroup) {

//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        View viewChild = layoutInflater.inflate(R.layout.custom_layout_group_child,viewGroup,false);
//        ExpandableListView expandableListView = viewChild.findViewById(R.id.expand_menu_child);
        SecondExpand secondExpand = new SecondExpand(context);

        ExpandAdapter secondAdapterter = new ExpandAdapter(context,
                loaiSanPhamList.get(groupParentPos).getList_child());
        secondExpand.setGroupIndicator(null);
        secondExpand.setAdapter(secondAdapterter);
        notifyDataSetChanged();

        return secondExpand;
    }

    public class SecondExpand extends ExpandableListView {

        public SecondExpand(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }



//    public class SecondAdapter extends BaseExpandableListAdapter {
//
//        List<LoaiSanPham> listChild;
//
//        public SecondAdapter(List<LoaiSanPham> listChild) {
//            this.listChild = listChild;
//
//            HandleJSONMenu handleJSONMenu = new HandleJSONMenu();
//
//            int count = listChild.size();
//            for (int i =0; i < count; i++) {
//                int maLoaiSP = listChild.get(i).getMALOAISP();
//                listChild.get(i).setList_child( handleJSONMenu.getLoaiSPWithMaLoai(maLoaiSP));
//            }
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listChild.size();
//        }
//
//        @Override
//        public int getChildrenCount(int groupParentPos) {
//            return listChild.get(groupParentPos).getList_child().size();
//        }
//
//        @Override
//        public Object getGroup(int groupParentPos) {
//            return listChild.get(groupParentPos);
//        }
//
//        @Override
//        public Object getChild(int groupParentPos, int groupChildPos) {
//            return listChild.get(groupParentPos).getList_child().get(groupChildPos);
//        }
//
//        @Override
//        public long getGroupId(int groupParentPos) {
//            return listChild.get(groupParentPos).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int groupParentPos, int groupChildPos) {
//            return listChild.get(groupParentPos).getList_child().get(groupChildPos).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int groupParentPos, boolean isExpand, View view, ViewGroup viewGroup) {
//
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//            View viewParent = layoutInflater.inflate(R.layout.custom_layout_group_parent,viewGroup,false);
//            TextView txt_name_loaiSP = viewParent.findViewById(R.id.txt_name_loaiSP);
//            txt_name_loaiSP.setText(listChild.get(groupParentPos).getTENLOAISP());
//
//            return viewParent;
//        }
//
//        @Override
//        public View getChildView(int groupParentPos, int groupChildPos, boolean isExpand, View view, ViewGroup viewGroup) {
//
//            TextView tv = new TextView(context);
//            tv.setText(listChild.get(groupParentPos).getList_child().get(groupChildPos).getTENLOAISP());
//            tv.setPadding(15, 5, 5, 5);
//            tv.setBackgroundColor(Color.RED);
//            tv.setTextSize(22);
//            tv.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//            return tv;
//        }
//
//        @Override
//        public boolean isChildSelectable(int i, int i1) {
//            return false;
//        }
//
//
//    }

}
