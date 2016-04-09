package com.example.sira.collegephonebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by sira on 8/4/16.
 */
public class ActivityContact extends Activity {

Context ctx = this;  String bundleroll = "";
    String name =""; String phone = "";  String fb = ""; String email=""; String year="";
    String dept =""; String roll="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        if(bundle!=null)
        { //bundle is not null
            bundleroll = bundle.getString("bundleroll");
            Toast.makeText(getBaseContext(),"bundle roll got is "+ bundleroll ,Toast.LENGTH_LONG).show();
        }


        TextView NameView = (TextView)findViewById(R.id.contactName);
        TextView deptt = (TextView)findViewById(R.id.DeptOutput);
        TextView yearr = (TextView)findViewById(R.id.YearOutput);
        TextView emaill = (TextView)findViewById(R.id.EmailOutput);
        Button fbbutton = (Button) findViewById(R.id.fbButton);
        Button callButton = (Button)findViewById(R.id.callButton);
        Button smsButton = (Button)findViewById(R.id.smsButton);




        DatabaseOperations DB = new DatabaseOperations(ctx);

        Cursor CR = DB.getInformation(DB); // we got entire results to the cursor object now..we just have to iterate
        CR.moveToFirst(); //moves cursor to first location

        while(CR.moveToNext())
        {

            if(bundleroll.equals(CR.getString(CR.getColumnIndex(TableData.TableInfo.USER_ROLL))) )
            {
                Log.d("CR.getString(6) one", CR.getString(CR.getColumnIndex(TableData.TableInfo.USER_ID)));
                Log.d("CR.getString(6) two", CR.getString(6));

                 name = CR.getString(0);  phone = CR.getString(2);
                 fb = CR.getString(3); email = CR.getString(4);
                year = CR.getString(6); dept = CR.getString(5);
                 roll = CR.getString(7);

                NameView.setText(name);
                deptt.setText("Department: " + dept);
                yearr.setText("Pass-out Year: " +year);
                emaill.setText("Email ID: " + email);

                //SEND AN SMS BUTTON

                smsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phone, null, "LOL You just lost Rs.1 as SmS charge - With Love - Siranjeevi D", null, null);
                        Toast.makeText(getBaseContext(),"SMS SENT",Toast.LENGTH_SHORT).show();
                            finish();
                                                            }
                                });

                //end of sms button

                // MAKE A CALL BUTTON

                callButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)));
                        finish();

                                                         }
                });

                //END OF CALL BUTTON

                //FB BUTTON - TAKE IT TO WEB VIEW --

                fbbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                Intent ii = new Intent(ActivityContact.this, ActivityWebView.class);
                        ii.putExtra("bundleurl",fb);
                        ii.putExtra("bundlename",name);
                        Log.d("Activity Web View start", "");

                        startActivity(ii);

                    }
                });




                break;

            } //end of if
            else {
                Toast.makeText(getBaseContext(),"Else statement executing",Toast.LENGTH_SHORT).show();
            }

        } //end of while




    }
}
