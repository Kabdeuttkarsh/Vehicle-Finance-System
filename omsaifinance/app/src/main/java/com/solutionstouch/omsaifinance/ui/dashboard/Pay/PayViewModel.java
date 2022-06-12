package com.solutionstouch.omsaifinance.ui.dashboard.Pay;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
