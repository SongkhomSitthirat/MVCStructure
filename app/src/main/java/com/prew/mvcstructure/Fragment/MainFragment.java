package com.prew.mvcstructure.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prew.mvcstructure.R;

/**
 * Created by Prew on 10/25/2016.
 */

public class MainFragment extends Fragment {

    TextView tvHello;

    int someVar;

    int x,y,z;

    public static MainFragment newInstance(int someVar)
    {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("someVar",someVar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Read Argument
        someVar = getArguments().getInt("someVar");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        iniInstance(rootView);
        return rootView;
    }

    private void iniInstance(View rootView) {
        tvHello = (TextView)rootView.findViewById(R.id.tvHello);

    }

    public void setHelloText(String text)
    {
        tvHello.setText(text);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save state;
        outState.putInt("x",x);
        outState.putInt("y",y);
        outState.putInt("z",z);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null)
        {
            //Restore state
            savedInstanceState.getInt("x");
            savedInstanceState.getInt("y");
            savedInstanceState.getInt("z");
        }
    }
}
