package com.example.sira.collegephonebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sira on 6/4/16.
 */
public class ActivityLogin extends Activity {
EditText ROLL, PASSWORD;
    Button LoginButton;
String roll,password,email,phone,fb;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ROLL = (EditText)findViewById(R.id.RollLoginAct);
        PASSWORD = (EditText)findViewById(R.id.PasswordLoginAct);
        LoginButton = (Button)findViewById(R.id.Lbutton);


        LoginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view)
            {
                roll = ROLL.getText().toString();
                password = PASSWORD.getText().toString();
                DatabaseOperations dop = new DatabaseOperations(ctx);
                Cursor CR = dop.getInformation(dop); // we got entire results to the cursor object now..we just have to iterate
                CR.moveToFirst(); //moves cursor to first location
                boolean Loginstatus =false;
                String namefromdb = "";

                do {
                    if(roll.equals(CR.getString(7)) && (password.equals(CR.getString(1))))
                    {   Log.d("CR.getString(7)",CR.getString(7) ); //8th column is USER_ROLL
                        Log.d("CR.getString(1)",CR.getString(1)); // 2nd col is password
                        Loginstatus = true;
                        namefromdb = CR.getString(7);
                    }
                } while(CR.moveToNext());

                if(Loginstatus == true)
                {

                 //Successfully logged in
                    Toast.makeText(getBaseContext(), "LoginStatus is true" + "Welcome" + namefromdb, Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(ActivityLogin.this, Authenticated.class);
                                startActivity(i);
                }

                else Toast.makeText(getBaseContext(),"LoginStatus is wrong",Toast.LENGTH_SHORT).show();

            }
        });







    }



}//end of class ActivityLogin
