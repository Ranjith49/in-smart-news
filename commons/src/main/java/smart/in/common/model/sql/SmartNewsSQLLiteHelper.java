package smart.in.common.model.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Smart News Application SQL-Lite Helper , holding information about the
 * a) DataBase of complete app
 * b) Tables [Create and accessors]
 *
 * @author ranjithsuda
 */

public class SmartNewsSQLLiteHelper extends SQLiteOpenHelper {

  public static final String DB_NAME = "smart.news.db";
  public static final int DB_VERSION = 1;

  // Table for the Source Entities ..
  public static final String TABLE_SOURCES = "sources";

  public static final String COL_SOURCE_ID = "source-id";
  public static final String COL_SOURCE_NAME = "source_name";
  public static final String COL_SOURCE_IMAGE_URL = "source_image_url";
  public static final String COL_SOURCE_ADD_TIME = "source_add_time";


  public SmartNewsSQLLiteHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  private static String createSourcesTable() {
    return "CREATE TABLE "
        + TABLE_SOURCES + " ("
        + COL_SOURCE_ID + " TEXT PRIMARY KEY,"
        + COL_SOURCE_NAME + " TEXT ,"
        + COL_SOURCE_IMAGE_URL + " TEXT,"
        + COL_SOURCE_ADD_TIME + " INTEGER )";
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(createSourcesTable());
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Nothing to do now ..
  }
}
