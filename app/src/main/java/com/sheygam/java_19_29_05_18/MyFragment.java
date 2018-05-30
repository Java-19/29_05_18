package com.sheygam.java_19_29_05_18;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class MyFragment extends Fragment {
    private String title = "Fragment";
    private CheckBox checkBox1, checkBox2;
    private boolean isFisrtChecked = false;
    private boolean isSecondChecked = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView view  = new TextView(getActivity());
//        view.setText(title);
//        view.setTextSize(20.0F);
//        view.setBackgroundColor(Color.rgb(0,255,0));
//        return view;
        return inflater.inflate(R.layout.my_fragment,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkBox1 = view.findViewById(R.id.checkBox);
        checkBox2 = view.findViewById(R.id.checkBox2);

//        final TextView textView = (TextView) view;
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                title = "new Text";
//                textView.setText(title);
//            }
//        },4000);
    }

    @Override
    public void onResume() {
        checkBox1.setChecked(isFisrtChecked);
        checkBox2.setChecked(isSecondChecked);
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        isFisrtChecked = checkBox1.isChecked();
        isSecondChecked = checkBox2.isChecked();
        super.onDestroyView();
    }
}
