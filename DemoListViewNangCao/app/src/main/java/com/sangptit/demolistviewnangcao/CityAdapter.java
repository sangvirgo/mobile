package com.sangptit.demolistviewnangcao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<City> cityList;

    // Constructor
    public CityAdapter(Context context, int layout, List<City> cityList) {
        this.context = context;
        this.layout = layout;
        this.cityList = cityList;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // ViewHolder pattern để tối ưu performance
    private class ViewHolder {
        TextView txtTen, txtlink;
        ImageView imgHinh;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            // Nạp layout cho 1 dòng
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            // Ánh xạ
            holder = new ViewHolder();
            holder.txtTen = convertView.findViewById(R.id.txtTen);
            holder.txtlink = convertView.findViewById(R.id.txtlink);
            holder.imgHinh = convertView.findViewById(R.id.imgHinh);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Lấy dữ liệu tại vị trí position
        City city = cityList.get(position);

        // Gán dữ liệu
        holder.txtTen.setText(city.getNameCity());
        holder.txtlink.setText(city.getLinkWiki());
        holder.imgHinh.setImageResource(city.getHinh());

        return convertView;
    }
}