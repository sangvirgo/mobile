package com.sangptit.demogridviewnangcao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HinhAnhAdapter extends BaseAdapter {

    private Context context;           // Context của Activity
    private int layout;                // Layout của mỗi dòng
    private List<HinhAnh> hinhAnhList; // Danh sách dữ liệu

    // Constructor
    public HinhAnhAdapter(Context context, int layout, List<HinhAnh> hinhAnhList) {
        this.context = context;
        this.layout = layout;
        this.hinhAnhList = hinhAnhList;
    }

    @Override
    public int getCount() {
        // Trả về số lượng phần tử trong danh sách
        return hinhAnhList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Class ViewHolder để tối ưu hiệu năng
    private class ViewHolder {
        ImageView imgHinhAnh;
        TextView txtTenHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Nếu convertView null, inflate layout mới
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            // Ánh xạ các view
            holder = new ViewHolder();
            holder.imgHinhAnh = convertView.findViewById(R.id.imgHinhAnh);
            holder.txtTenHinh = convertView.findViewById(R.id.txtTenHinh);

            // Lưu holder vào convertView
            convertView.setTag(holder);
        } else {
            // Nếu đã có convertView, lấy holder từ tag
            holder = (ViewHolder) convertView.getTag();
        }

        // Lấy đối tượng HinhAnh tại vị trí position
        HinhAnh hinhAnh = hinhAnhList.get(position);

        // Gán dữ liệu vào các view
        holder.imgHinhAnh.setImageResource(hinhAnh.getHinh());
        holder.txtTenHinh.setText(hinhAnh.getTen());

        return convertView;
    }
}