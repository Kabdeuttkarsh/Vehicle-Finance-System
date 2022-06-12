package com.solutionstouch.omsaifinance.ui.dashboard.documents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DocumentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DocumentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("My Documents");
    }

    public LiveData<String> getText() {
        return mText;
    }

}


