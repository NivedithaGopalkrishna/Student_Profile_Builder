package com.example.studentprofilefragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    Student student= new Student();
    // This is the sharedpreferences file that is used to save UserInfoDTO list.
    private final static String SHARED_PREFERENCES_FILE_USER_INFO_LIST = "userInfoList";

    // This is the saved UserInfoDTO list jason string key in sharedpreferences file..
    private final static String SHARED_PREFERENCES_KEY_USER_INFO_LIST = "User_Info_List";

    // This is the debug log info tag which will be printed in the android monitor console.
    private final static String USER_INFO_LIST_TAG = "USER_INFO_LIST_TAG";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView stuName;
    private TextView stuId;
    private TextView stuDept;
    private ImageView stuAvatar;
    private TextView stunamelbl;
    private TextView stuIDlbl;
    private TextView stuDeptlbl;
    Button edit;





    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {



        Context ctx = getContext();
        super.onActivityCreated(savedInstanceState);
        stuName = getActivity().findViewById(R.id.textView4);
        stuId = getActivity().findViewById(R.id.textView5);
        stuDept = getActivity().findViewById(R.id.textView6);
        stuAvatar = getActivity().findViewById(R.id.imageView8);
        edit = getActivity().findViewById(R.id.button2);
        stunamelbl = getActivity().findViewById(R.id.textView3);
        stuIDlbl = getActivity().findViewById(R.id.textView2);
        stuDeptlbl = getActivity().findViewById(R.id.textView);

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFERENCES_FILE_USER_INFO_LIST, ctx.MODE_PRIVATE);

        // Get saved string data in it.
        String userInfoListJsonString = sharedPreferences.getString(SHARED_PREFERENCES_KEY_USER_INFO_LIST, "");

        // Create Gson object and translate the json string to related java object array.
        Gson gson = new Gson();
        student= gson.fromJson(userInfoListJsonString, Student.class);
        stuName.setText(student.getName() +" "+ student.getlName());
        stuId.setText(student.getId());
        stuDept.setText(student.getDepartment());
        stuAvatar.setImageResource(student.getImage());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.sendInf(student);
                getFragmentManager().beginTransaction().remove(EditFragment.this).commit();
            }
        });


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
        void onFragmentInteraction(Uri uri);
        Student sendInf(Student stu);
    }

}
