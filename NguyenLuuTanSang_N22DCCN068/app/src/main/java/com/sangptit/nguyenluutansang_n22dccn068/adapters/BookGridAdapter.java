package com.sangptit.nguyenluutansang_n22dccn068.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sangptit.nguyenluutansang_n22dccn068.R;
import com.sangptit.nguyenluutansang_n22dccn068.models.Book;

import java.util.ArrayList;

public class BookGridAdapter extends BaseAdapter {

    Context context;
    ArrayList<Book> bookList;

    public BookGridAdapter(Context context, ArrayList<Book> bookList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book_grid, parent, false);
        }

        ImageView imgBook = convertView.findViewById(R.id.imgBook);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvAuthor);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);

        Book book = bookList.get(position);

        imgBook.setImageResource(book.getImageResId());
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvPrice.setText(book.getPrice());

        return convertView;
    }
}
