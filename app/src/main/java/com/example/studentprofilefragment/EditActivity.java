package com.example.studentprofilefragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class EditActivity extends AppCompatActivity {
    EditText name;

    EditText id;
    EditText department;
    ImageView selected_image;
    Button edit;
    Student student= new Student();
    // This is the sharedpreferences file that is used to save UserInfoDTO list.
    private final static String SHARED_PREFERENCES_FILE_USER_INFO_LIST = "userInfoList";

    // This is the saved UserInfoDTO list jason string key in sharedpreferences file..
    private final static String SHARED_PREFERENCES_KEY_USER_INFO_LIST = "User_Info_List";

    // This is the debug log info tag which will be printed in the android monitor console.
    private final static String USER_INFO_LIST_TAG = "USER_INFO_LIST_TAG";

    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("Display My Profile");

        name= findViewById(R.id.name);
        edit= findViewById(R.id.edit);
        id= findViewById(R.id.id);
        department=findViewById(R.id.dep);
        selected_image = findViewById(R.id.selectAvatar);
        // Create SharedPreferences object.
        Context ctx = getApplicationContext();
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREFERENCES_FILE_USER_INFO_LIST, MODE_PRIVATE);

        // Get saved string data in it.
        String userInfoListJsonString = sharedPreferences.getString(SHARED_PREFERENCES_KEY_USER_INFO_LIST, "");

        // Create Gson object and translate the json string to related java object array.
        Gson gson = new Gson();
         student= gson.fromJson(userInfoListJsonString, Student.class);
        name.setText(student.getName() +" "+ student.getlName());
          id.setText(student.getId());
          department.setText(student.getDepartment());
        selected_image.setImageResource(student.getImage());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("AddedStudent",student);
                setResult(001,intent);


                finish();
            }
        });

    }
}
