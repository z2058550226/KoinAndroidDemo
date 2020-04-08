package com.suika.koindemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kotlin.Lazy;

import static org.koin.androidx.viewmodel.compat.ViewModelCompat.getViewModel;
import static org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel;

public class MainActivityJava extends AppCompatActivity {
    // lazy ViewModel
    private Lazy<MainViewModel> viewModelLazy = viewModel(this, MainViewModel.class);
    // directly get the ViewModel instance
    private MainViewModel viewModel = getViewModel(this, MainViewModel.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
