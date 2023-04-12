package com.example.ui;

import android.view.View;

import com.example.ui.value.BoardValue;

import java.util.ArrayList;

public interface OnItemClickListener {
    void onItemSelected(View view, int position, ArrayList<BoardValue> items);
}
