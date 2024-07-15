package com.silverhorse.topfivecolors.ui.camera;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silverhorse.topfivecolors.R;
import com.silverhorse.topfivecolors.model.RGBColor;
import com.silverhorse.topfivecolors.ui.shared.SharedViewModel;
import com.silverhorse.topfivecolors.utils.CameraHelper;

import java.util.List;

public class CameraFragment extends Fragment {

    private CameraViewModel mViewModel;
    private CameraHelper mCameraHelper;
    private PreviewView previewView;
    private SharedViewModel mSharedViewModel;
//    private TextView colorsTextView;

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CameraViewModel.class);
        mSharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        previewView = view.findViewById(R.id.previewView);
        mCameraHelper = new CameraHelper(requireContext(), previewView, mViewModel);
        mViewModel.getDominantColors().observe(getViewLifecycleOwner(), this::displayColors);
        mCameraHelper.startCamera(this);
    }

    private void displayColors(List<RGBColor> colorModels) {
        mSharedViewModel.setDominantColors(colorModels);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCameraHelper.shutdown();
    }

    public CameraHelper getCameraHelper() {
        return mCameraHelper;
    }

}