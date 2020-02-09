package com.example.studentprofilefragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BlankFragment extends Fragment {
   int selected =0;
    private OnFragmentInteractionListener mListener;
    ImageView avatar1 ,avatar2,avatar3,avatar4,avatar5,avatar6;




    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         avatar1 = getActivity().findViewById(R.id.imageView2);
         avatar2 = getActivity().findViewById(R.id.imageView3);
        avatar3 = getActivity().findViewById(R.id.imageView4);
        avatar4 = getActivity().findViewById(R.id.imageView5);
       avatar5 = getActivity().findViewById(R.id.imageView6);
       avatar6 = getActivity().findViewById(R.id.imageView7);


        avatar1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selected= mListener.selectImageFragment(R.drawable.avatar_f_1);
                Log.d("avatar" ,Integer.toString(selected));
              closefragment();

            }
        });

        avatar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.selectImageFragment(R.drawable.avatar_f_2);
               closefragment();

            }
        });

        avatar3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.selectImageFragment(R.drawable.avatar_f_3);
            closefragment();

            }
        });

        avatar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.selectImageFragment(R.drawable.avatar_m_1);
               closefragment();

            }
        });

        avatar5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.selectImageFragment(R.drawable.avatar_m_2);
             closefragment();

            }
        });

        avatar6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                mListener.selectImageFragment(R.drawable.avatar_m_3);
               closefragment();

            }
        });
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        int selectImageFragment(int i);
    }

    private void closefragment() {
        getFragmentManager().beginTransaction().remove(BlankFragment.this).commit();



    }
}
