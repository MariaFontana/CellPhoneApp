package com.example.acer.mynewponeapp.Util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class verticalSpacignDecorator extends RecyclerView.ItemDecoration {


    private final int verticalSpaceHigth;

    public verticalSpacignDecorator(int verticalSpaceHigth) {
        this.verticalSpaceHigth = verticalSpaceHigth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom=verticalSpaceHigth;
    }
}
