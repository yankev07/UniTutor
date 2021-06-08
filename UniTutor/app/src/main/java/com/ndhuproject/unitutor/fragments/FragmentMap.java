package com.ndhuproject.unitutor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ndhuproject.unitutor.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kevin on 5/19/18.
 */

public class FragmentMap extends Fragment {

    private View view;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map,null);

        imageView = (ImageView)view.findViewById(R.id.imageView);

        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();
        return view;
    }

}
