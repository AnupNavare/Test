package com.example.photonotes;

import java.util.ArrayList;
import java.util.List;

import com.example.photonotes.CapturedPhoto;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PhotoNotesDatabase";
	private static final String TABLE_NAME = "PhotoDetails";

	/* ........ Table Coulumn Names.............. */

	private static final String KEY_ID = "Id";
	private static final String KEY_CAPTION = "Caption";
	private static final String KEY_PHOTOPATH = "Path";

	private SQLiteDatabase database;

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String CREATE_PHOTO_DETAILS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_CAPTION
				+ " VARCHAR(100)," + KEY_PHOTOPATH + " VARCHAR(100)" + ");";
		db.execSQL(CREATE_PHOTO_DETAILS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE " + TABLE_NAME + "");
		onCreate(db);
	}

	public void open() throws SQLException {
		database = getWritableDatabase();
	}

	public void close() {
		super.close();
	}

	/* ...........Method to add CapturedPhoto object in database......... */
	public long addCapturedPhoto(CapturedPhoto capPhoto) {
		SQLiteDatabase db = getWritableDatabase();
		Log.d("in addCaputeredPhoto", "Yes...sucess");
		ContentValues values = new ContentValues();
		values.put(KEY_CAPTION, capPhoto.getCaption());
		values.put(KEY_PHOTOPATH, capPhoto.getPhotoPath());

		long id = db.insert(TABLE_NAME, null, values);
		Log.d("in addCaputeredPhoto", "Yes...sucess after inserting");
		db.close();
		return id;
	}

	/* .......Method to get CapturedPhoto object.......... */
	public CapturedPhoto getCapturedPhoto(long id) {
		SQLiteDatabase db = getReadableDatabase();
		CapturedPhoto photo = new CapturedPhoto();
		Log.d("id sent", "" + id);
		Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
				KEY_CAPTION, KEY_PHOTOPATH }, KEY_ID + "= ?",
				new String[] { String.valueOf(id) }, null, null, null);
		// Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "
		// + KEY_CAPTION + " = Y", null);
		Log.d("checking cursor", "before cursor null check");

		if (cursor != null && cursor.moveToFirst()) {
			Log.d("checking cursor", "cursor not null");
			int count = cursor.getCount();

			Log.d("check rows affected", "" + count);
			photo = new CapturedPhoto(Integer.parseInt(cursor.getString(0)),
					cursor.getString(1), cursor.getString(2));
			cursor.close();
			db.close();
			return photo;

		}
		return photo;
	}

	/* .......Method to get list of caption to show in list......... */
	public ArrayList<String> getAllPhotoCaption() {
		Log.d("In getPhotoCaption", "Yes...success");
		ArrayList<String> captionList = new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_CAPTION },
				null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			Log.d("If cursor is not null and has rows", "Yes...success");
			while (cursor.moveToNext()) {
				Log.d("If move to next", "Yes...success");
				Log.d("columnvalue", "" + cursor.getString(0));
				// CapturedPhoto photoCaption =
				cursorToCapturedPhotoCaption(cursor);
				// captionList.add(photoCaption);
				captionList.add(cursor.getString(0));
			}
		}
		cursor.close();
		return captionList;
	}

	/* ...........Method to show photo when caption given........... */
	public String getPhoto(String caption) {
		Log.d("In getPhotoCaption", "Yes...success");
		String photoPath;
		SQLiteDatabase db = getReadableDatabase();
		Log.d("in getPhoto", "true");
		Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_PHOTOPATH },
				KEY_CAPTION + " = ? ",
				new String[] { String.valueOf(caption) }, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			Log.d("cursor not null", "true");
			photoPath = cursor.getString(0);
			return photoPath;
		} else
			return null;

	}

	private CapturedPhoto cursorToCapturedPhotoCaption(Cursor c) {
		CapturedPhoto cp = new CapturedPhoto();
		cp.setCaption(c.getString(0));
		return cp;

	}

}
