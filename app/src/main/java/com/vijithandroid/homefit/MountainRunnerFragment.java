package com.vijithandroid.homefit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vijithnava on 2016-02-26.
 */
public class MountainRunnerFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    static final String TITLE = "Mountain Runner";
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MountainRunnerFragment newInstance(int sectionNumber) {
        MountainRunnerFragment fragment = new MountainRunnerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MountainRunnerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mountainrunner_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        return rootView;
    }
}

