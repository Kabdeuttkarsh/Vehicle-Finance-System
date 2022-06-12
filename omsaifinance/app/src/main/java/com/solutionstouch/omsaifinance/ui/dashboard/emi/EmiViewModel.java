package com.solutionstouch.omsaifinance.ui.dashboard.emi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EmiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("EMI Calculator");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
