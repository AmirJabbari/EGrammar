package com.megadroidteam.egrammer.Database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.megadroidteam.egrammer.model.Pharal;

public class DatabaseAssets extends SQLiteOpenHelper {
	String DB_PATH = null;
	private static String DB_NAME = "idioms.db";
	public static final String TABLE_Customer= "Tbl_Customer";
	public static final String TABLE_ACTION = "tbl_action";
	public static final String COLUMN_ID = "id";

	// TABLE_CONTENT

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	@SuppressLint("SdCardPath")
	public DatabaseAssets(Context context) {

		super(context, DB_NAME, null, 1);//1 is database  version
		this.myContext = context;
		// DB_PATH = "/data/data/" + context.getPackageName() + "/" +
		// "databases/";
		DB_PATH = "/data/data/" + myContext.getPackageName() + "/" + "databases/";
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS
							| SQLiteDatabase.CREATE_IF_NECESSARY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	public void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		// SQLiteDatabase.NO_LOCALIZED_COLLATORS
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY
						| SQLiteDatabase.NO_LOCALIZED_COLLATORS
						| SQLiteDatabase.CREATE_IF_NECESSARY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// return cursor
	public Cursor query(String table, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		return myDataBase.query(TABLE_ACTION, null, null, null, null, null,
				null);

	}


	public List<Pharal> getAllNotes() {
		List<Pharal> pharals = new ArrayList<>();

		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_Customer + " ORDER BY " +
				TABLE_Customer + " DESC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Pharal pharal = new Pharal();
				pharal.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
				pharal.setTitle(cursor.getString(cursor.getColumnIndex(TABLE_Customer)));
				//pharal.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

				pharals.add(pharal);
			} while (cursor.moveToNext());
		}

		// close db connection
		db.close();

		// return notes list
		return pharals;
	}

}
