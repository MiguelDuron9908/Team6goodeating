package com.example.goodeatingfin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FragmentCuatro extends Fragment {
    private View fragmento;

    private ImageView alimentos;
    private ImageView bebidas;



    public FragmentCuatro() {}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cuatro, container, false);
    }
}