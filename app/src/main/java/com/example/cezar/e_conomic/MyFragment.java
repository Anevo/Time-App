package com.example.cezar.e_conomic;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * Created by Cezar on 30-Jan-18.
 */

public class MyFragment extends Fragment {
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_layout, container, false);

        TextView tv = (TextView) v.findViewById(R.id.textViewfrag);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static MyFragment newInstance(String text){
        MyFragment f = new MyFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }*/
}
