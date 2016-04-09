package com.example.sira.collegephonebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by sira on 7/4/16.
 */
public class Authenticated extends Activity {

    Spinner USER_DEPT, USER_YEAR;
    String user_dept, user_year;
    Button filterbutton;
    ListView filteredView;
    Context ctx = this;
    String name = "";
    String roll = "";
    String[] year = {"2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016"};
    String[] dept = {"chem","cse","ice","archi","meta"};
    int post = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authenticated_activity);

        USER_DEPT = (Spinner)findViewById(R.id.dept_spin);
        USER_YEAR = (Spinner)findViewById(R.id.Yspin);
        filteredView = (ListView)findViewById(R.id.filteredlistview);
        filterbutton = (Button)findViewById(R.id.filterbutton);

        USER_DEPT.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dept));
        USER_YEAR.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year));


        filterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // filter button clicked


                user_dept = USER_DEPT.getSelectedItem().toString();
                user_year = USER_YEAR.getSelectedItem().toString();

                // initialize the string name
                name = "";
                roll = "";


                Toast.makeText(Authenticated.this, user_dept, Toast.LENGTH_SHORT).show();
                Toast.makeText(Authenticated.this, user_year, Toast.LENGTH_SHORT).show();

                DatabaseOperations DB = new DatabaseOperations(ctx);

                Cursor CR = DB.getInformation(DB); // we got entire results to the cursor object now..we just have to iterate
                CR.moveToFirst(); //moves cursor to first location
                int i=0;
                while(CR.moveToNext())
                {

                    if(user_dept.equals(CR.getString(CR.getColumnIndex(TableData.TableInfo.USER_dept))) && user_year.equals(CR.getString(CR.getColumnIndex(TableData.TableInfo.USER_PASSOUT))))
                    {
                        Log.d("CR.getString(6) one", CR.getString(CR.getColumnIndex(TableData.TableInfo.USER_ID)));
                        Log.d("CR.getString(6) two", CR.getString(6));

                        name = name + CR.getString(0) + ":"; //get the student name
                        roll = roll + CR.getString(7) + ":"; // get the roll number
                        i++;
                    } //end of if


                } //end of while

                   final String[] name2 = name.split(":");
                    final String[] roll2 = roll.split(":");

                for(int k=0; k<name2.length; k++) { Log.d("content of array name2", name2[k]); }

                    String[] resultFalse = {"No Data Available on database"};

                if(name.equals("") == false) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, name2);
                    filteredView.setAdapter(adapter);
                    // When the item on listview gets selected, retrieve the roll number and pass roll number as bundle to next intent - Contact Intent

                        filteredView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                String value = (String) adapterView.getItemAtPosition(position);
                                for (int i = 0; i < name2.length; i++)
                                    if (value.equals(name2[i])) {
                                        post = i;
                                    }
                                String roll3 = roll2[post];
                                Log.d("roll of selected ",roll3); //Success till here

                                //Passing the roll number to bundle to activity





                                Intent i = new Intent(Authenticated.this, ActivityContact.class);
                                Toast.makeText(getBaseContext(),"Intent i created", Toast.LENGTH_SHORT).show();
                                i.putExtra("bundleroll", roll3);
                                Log.d("Activitycontact start", "");
                                startActivity(i);








                            } // end of onItemClick
                        });//end of setOnItemClickListener

                        } //end of if

                            else

                            {

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, resultFalse);
                                filteredView.setAdapter(adapter);
                            }// end of else


                        }
                });



    }







} // end of class Authenticated
