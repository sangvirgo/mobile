package com.sangptit.nguyenluutansang_n22dccn068.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.sangptit.nguyenluutansang_n22dccn068.R;
import com.sangptit.nguyenluutansang_n22dccn068.models.Book;

import java.util.ArrayList;

public class CartListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Book> bookList;

    public CartListAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        ImageView imgCartBook = convertView.findViewById(R.id.imgCartBook);
        TextView tvCartTitle = convertView.findViewById(R.id.tvCartTitle);
        TextView tvCartGenre = convertView.findViewById(R.id.tvCartGenre);
        TextView tvCartPrice = convertView.findViewById(R.id.tvCartPrice);
        CheckBox checkBoxCart = convertView.findViewById(R.id.checkBoxCart);

        Book book = bookList.get(position);

        imgCartBook.setImageResource(book.getImageResId());
        tvCartTitle.setText(book.getTitle());
        tvCartGenre.setText(book.getAuthor());
        tvCartPrice.setText(book.getPrice());

        return convertView;
    }
}
