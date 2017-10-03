package samarthgupta.com.trysms;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String accNumber,debCred,amount,bal;
    DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(MainActivity.this);

        fetchInbox();

        //To get list of saved sql
//        databaseHandler.getAllInfo();
    }

    public void fetchInbox()
    {
        Uri uriSms = Uri.parse("content://sms/inbox");
        String s = "BNK";
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body", "person"},"address" + " LIKE ?", new String[] {"%" + s + "%"},null,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor != null) {
            while  (cursor.moveToNext())
            {
                String address = cursor.getString(1);
                String body = cursor.getString(3);
                long timestamp = cursor.getLong(2);

                String[] bodyWords = body.split(" ");
                int i =0;

                for (String word : bodyWords) {
                    if (word.startsWith("XXXX")) {

                        accNumber = word;
                        break;
                    }
                }

                for (String word : bodyWords) {
                    if (word.startsWith("debited")||word.startsWith("credited")||word.startsWith("DEB")||word.startsWith("CRED")) {

                        debCred = word;
                        break;
                    }
                }

                int flag =0;
                for(int t=0;t<bodyWords.length;t++){
                    if(flag!=1 && bodyWords[t].equals("Rs")){
                        amount = bodyWords[t+1];
                        flag=1;
                    }

                    else if(flag==1 && bodyWords[t].equals("Rs")){
                        bal = bodyWords[t+1];
                    }
                }


                SimpleDateFormat date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
                date.format(timestamp);


                String am = amount.substring(0,amount.length()-1).replace(",","");
                String ba = bal.replace(",","").substring(0,bal.length()-2);

                MessageInfo info = new MessageInfo( date.format(timestamp),address,
                        debCred,Float.parseFloat(am),
                        Float.parseFloat(ba),debCred,System.currentTimeMillis());

                System.out.println("Mobile number == "+address);
                System.out.println("SMS Text == "+body);
                System.out.println("Acc == "+ accNumber);
                System.out.println("debcred == "+ debCred);
                System.out.println("Time == "+date.format(timestamp));
                System.out.println("amount == "+ am);
                System.out.println("Bal ==  "+ ba);

                databaseHandler.addInformation(info);




////        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
//                tvDate.setText(dateFormat.format(date));

//                Cursor cursor = mDb.query(DATABASE_TABLE, new String[] {KEY_TITLE},
//                        KEY_TITLE + " LIKE ?", new String[] {"%" + s + "%"},
//                        null, null, null);

//                sms.add("Address=&gt; "+address+"n SMS =&gt; "+body);
            }
        }

        if (cursor != null) {
            cursor.close();
        }
    }



}
