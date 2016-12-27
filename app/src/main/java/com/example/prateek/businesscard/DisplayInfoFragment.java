package com.example.prateek.businesscard;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prateek.businesscard.Database.DataContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class DisplayInfoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public DisplayInfoFragment() {
        setHasOptionsMenu(true);
    }

    public static final String DETAIL_URI = "DETAIL_URI";
    private static final String[] DETAILS_COLUMNS = {

            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.NAME,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.OCCUPATION,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.COMPANY,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.PHONE,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.WORK,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.HANGOUT,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.SKYPE,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.GOOGLE,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.FACEBOOK,
            DataContract.DataEntry.TABLE_NAME + "." + DataContract.DataEntry.BLOG
    };

    private static final int COL_NAME = 1;
    private static final int COL_OCCUPATION = 2;
    private static final int COL_COMPANY = 3;
    private static final int COL_PHONE = 4;
    private static final int COL_WORK = 5;
    private static final int COL_HANGOUT = 6;
    private static final int COL_SKYPE = 7;
    private static final int COL_GOOGLE = 8;
    private static final int COL_FACEBOOK = 9;
    private static final int COL_BLOG = 10;

    private static final int CURSOR_LOADER_ID = 0;

    private Uri mUri;
    private Cursor mCurosr;

    private TextView mName;
    private TextView mOccupation;
    private TextView mCompany;
    private TextView mPhone;
    private TextView mWork;
    private TextView mHang;
    private TextView mSkype;
    private TextView mGoogle;
    private TextView mFace;
    private TextView mBlog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_info, container, false);
        mName = (TextView) view.findViewById(R.id.name1);
        mOccupation = (TextView) view.findViewById(R.id.occ);
        mCompany = (TextView) view.findViewById(R.id.comp);
        mPhone = (TextView) view.findViewById(R.id.phone1);
        mWork = (TextView) view.findViewById(R.id.work1);
        mHang = (TextView) view.findViewById(R.id.hang);
        mSkype = (TextView) view.findViewById(R.id.skp);
        mGoogle = (TextView) view.findViewById(R.id.goog);
        mFace = (TextView) view.findViewById(R.id.faceb);
        mBlog = (TextView) view.findViewById(R.id.blog1);

        Bundle arguments = getArguments();
        if (arguments != null){
            mUri = arguments.getParcelable(DisplayInfoFragment.DETAIL_URI);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){

        if(null != mUri){
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    DETAILS_COLUMNS,
                    null,
                    null,
                    null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){

        if(!data.moveToFirst()){
            return;
        }

        String name = data.getString(COL_NAME);
        mName.setText(name);

        String occupation = data.getString(COL_OCCUPATION);
        mOccupation.setText(occupation);

        String company = data.getString(COL_COMPANY);
        mCompany.setText(company);

        String phone = data.getString(COL_PHONE);
        mPhone.setText(phone);

        String work = data.getString(COL_WORK);
        mWork.setText(work);

        String hang = data.getString(COL_HANGOUT);
        mHang.setText(hang);

        String skype = data.getString(COL_SKYPE);
        mSkype.setText(skype);

        String google =data.getString(COL_GOOGLE);
        mGoogle.setText(google);

        String facebook = data.getString(COL_FACEBOOK);
        mFace.setText(facebook);

        String blog = data.getString(COL_BLOG);
        mBlog.setText(blog);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){

    }


}
