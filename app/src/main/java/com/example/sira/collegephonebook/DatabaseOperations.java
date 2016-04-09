package com.example.sira.collegephonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sira on 6/4/16.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 12;
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME +" ("+
            TableData.TableInfo.USER_ID +" TEXT ,"
            + TableData.TableInfo.USER_PASS + " TEXT ,"
            + TableData.TableInfo.USER_PHONE + " TEXT ,"
            + TableData.TableInfo.USER_FB + " TEXT ,"
            + TableData.TableInfo.USER_EMAIL + " TEXT, "
            + TableData.TableInfo.USER_PASSOUT + " TEXT, "
            + TableData.TableInfo.USER_dept + " TEXT, "
            + TableData.TableInfo.USER_ROLL + " TEXT "+
            ");";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
            // database iscreated now
        Log.d("DB created", "success");

    } // end of constructor

    @Override
    public void onCreate(SQLiteDatabase sdb)
    {
    sdb.execSQL(CREATE_QUERY);
        //TABLE CREATED
        Log.d("Table created","success");
    } // end of oncreate

    public void putInformation(DatabaseOperations dop, String name,String pass, String phone, String fb, String email, String dept, String passout, String roll)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_ID,name);
        cv.put(TableData.TableInfo.USER_PASS,pass);
        cv.put(TableData.TableInfo.USER_PHONE,phone);
        cv.put(TableData.TableInfo.USER_FB ,fb);
        cv.put(TableData.TableInfo.USER_EMAIL,email);
        cv.put(TableData.TableInfo.USER_dept,dept);
        cv.put(TableData.TableInfo.USER_PASSOUT,passout);
        cv.put(TableData.TableInfo.USER_ROLL,roll);
        SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("DatabaseOperations", "one row inserted");

    } //end of put information


    public Cursor getInformation(DatabaseOperations dop)
    {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns ={TableData.TableInfo.USER_ID, TableData.TableInfo.USER_PASS, TableData.TableInfo.USER_PHONE, TableData.TableInfo.USER_FB , TableData.TableInfo.USER_EMAIL, TableData.TableInfo.USER_dept, TableData.TableInfo.USER_PASSOUT, TableData.TableInfo.USER_ROLL};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return CR;


    } // end of getInformation




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // If you need to add a column

        if (i1 > i) {
       //     db.execSQL("ALTER TABLE " + TableData.TableInfo.TABLE_NAME + " ADD COLUMN " + TableData.TableInfo.USER_PHONE + " TEXT DEFAULT 0");
         //db.execSQL("ALTER TABLE " + TableData.TableInfo.TABLE_NAME + " ADD COLUMN " + TableData.TableInfo.USER_FB  + " TEXT ");
           // db.execSQL("ALTER TABLE " + TableData.TableInfo.TABLE_NAME + " ADD COLUMN " + TableData.TableInfo.USER_EMAIL  + " TEXT ");
           // db.execSQL("ALTER TABLE " + TableData.TableInfo.TABLE_NAME + " ADD COLUMN " + TableData.TableInfo.USER_dept  + " TEXT ");
            //db.execSQL("ALTER TABLE " + TableData.TableInfo.TABLE_NAME + " ADD COLUMN " + TableData.TableInfo.USER_PASSOUT  + " TEXT ");
           //db.execSQL("DROP TABLE " + TableData.TableInfo.TABLE_NAME);
       db.execSQL(CREATE_QUERY);
          //  db.execSQL("DROP TABLE " + TableData.TableInfo.TABLE_NAME);

        }

    }// end of onUpgrade

}//End of DBOp class
