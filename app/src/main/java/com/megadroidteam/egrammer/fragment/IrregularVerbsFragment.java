package com.megadroidteam.egrammer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.megadroidteam.egrammer.R;
import com.megadroidteam.egrammer.activities.PhrasalVerbsActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class IrregularVerbsFragment extends Fragment {


    public IrregularVerbsFragment() {
        // Required empty public constructor
    }

    LinearLayout lin_pharal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_irregular_verbs, container, false);
        lin_pharal=view.findViewById(R.id.lin_pharal);
        lin_pharal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PhrasalVerbsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
