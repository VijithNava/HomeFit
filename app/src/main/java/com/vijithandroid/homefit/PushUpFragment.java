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
public class PushUpFragment extends Fragment implements ObservableScrollViewCallbacks {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    static final String TITLE = "Push Up";

    public PushUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exercise_fragment, container, false);

        ObservableScrollView sv = (ObservableScrollView) rootView.findViewById(R.id.scrollView);
        sv.setScrollViewCallbacks(this);

        ImageView imageOne = (ImageView) rootView.findViewById(R.id.exercise_img_one);
        imageOne.setImageResource(R.drawable.pushup_1);

        ImageView imageTwo = (ImageView) rootView.findViewById(R.id.exercise_img_two);
        imageTwo.setImageResource(R.drawable.pushup_2);

        TextView textView = (TextView) rootView.findViewById(R.id.exercise_description);
        textView.setText("1.\tLie on the floor face down and place your hands about 36 inches apart while holding your torso up at arms length.\n\n" +
                "2.\tNext, lower yourself downward until your chest almost touches the floor as you inhale.\n\n" +
                "3.\tNow breathe out and press your upper body back up to the starting position while squeezing your chest.\n\n" +
                "4.\tAfter a brief pause at the top contracted position, you can begin to lower yourself downward again for as many repetitions as needed.");

        ImageView imageMusclesWorked = (ImageView) rootView.findViewById(R.id.exercise_muscle);
        imageMusclesWorked.setImageResource(R.drawable.pushup_main_muscle);

        Button launchButton = (Button) rootView.findViewById(R.id.launch_workout_video);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.bodybuilding.com/exercises/detail/view/name/pushups")));
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

