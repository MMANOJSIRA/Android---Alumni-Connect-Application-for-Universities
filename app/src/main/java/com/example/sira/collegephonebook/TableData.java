package com.example.sira.collegephonebook;

import android.provider.BaseColumns;

/**
 * Created by sira on 6/4/16.
 */
public class TableData {

public TableData()
{
//constructor function

} // end of tabledata

public static abstract class TableInfo implements BaseColumns
{

    public static final String USER_ID = "user_id";
    public static final String USER_PASS = "user_pass";

    public static final String USER_PHONE = "user_phone";
    public static final String USER_FB = "user_fb";
    public static final String USER_EMAIL = "user_email";

    public static final String USER_PASSOUT = "user_passout";
    public static final String USER_dept = "user_dept";
    public static final String USER_ROLL = "user_roll";

    public static final String DATABASE_NAME = "user_info";
    public static final String TABLE_NAME = "reg_info";





}



} // end of class TableData

