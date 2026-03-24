package com.sangptit.nguyenluutansang_n22dccn068.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.sangptit.nguyenluutansang_n22dccn068.R;
import com.sangptit.nguyenluutansang_n22dccn068.adapters.CartListAdapter;
import com.sangptit.nguyenluutansang_n22dccn068.models.Book;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    ListView listViewCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        listViewCart = v.findViewById(R.id.listViewCart);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Great Gatsby", "Fantasy", "$12.99", R.drawable.book1));
        bookList.add(new Book("1984", "Dystopian", "$11.99", R.drawable.book3));
        bookList.add(new Book("The Hobbit", "Fantasy", "$15.99", R.drawable.book5));
        bookList.add(new Book("Harry Potter", "Fantasy", "$18.50", R.drawable.book6));

        CartListAdapter adapter = new CartListAdapter(getContext(), bookList);
        listViewCart.setAdapter(adapter);

        return v;
    }
}
