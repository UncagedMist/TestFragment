package tbc.uncagedmist.testfragment.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tbc.uncagedmist.testfragment.Common.Common;
import tbc.uncagedmist.testfragment.R;

public class FirstFragment extends Fragment {

    Button btnContinue;
    EditText edtUrl;

    View myFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         myFragment = inflater.inflate(R.layout.fragment_first, container, false);


         btnContinue = myFragment.findViewById(R.id.btnContinue);
         edtUrl = myFragment.findViewById(R.id.edtUrl);

         onCLick();

         return myFragment;
    }

    private void onCLick() {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment secondFragment = new SecondFragment();
                Common.CURRENT_URL = edtUrl.getText().toString();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.mainLayout,secondFragment);
                transaction.commit();
            }
        });
    }
}