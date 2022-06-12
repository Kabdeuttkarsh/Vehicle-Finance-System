package com.solutionstouch.omsaifinance.ui.dashboard.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.solutionstouch.omsaifinance.LoginActivity;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    LocalStorage localStorage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
      //  final TextView textView = root.findViewById(R.id.text_share);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        localStorage = new LocalStorage(getContext());
        localStorage.logoutUser();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
        Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
      //  overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        return root;
    }

}
