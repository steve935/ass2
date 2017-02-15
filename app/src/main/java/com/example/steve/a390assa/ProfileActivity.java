package com.example.steve.a390assa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ProfileActivity extends AppCompatActivity {

    /**---------edittext      ----------**/
    protected EditText nameInput;
    protected EditText ageInput;
    protected EditText idInput;

    /**-------- storing edittext string variables------------**/
    protected String studentName = null;
    protected String studentId = null;
    protected String studentAge = null;

    /** variables used to validate name and age input **/
    protected String nameCheck = null;
    protected int    ageCheck = 0;


    public Button saveProfileButton;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        saveProfileButton = (Button)findViewById(R.id.saveButton);

        nameInput = (EditText)findViewById(R.id.name_editText);
        idInput = (EditText)findViewById(R.id.id_editText);
        ageInput = (EditText)findViewById(R.id.age_editText);


        sp = getSharedPreferences("Profile_Preference", MODE_PRIVATE);//important

        studentName = sp.getString("profile_name", null);
        studentId = sp.getString("profile_id",null);
        studentAge = sp.getString("profile_age",null);


    }

    /**menu creation**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_edit:
                itemEdit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        nameInput.setText(studentName);
        idInput.setText(studentId);
        ageInput.setText(studentAge);

        nameInput.setFocusable(false);
        ageInput.setFocusable(false);
        idInput.setFocusable(false);

        saveProfileButton.setVisibility(View.INVISIBLE);
        saveProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sp = getSharedPreferences("Profile_Preference", MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString("profile_name", nameInput.getText().toString());
                spEditor.putString("profile_id",idInput.getText().toString());
                spEditor.putString("profile_age",ageInput.getText().toString());
                spEditor.commit();

                nameCheck = nameInput.getText().toString();
                ageCheck = Integer.parseInt(ageInput.getText().toString());


                if(!nameCheck.matches(""))
                {
                    if (ageCheck>=18|| 99<= ageCheck)
                    {
                        nameInput.setFocusable(false);
                        idInput.setFocusable(false);
                        ageInput.setFocusable(false);
                        saveProfileButton.setVisibility(View.INVISIBLE); // makes save button invisible

                        Toast toast = Toast.makeText(getApplicationContext(),"Profile saved", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        Toast numError = Toast.makeText(getApplicationContext(),"wrong age", Toast.LENGTH_SHORT);
                        numError.show();
                    }

                }
                else {
                    Toast nameError = Toast.makeText(getApplicationContext(),"empty field", Toast.LENGTH_SHORT);
                    nameError.show();
                    //break;
                }
            }
        });

    }

    public void itemEdit() {

        nameInput.setFocusableInTouchMode(true);///set name editext to editable
        idInput.setFocusableInTouchMode(true);//set id editext to editable
        ageInput.setFocusableInTouchMode(true);//se age edittext to editable
        saveProfileButton.setVisibility(View.VISIBLE);// makes save button visible again

    }
}
