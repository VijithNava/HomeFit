package com.vijithandroid.homefit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

/**
 * Created by vijithnava on 2016-02-26.
 */
public class SquatFragment extends Fragment implements ObservableScrollViewCallbacks {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    static final String TITLE = "Squat";

    public SquatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exercise_fragment, container, false);

        ObservableScrollView sv = (ObservableScrollView) rootView.findViewById(R.id.scrollView);
        sv.setScrollViewCallbacks(this);

        ImageView imageOne = (ImageView) rootView.findViewById(R.id.exercise_img_one);
        imageOne.setImageResource(R.drawable.bodyweight_squat_1);

        ImageView imageTwo = (ImageView) rootView.findViewById(R.id.exercise_img_two);
        imageTwo.setImageResource(R.drawable.bodyweight_squat_2);

        TextView textView = (TextView) rootView.findViewById(R.id.exercise_description);
        textView.setText("1.\tStand with your feet shoulder width apart. You can place your hands behind your head. This will be your starting position.\n\n" +
                "2.\tBegin the movement by flexing your knees and hips, sitting back with your hips.\n\n" +
                "3.\tContinue down to full depth if you are able,and quickly reverse the motion until you return to the starting position. As you squat, keep your head and chest up and push your knees out.");

        ImageView imageMusclesWorked = (ImageView) rootView.findViewById(R.id.exercise_muscle);
        imageMusclesWorked.setImageResource(R.drawable.bodyweight_squat_main_muscle);

        Button launchButton = (Button) rootView.findViewById(R.id.launch_workout_video);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.bodybuilding.com/exercises/detail/view/name/bodyweight-squat")));
            }
        });

        return rootView;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll,
                                boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
}

