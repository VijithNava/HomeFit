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
public class MountainClimberFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    static final String TITLE = "Mountain Climber";

    public MountainClimberFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exercise_fragment, container, false);
        ImageView imageOne = (ImageView) rootView.findViewById(R.id.exercise_img_one);
        imageOne.setImageResource(R.drawable.mountain_climber_1);

        ImageView imageTwo = (ImageView) rootView.findViewById(R.id.exercise_img_two);
        imageTwo.setImageResource(R.drawable.mountain_climber_2);

        TextView textView = (TextView) rootView.findViewById(R.id.exercise_description);
        textView.setText("1.\tBegin in a pushup position, with your weight supported by your hands and toes. Flexing the knee and hip, bring one leg until the knee is approximately under the hip. This will be your starting position.\n\n" +
                "2.\tExplosively reverse the positions of your legs, extending the bent leg until the leg is straight and supported by the toe, and bringing the other foot up with the hip and knee flexed. Repeat in an alternating fashion for 20-30 seconds.");


        ImageView imageMusclesWorked = (ImageView) rootView.findViewById(R.id.exercise_muscle);
        imageMusclesWorked.setImageResource(R.drawable.mountain_climber_main_muscle);

        Button launchButton = (Button) rootView.findViewById(R.id.launch_workout_video);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.bodybuilding.com/exercises/detail/view/name/mountain-climbers")));
            }
        });

        return rootView;
    }
}

