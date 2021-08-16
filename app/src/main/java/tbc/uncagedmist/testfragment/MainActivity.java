package tbc.uncagedmist.testfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import tbc.uncagedmist.testfragment.Fragments.FirstFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        FirstFragment firstFragment = new FirstFragment();
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.mainLayout,firstFragment).commit();
    }
}