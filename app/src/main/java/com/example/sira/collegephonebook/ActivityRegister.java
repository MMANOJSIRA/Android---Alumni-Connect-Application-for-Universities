package com.example.sira.collegephonebook;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by sira on 6/4/16.
 */
public class ActivityRegister extends Activity{

    EditText USER_NAME, USER_PASS, CON_PASS, USER_PHONE, USER_FB, USER_EMAIL, USER_ROLL;
    Button Reg;
    Spinner USER_DEPT, USER_YEAR;
    String user_name, user_pass, con_pass, user_fb, user_phone, user_email, user_dept, user_year, user_roll;
    Context ctx = this;

    String[] year = {"2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016"};
    String[] dept = {"chem","cse","ice","archi","meta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        USER_NAME = (EditText)findViewById(R.id.username);
        USER_PASS = (EditText)findViewById(R.id.pass);
        CON_PASS = (EditText)findViewById(R.id.conpass);
        USER_PHONE = (EditText)findViewById(R.id.Phone);
        USER_FB = (EditText)findViewById(R.id.fbreg);
        USER_EMAIL =(EditText)findViewById(R.id.R_email);
        USER_DEPT = (Spinner)findViewById(R.id.Dept_spinner);
        USER_YEAR = (Spinner)findViewById(R.id.Yspinner);
        USER_ROLL = (EditText)findViewById(R.id.RollOn);

        USER_DEPT.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dept));
        USER_YEAR.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,year));

        Reg = (Button)findViewById(R.id.reg);
            Reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user_name = USER_NAME.getText().toString();
                    user_pass = USER_PASS.getText().toString();
                    con_pass = CON_PASS.getText().toString();
                    user_phone = USER_PHONE.getText().toString();
                    user_fb = USER_FB.getText().toString();
                    user_email = USER_EMAIL.getText().toString();
                    user_dept = USER_DEPT.getSelectedItem().toString();
                    user_year = USER_YEAR.getSelectedItem().toString();
                    user_roll = USER_ROLL.getText().toString();

                    Log.d("user pass is: " + user_pass, "con pass is: " + con_pass);
                    if ((user_pass.equals(con_pass))) {
                        DatabaseOperations DB = new DatabaseOperations(ctx);

                        //check whether user name already exists


                        Cursor CR = DB.getInformation(DB); // we got entire results to the cursor object now..we just have to iterate
                        CR.moveToFirst(); //moves cursor to first location
                        boolean valid_user_name = true;


                        while(CR.moveToNext()) {
                            if(user_roll.equals(CR.getString(7)) )
                            {   Log.d("CR.getString(7)",CR.getString(7) );
                                valid_user_name = false;
                            }
                        }

                        if(valid_user_name == true) {



    if (!(user_name.equals("") || user_pass.equals("") || user_email.equals("") || user_roll.equals("") || user_phone.equals("")))
    {                       Toast.makeText(getBaseContext(), "RegStatus is true" + " Welcome "  + user_name  , Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), "ReginStatus is true" + " Welcome "   + user_year , Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), "ReginStatus is true" + " Welcome "   + user_dept  , Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), "ReginStatus is true" + " Welcome "  + user_email , Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), "ReginStatus is true" + " Welcome "   + user_phone  , Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), "ReginStatus is true" + " Welcome "  +  user_fb , Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), "ReginStatus is true" + " Welcome "  +  user_roll , Toast.LENGTH_SHORT).show();
                            //publish to database
                            DB.putInformation(DB, user_name, user_pass, user_phone, user_fb, user_email, user_dept, user_year, user_roll);

                            //after entry into database....
                            Toast.makeText(getBaseContext(), "Reg successful", Toast.LENGTH_SHORT).show();

                            /*


                            call another intent activity.



                            */
                            finish(); }// finishes activity and goes to calling activity.

                        } // end of valid users
                        else {
                            Toast.makeText(getBaseContext(), "LoginStatus is wrong -- check Details", Toast.LENGTH_SHORT).show();
                            USER_NAME.setText("");
                            USER_PASS.setText("");
                            CON_PASS.setText("");

                        }











                    } // end of if(user_pass) = (confirm pass)

                    else {
                        USER_PASS.setText("");
                        CON_PASS.setText("");
                        Toast.makeText(getBaseContext(), "Pass not matching", Toast.LENGTH_SHORT).show();

                    } // end of else statement

                } // end of onclick

            });

    }
}
