package smart.in.sources.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import smart.in.common.helper.Utils;
import smart.in.common.model.sql.SmartNewsSQLLiteHelper;
import smart.in.sources.entity.SourceUserEntity;

/**
 * Implementation of Sources DB Interface for all DB operations
 *
 * @author ranjithsuda
 */

public class SourcesDbImpl implements SourcesDBInterface {

  private final static Object LOCK = new Object();
  private static SmartNewsSQLLiteHelper sqlLiteHelper;
  private static SourcesDBInterface sourcesDBInterface;
  private SQLiteDatabase database;

  private SourcesDbImpl() {
    sqlLiteHelper = new SmartNewsSQLLiteHelper(Utils.getAppContext());
  }

  public static SourcesDBInterface getInstance() {
    synchronized (LOCK) {
      if (sourcesDBInterface == null) {
        synchronized (LOCK) {
          sourcesDBInterface = new SourcesDbImpl();
        }
      }
    }
    return sourcesDBInterface;
  }

  private void autoOpen() {
    if (database == null || !database.isOpen()) {
      database = sqlLiteHelper.getWritableDatabase();
    }
  }

  @Override
  public void addSource(SourceUserEntity sourceEntity) {
    autoOpen();

    ContentValues values = new ContentValues();
    values.put(SmartNewsSQLLiteHelper.COL_SOURCE_ID, sourceEntity.getId());
    values.put(SmartNewsSQLLiteHelper.COL_SOURCE_NAME, sourceEntity.getName());
    values.put(SmartNewsSQLLiteHelper.COL_SOURCE_IMAGE_URL, sourceEntity.getUrlImage());
    values.put(SmartNewsSQLLiteHelper.COL_SOURCE_ADD_TIME, sourceEntity.getAddTime());

    database.insertWithOnConflict(SmartNewsSQLLiteHelper.TABLE_SOURCES, null,
        values, SQLiteDatabase.CONFLICT_REPLACE);
  }

  @Override
  public void removeSource(String sourceId) {
    autoOpen();
    String selection = SmartNewsSQLLiteHelper.COL_SOURCE_ID + " = ?";
    String[] selectionArgs = {sourceId};
    database.delete(SmartNewsSQLLiteHelper.TABLE_SOURCES, selection, selectionArgs);

  }

  @Override
  public void deleteAllSources() {
    autoOpen();
    database.delete(SmartNewsSQLLiteHelper.TABLE_SOURCES, null, null);
  }

  @Override
  public ArrayList<SourceUserEntity> getAllSources() {
    autoOpen();
    ArrayList<SourceUserEntity> data = new ArrayList<>();
    String sortOder = SmartNewsSQLLiteHelper.COL_SOURCE_ADD_TIME + " DESC";
    Cursor c = database.query(SmartNewsSQLLiteHelper.TABLE_SOURCES,
        null,  // all columns
        null,  // all rows
        null,  // no selection criteria
        null,  // no rows groups
        null,  // no filtering of rows
        sortOder);

    if (c == null || !c.moveToFirst()) {
      return data;
    }

    do {
      String id = c.getString(c.getColumnIndex(SmartNewsSQLLiteHelper.COL_SOURCE_ID));
      String name = c.getString(c.getColumnIndex(SmartNewsSQLLiteHelper.COL_SOURCE_NAME));
      String imgUrl = c.getString(c.getColumnIndex(SmartNewsSQLLiteHelper.COL_SOURCE_IMAGE_URL));
      long date_add = c.getLong(c.getColumnIndex(SmartNewsSQLLiteHelper.COL_SOURCE_ADD_TIME));

      SourceUserEntity userEntity = new SourceUserEntity();
      userEntity.setId(id);
      userEntity.setName(name);
      userEntity.setUrlImage(imgUrl);
      userEntity.setAddTime(date_add);

      data.add(userEntity);
    } while (c.moveToNext());
    return data;
  }
}
