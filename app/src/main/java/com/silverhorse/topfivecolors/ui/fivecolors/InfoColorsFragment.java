package com.silverhorse.topfivecolors.ui.fivecolors;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silverhorse.topfivecolors.R;
import com.silverhorse.topfivecolors.model.RGBColor;
import com.silverhorse.topfivecolors.ui.shared.SharedViewModel;

import java.util.List;

public class InfoColorsFragment extends Fragment {

    public static InfoColorsFragment newInstance() {
        return new InfoColorsFragment();
    }

    private InfoColorsViewModel mInfoColorsViewModel;
    private SharedViewModel mSharedViewModel;

    private TextView textView1, textView2, textView3, textView4, textView5;
    private View colorBox1, colorBox2, colorBox3, colorBox4, colorBox5;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_colors, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInfoColorsViewModel = new ViewModelProvider(this).get(InfoColorsViewModel.class);
        mSharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        textView1 = view.findViewById(R.id.textView1);
        colorBox1 = view.findViewById(R.id.colorBox1);
        textView2 = view.findViewById(R.id.textView2);
        colorBox2 = view.findViewById(R.id.colorBox2);
        textView3 = view.findViewById(R.id.textView3);
        colorBox3 = view.findViewById(R.id.colorBox3);
        textView4 = view.findViewById(R.id.textView4);
        colorBox4 = view.findViewById(R.id.colorBox4);
        textView5 = view.findViewById(R.id.textView5);
        colorBox5 = view.findViewById(R.id.colorBox5);

        // Observe sharedViewModel to get color updates
        mSharedViewModel.getDominantColors().observe(getViewLifecycleOwner(), this::updateColors);

        // Observe local ViewModel to update UI
        mInfoColorsViewModel.getColor1().observe(getViewLifecycleOwner(), color -> colorBox1.setBackgroundColor(color));
        mInfoColorsViewModel.getColor2().observe(getViewLifecycleOwner(), color -> colorBox2.setBackgroundColor(color));
        mInfoColorsViewModel.getColor3().observe(getViewLifecycleOwner(), color -> colorBox3.setBackgroundColor(color));
        mInfoColorsViewModel.getColor4().observe(getViewLifecycleOwner(), color -> colorBox4.setBackgroundColor(color));
        mInfoColorsViewModel.getColor5().observe(getViewLifecycleOwner(), color -> colorBox5.setBackgroundColor(color));

        mInfoColorsViewModel.getText1().observe(getViewLifecycleOwner(), textView1::setText);
        mInfoColorsViewModel.getText2().observe(getViewLifecycleOwner(), textView2::setText);
        mInfoColorsViewModel.getText3().observe(getViewLifecycleOwner(), textView3::setText);
        mInfoColorsViewModel.getText4().observe(getViewLifecycleOwner(), textView4::setText);
        mInfoColorsViewModel.getText5().observe(getViewLifecycleOwner(), textView5::setText);
    }

    private void updateColors(List<RGBColor> colors) {
//        if (colors == null || colors.size() < 5) return;

        mInfoColorsViewModel.setColor1(colors.get(0).getColor());
        mInfoColorsViewModel.setColor2(colors.get(1).getColor());
        mInfoColorsViewModel.setColor3(colors.get(2).getColor());
        mInfoColorsViewModel.setColor4(colors.get(3).getColor());
        mInfoColorsViewModel.setColor5(colors.get(4).getColor());

        mInfoColorsViewModel.setText1(colors.get(0).RGBString());
        mInfoColorsViewModel.setText2(colors.get(1).RGBString());
        mInfoColorsViewModel.setText3(colors.get(2).RGBString());
        mInfoColorsViewModel.setText4(colors.get(3).RGBString());
        mInfoColorsViewModel.setText5(colors.get(4).RGBString());
    }
}