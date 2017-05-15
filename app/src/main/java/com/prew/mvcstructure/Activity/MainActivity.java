package com.prew.mvcstructure.Activity;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.prew.mvcstructure.Fragment.MainFragment;
import com.prew.mvcstructure.Fragment.SecondFragment;
import com.prew.mvcstructure.R;
import com.prew.mvcstructure.Util.ScreenUtils;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screenWidth = ScreenUtils.getInstance().getScreenWidth();
        int screenHeight = ScreenUtils.getInstance().getScreenHeight();

        /*Toast.makeText(getApplicationContext(), "ScreenWidth = " + screenWidth, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "ScreenHeight = " + screenHeight, Toast.LENGTH_SHORT).show();*/

        if (Build.VERSION.SDK_INT >= 21) {
            //run on api 21+
        } else {
            //run on api below 20
        }

        if (savedInstanceState == null)
        {
            //Fragment
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance(123),"MainFragment")
                    .commit();

            //SecondFragment
            SecondFragment secondFragment = SecondFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,secondFragment,"SecondFragment")
                    .detach(secondFragment)
                    .commit();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (savedInstanceState == null)
        {
            MainFragment fragment = (MainFragment)getSupportFragmentManager().findFragmentByTag("MainFragment");
            fragment.setHelloText("Woo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\nWoo Hoooo\n");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_second_fragment:

                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentContainer);

                if (fragment instanceof SecondFragment == false)
                {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.from_right,R.anim.to_left,R.anim.from_left,R.anim.to_right)
                            .replace(R.id.contentContainer, SecondFragment.newInstance(),"SecondFragment")
                            .addToBackStack(null)
                            .commit();
                }
            Toast.makeText(getApplicationContext(),"Fragment Second",Toast.LENGTH_SHORT).show();
            return true;

            case R.id.action_first_tab:
                MainFragment mainFragment = (MainFragment)getSupportFragmentManager().findFragmentByTag("MainFragment");
                SecondFragment secondFragment = (SecondFragment)getSupportFragmentManager()
                        .findFragmentByTag("SecondFragment");
                getSupportFragmentManager().beginTransaction()
                        .attach(mainFragment)
                        .detach(secondFragment)
                        .commit();
                return true;

            case R.id.action_second_tab:
                MainFragment mainFragment1 = (MainFragment)getSupportFragmentManager().findFragmentByTag("MainFragment");
                SecondFragment secondFragment1 = (SecondFragment)getSupportFragmentManager().findFragmentByTag("SecondFragment");
                getSupportFragmentManager().beginTransaction()
                        .attach(secondFragment1)
                        .detach(mainFragment1)
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
