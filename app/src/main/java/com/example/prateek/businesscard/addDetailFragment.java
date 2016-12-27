package com.example.prateek.businesscard;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prateek.businesscard.Database.DataContract;
import com.example.prateek.businesscard.Database.DataDbHelper;

import java.util.Vector;

/**
 * A placeholder fragment containing a simple view.
 */
public class addDetailFragment extends Fragment {

    SQLiteDatabase database;
    DataDbHelper dbHelper;
    Context mContext;
    //Context upCotext;

    public addDetailFragment() {

    }

    EditText nameText;
    EditText occupation;
    EditText company;
    EditText phone;
    EditText work;
    EditText hangout;
    EditText google;
    EditText facebook;
    EditText skype;
    EditText blog;

    String name_data;
    String occ_data;
    String comp_data;
    String phon_data;
    String work_data;
    String hangout_data;
    String google_data;
    String skype_data;
    String facebook_data;
    String blog_data;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_detail, container, false);
        mContext = getActivity();
        dbHelper = new DataDbHelper(mContext);

        nameText = (EditText) view.findViewById(R.id.name);
        occupation = (EditText) view.findViewById(R.id.occupation);
        company = (EditText) view.findViewById(R.id.company);
        phone = (EditText) view.findViewById(R.id.phone);
        work = (EditText) view.findViewById(R.id.work);
        hangout = (EditText) view.findViewById(R.id.hangout);
        google = (EditText) view.findViewById(R.id.google);
        facebook = (EditText) view.findViewById(R.id.facebook);
        skype = (EditText) view.findViewById(R.id.skype);
        blog = (EditText) view.findViewById(R.id.blog);

        Button button = (Button) view.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_data = nameText.getText().toString();
                occ_data = occupation.getText().toString();
                comp_data = company.getText().toString();
                phon_data = phone.getText().toString();
                work_data = work.getText().toString();
                hangout_data = hangout.getText().toString();
                google_data = google.getText().toString();
                skype_data = skype.getText().toString();
                facebook_data = facebook.getText().toString();
                blog_data = blog.getText().toString();
                Log.d("DATA", name_data);
                try {

                    Vector<ContentValues> cVector = new Vector<ContentValues>();

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DataContract.DataEntry.NAME, name_data);
                    contentValues.put(DataContract.DataEntry.OCCUPATION, occ_data);
                    contentValues.put(DataContract.DataEntry.COMPANY, comp_data);
                    contentValues.put(DataContract.DataEntry.PHONE, phon_data);
                    contentValues.put(DataContract.DataEntry.WORK, work_data);
                    contentValues.put(DataContract.DataEntry.HANGOUT, hangout_data);
                    contentValues.put(DataContract.DataEntry.SKYPE, skype_data);
                    contentValues.put(DataContract.DataEntry.GOOGLE, google_data);
                    contentValues.put(DataContract.DataEntry.FACEBOOK, facebook_data);
                    contentValues.put(DataContract.DataEntry.BLOG, blog_data);

                    /*database.insert(DataContract.DataEntry.TABLE_NAME, null, contentValues);
                    database.close();*/

                    cVector.add(contentValues);
                    int inserted = 0;
                    if (cVector.size() > 0) {
                        ContentValues[] cvvArray = new ContentValues[cVector.size()];
                        cVector.toArray(cvvArray);
                        inserted = mContext.getContentResolver().bulkInsert(DataContract.DataEntry.CONTENT_URI, cvvArray);
                    }
                    Log.v("DATABASE", "VALUES INSERTED");
                }catch (Exception e){
                    e.printStackTrace();
                }

                Toast.makeText(mContext, "Data Saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                /*if(name_data != null) {
                    Intent intent = new Intent(getActivity(), DisplayInfo.class);
                    startActivity(intent);
                }*/
            }
        });
        return view;
    }

}
