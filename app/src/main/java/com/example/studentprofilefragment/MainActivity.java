package com.example.studentprofilefragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener ,EditFragment.OnFragmentInteractionListener{

    EditText name;
    EditText lname;
    EditText studentId;
    Button save;
    RadioGroup rg;
    ImageView iv;
    Student student = new Student();
    boolean selecteddEP = false;

    int selectedImage= 0;

    // This is the sharedpreferences file that is used to save UserInfoDTO list.
    private final static String SHARED_PREFERENCES_FILE_USER_INFO_LIST = "userInfoList";


    private final static String SHARED_PREFERENCES_KEY_USER_INFO_LIST = "User_Info_List";

    // This is the debug log info tag which will be printed in the android monitor console.
    private final static String USER_INFO_LIST_TAG = "USER_INFO_LIST_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Profile");
        name= findViewById(R.id.editText);
        lname= findViewById(R.id.lastName);
        iv =findViewById(R.id.imageView);
        save= findViewById(R.id.save);
        rg = findViewById(R.id.radioGroup);
        studentId = findViewById(R.id.editText3);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setVisibility(View.INVISIBLE);
                lname.setVisibility(View.INVISIBLE);
                iv.setVisibility(View.INVISIBLE);
                save.setVisibility(View.INVISIBLE);
                rg.setVisibility(View.INVISIBLE);
                studentId.setVisibility(View.INVISIBLE);



                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,new BlankFragment())
                        .commit();


            }
        });



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton selectedRadioBtn = (RadioButton) group.findViewById(checkedId);
                selecteddEP = selectedRadioBtn.isChecked();
                student.setDepartment(String.valueOf(selectedRadioBtn.getText()));
            }
        });

       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               student.name = name.getText().toString();
               student.setlName(lname.getText().toString());
               student.setId(studentId.getText().toString());
               student.setImage(selectedImage);
               Context ctx = getApplicationContext();
               if (student.name.length() == 0 || student.lName.length() == 0) {
                   Toast.makeText(ctx, "Inavlid Name.", Toast.LENGTH_SHORT).show();

               } else if (student.id.equalsIgnoreCase("") || Integer.valueOf(student.id) < 100000000) {
                   Toast.makeText(ctx, "Inavlid Student ID.", Toast.LENGTH_SHORT).show();
               } else {


                   // Create Gson object.
                   Gson gson = new Gson();


                   String userInfoListJsonString = gson.toJson(student);

                   // Create SharedPreferences object.

                   SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFERENCES_FILE_USER_INFO_LIST, MODE_PRIVATE);

                   // Put the json format string to SharedPreferences object.
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString(SHARED_PREFERENCES_KEY_USER_INFO_LIST, userInfoListJsonString);
                   editor.commit();


                   name.setVisibility(View.INVISIBLE);
                   lname.setVisibility(View.INVISIBLE);
                   iv.setVisibility(View.INVISIBLE);
                   save.setVisibility(View.INVISIBLE);
                   rg.setVisibility(View.INVISIBLE);
                   studentId.setVisibility(View.INVISIBLE);
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.container,new EditFragment())
                           .commit();


                   //startActivity(intent);


               }
           }
       });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public Student sendInf(Student stu) {
        setTitle("My Profile");

        name.setVisibility(View.VISIBLE);
        lname.setVisibility(View.VISIBLE);
        iv.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
        rg.setVisibility(View.VISIBLE);
        studentId.setVisibility(View.VISIBLE);
        name.setText(stu.name);
        lname.setText(stu.lName);
        studentId.setText(stu.id);
        iv.setImageResource(stu.image);
        return stu;

    }


    @Override
    public int selectImageFragment(int i) {
        selectedImage =i;
        Log.d("image", Integer.toString(i));
        iv.setImageResource(i);
        name.setVisibility(View.VISIBLE);
        lname.setVisibility(View.VISIBLE);
        iv.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
        rg.setVisibility(View.VISIBLE);
        studentId.setVisibility(View.VISIBLE);
        return i;
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
