package samarthgupta.com.trysms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samarthgupta on 04/10/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SmsInfo";
    private static final String TABLE_CONTACTS = "Info";
    private static final String KEY_BankName = "BankName";
    private static final String KEY_Date = "Date";
    private static final String KEY_Mode = "Mode";
    private static final String KEY_Acc = "AccNumber";
    private static final String KEY_Bal = "Balance";
    private static final String KEY_Amt = "Amount";
    private static final String KEY_ID = "ID";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_BankName + " TEXT," + KEY_Date + " TEXT,"
                + KEY_Mode + " TEXT," + KEY_Acc + " TEXT,"+ KEY_Bal +" FLOAT,"+KEY_Amt+" FLOAT,"+KEY_ID+" INTEGER PRIMARY KEY"+")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    void addInformation(MessageInfo mess) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BankName, mess.getBankName());
        values.put(KEY_Acc, mess.getAccNumber());
        values.put(KEY_Amt,mess.getAmount());
        values.put(KEY_Bal,mess.getBal());
        values.put(KEY_Date,mess.getDate());
        values.put(KEY_Mode,mess.getCreDeb());
        values.put(KEY_ID,mess.getId());


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get all contacts in a list view
    public List<MessageInfo> getAllInfo() {
        List<MessageInfo> messageList = new ArrayList<MessageInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setBankName(cursor.getString(0));
                messageInfo.setDate(cursor.getString(1));
                messageInfo.setCreDeb(cursor.getString(2));
                messageInfo.setAccNumber(cursor.getString(3));
                messageInfo.setBal(cursor.getFloat(4));
                messageInfo.setAmount(cursor.getFloat(5));
                messageList.add(messageInfo);


            } while (cursor.moveToNext());
        }

        // return contact list
        cursor.close();
        return messageList;
    }

}


