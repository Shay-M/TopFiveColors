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
import com.silverhorse.topfivecolors.model.ColorPercentage;
import com.silverhorse.topfivecolors.ui.shared.SharedViewModel;

import java.text.DecimalFormat;
import java.util.List;

public class InfoColorsFragment extends Fragment {

    public static InfoColorsFragment newInstance() {
        return new InfoColorsFragment();
    }

    private InfoColorsViewModel mInfoColorsViewModel;
    private SharedViewModel mSharedViewModel;

    private TextView textView1, textView2, textView3, textView4, textView5;
    private TextView colorBox1, colorBox2, colorBox3, colorBox4, colorBox5;

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

        textView1 = view.findViewById(R.id.rgbTextView1);
        colorBox1 = view.findViewById(R.id.colorTextView1);
        textView2 = view.findViewById(R.id.rgbTextView2);
        colorBox2 = view.findViewById(R.id.colorTextView2);
        textView3 = view.findViewById(R.id.rgbTextView3);
        colorBox3 = view.findViewById(R.id.colorTextView3);
        textView4 = view.findViewById(R.id.rgbTextView4);
        colorBox4 = view.findViewById(R.id.colorTextView4);
        textView5 = view.findViewById(R.id.rgbTextView5);
        colorBox5 = view.findViewById(R.id.colorTextView5);

        // Observe sharedViewModel to get color updates
        mSharedViewModel.getDominantColors().observe(getViewLifecycleOwner(), this::updateColors);

        // Observe local ViewModel to update UI
        mInfoColorsViewModel.getColor1().observe(getViewLifecycleOwner(), color -> colorBox1.setBackgroundColor(color));
        mInfoColorsViewModel.getColor2().observe(getViewLifecycleOwner(), color -> colorBox2.setBackgroundColor(color));
        mInfoColorsViewModel.getColor3().observe(getViewLifecycleOwner(), color -> colorBox3.setBackgroundColor(color));
        mInfoColorsViewModel.getColor4().observe(getViewLifecycleOwner(), color -> colorBox4.setBackgroundColor(color));
        mInfoColorsViewModel.getColor5().observe(getViewLifecycleOwner(), color -> colorBox5.setBackgroundColor(color));

        final DecimalFormat decimalFormat = new DecimalFormat("0.00");
        mInfoColorsViewModel.getPercentage1().observe(getViewLifecycleOwner(), percentage -> {
            String formattedPercentage = decimalFormat.format(percentage) + "%";

            colorBox1.setText(formattedPercentage);
        });

        mInfoColorsViewModel.getPercentage2().observe(getViewLifecycleOwner(), percentage -> {
            String formattedPercentage = decimalFormat.format(percentage) + "%";
            colorBox2.setText(formattedPercentage);
        });

        mInfoColorsViewModel.getPercentage3().observe(getViewLifecycleOwner(), percentage -> {
            String formattedPercentage = decimalFormat.format(percentage) + "%";
            colorBox3.setText(formattedPercentage);
        });

        mInfoColorsViewModel.getPercentage4().observe(getViewLifecycleOwner(), percentage -> {
            String formattedPercentage = decimalFormat.format(percentage) + "%";
            colorBox4.setText(formattedPercentage);
        });

        mInfoColorsViewModel.getPercentage5().observe(getViewLifecycleOwner(), percentage -> {
            String formattedPercentage = decimalFormat.format(percentage) + "%";
            colorBox5.setText(formattedPercentage);
        });

        mInfoColorsViewModel.getText1().observe(getViewLifecycleOwner(), textView1::setText);
        mInfoColorsViewModel.getText2().observe(getViewLifecycleOwner(), textView2::setText);
        mInfoColorsViewModel.getText3().observe(getViewLifecycleOwner(), textView3::setText);
        mInfoColorsViewModel.getText4().observe(getViewLifecycleOwner(), textView4::setText);
        mInfoColorsViewModel.getText5().observe(getViewLifecycleOwner(), textView5::setText);

    }

    private void updateColors(final List<ColorPercentage> colors) {
        mInfoColorsViewModel.setColor1(colors.get(0).color());
        mInfoColorsViewModel.setColor2(colors.get(1).color());
        mInfoColorsViewModel.setColor3(colors.get(2).color());
        mInfoColorsViewModel.setColor4(colors.get(3).color());
        mInfoColorsViewModel.setColor5(colors.get(4).color());

        mInfoColorsViewModel.setText1(RGBString(colors.get(0).color()));
        mInfoColorsViewModel.setText2(RGBString(colors.get(1).color()));
        mInfoColorsViewModel.setText3(RGBString(colors.get(2).color()));
        mInfoColorsViewModel.setText4(RGBString(colors.get(3).color()));
        mInfoColorsViewModel.setText5(RGBString(colors.get(4).color()));

        mInfoColorsViewModel.setPercentage1(colors.get(0).percentage());
        mInfoColorsViewModel.setPercentage2(colors.get(1).percentage());
        mInfoColorsViewModel.setPercentage3(colors.get(2).percentage());
        mInfoColorsViewModel.setPercentage4(colors.get(3).percentage());
        mInfoColorsViewModel.setPercentage5(colors.get(4).percentage());
    }

    public String RGBString(final int color) {
        return "R:" + ((color >> 16) & 0xFF) +
                " G:" + ((color >> 8) & 0xFF) +
                " B:" + (color & 0xFF);
    }
}
