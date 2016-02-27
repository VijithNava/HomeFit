package com.vijithandroid.homefit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vijithnava on 2016-02-26.
 */
public class BurpeeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    static final String TITLE = "Burpee";

    public BurpeeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exercise_fragment, container, false);
        ImageView imageOne = (ImageView) rootView.findViewById(R.id.exercise_img_one);
        imageOne.setImageResource(R.drawable.burpee_1);

        ImageView imageTwo = (ImageView) rootView.findViewById(R.id.exercise_img_two);
        imageTwo.setImageResource(R.drawable.burpee_2);

        ImageView imageThree = (ImageView) rootView.findViewById(R.id.exercise_img_three);
        imageThree.setImageResource(R.drawable.burpee_3);
        imageThree.setVisibility(View.VISIBLE);

        TextView textView = (TextView) rootView.findViewById(R.id.exercise_description);
        textView.setText("1.\tBegin standing with your legs shoulder - width apart.\n\n" +
                "2.\tPlace your hands on the floor and kick your legs back so you end up with your stomach and thighs on the floor. Your elbows should be bent.\n\n" +
                "3.\tFrom this position, press up like you're doing a push-up and push your hips up.\n\n" +
                "4.\tJump your feet under your hips and stand.\n\n" +
                "5.\tFinish the movement by jumping in the air and bringing your hands over your head.\n\n" +
                "6.\tRepeat.");

        ImageView imageMusclesWorked = (ImageView) rootView.findViewById(R.id.exercise_muscle);
        imageMusclesWorked.setImageResource(R.drawable.burpee_main_muscle);

        Button launchButton = (Button) rootView.findViewById(R.id.launch_workout_video);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.bodybuilding.com/exercises/detail/view/name/burpee")));
            }
        });

        return rootView;
    }
}

