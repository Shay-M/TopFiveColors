package com.silverhorse.topfivecolors.ui.fivecolors;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silverhorse.topfivecolors.R;

public class InfoColorsFragment extends Fragment {

    public static InfoColorsFragment newInstance() {
        return new InfoColorsFragment();
    }

    private InfoColorsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_colors, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InfoColorsViewModel.class);

        TextView textView1 = view.findViewById(R.id.textView1);
        View colorBox1 = view.findViewById(R.id.colorBox1);
        TextView textView2 = view.findViewById(R.id.textView2);
        View colorBox2 = view.findViewById(R.id.colorBox2);
        TextView textView3 = view.findViewById(R.id.textView3);
        View colorBox3 = view.findViewById(R.id.colorBox3);
        TextView textView4 = view.findViewById(R.id.textView4);
        View colorBox4 = view.findViewById(R.id.colorBox4);
        TextView textView5 = view.findViewById(R.id.textView5);
        View colorBox5 = view.findViewById(R.id.colorBox5);

        mViewModel.getColor1().observe(getViewLifecycleOwner(), colorBox1::setBackgroundColor);
        mViewModel.getColor2().observe(getViewLifecycleOwner(), colorBox2::setBackgroundColor);
        mViewModel.getColor3().observe(getViewLifecycleOwner(), colorBox3::setBackgroundColor);
        mViewModel.getColor4().observe(getViewLifecycleOwner(), colorBox4::setBackgroundColor);
        mViewModel.getColor5().observe(getViewLifecycleOwner(), colorBox5::setBackgroundColor);

        mViewModel.getText1().observe(getViewLifecycleOwner(), textView1::setText);
        mViewModel.getText2().observe(getViewLifecycleOwner(), textView2::setText);
        mViewModel.getText3().observe(getViewLifecycleOwner(), textView3::setText);
        mViewModel.getText4().observe(getViewLifecycleOwner(), textView4::setText);
        mViewModel.getText5().observe(getViewLifecycleOwner(), textView5::setText);

        mViewModel.setColor1(Color.RED);
        mViewModel.setColor2(Color.GREEN);
        mViewModel.setColor3(Color.BLUE);
        mViewModel.setColor4(Color.YELLOW);
        mViewModel.setColor5(Color.MAGENTA);

        mViewModel.setText1("Text View 1");
        mViewModel.setText2("Text View 2");
        mViewModel.setText3("Text View 3");
        mViewModel.setText4("Text View 4");
        mViewModel.setText5("Text View 5");

    }

}