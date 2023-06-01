package com.example.goodeatingfin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentUno extends Fragment {

    private View fragmento;
    private ImageView drink,pop,dish;
    private ImageView bowl,pear,lemon;

    public FragmentUno() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmento= inflater.inflate(R.layout.fragment_uno, container, false);

        drink=(ImageView) fragmento.findViewById(R.id.drink);
        pop=(ImageView) fragmento.findViewById(R.id.pop);
        dish=(ImageView) fragmento.findViewById(R.id.dish);

        bowl=(ImageView) fragmento.findViewById(R.id.bowl);
        pear =(ImageView) fragmento.findViewById(R.id.pera);
        lemon=(ImageView) fragmento.findViewById(R.id.limonada);

        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),AgregarproductoActivity.class);
                intent.putExtra("categoria","drink");
                startActivity(intent);
            }
        });
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),AgregarproductoActivity.class);
                intent.putExtra("categoria","pop");
                startActivity(intent);
            }
        });
        dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),AgregarproductoActivity.class);
                intent.putExtra("categoria","dish");
                startActivity(intent);
            }
        });
        bowl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),AgregarproductoActivity.class);
                intent.putExtra("categoria","bowl");
                startActivity(intent);
            }
        });
        pear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),AgregarproductoActivity.class);
                intent.putExtra("categoria","pear");
                startActivity(intent);
            }
        });
        lemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),AgregarproductoActivity.class);
                intent.putExtra("categoria","lemon");
                startActivity(intent);
            }
        });
        return fragmento;
    }
}