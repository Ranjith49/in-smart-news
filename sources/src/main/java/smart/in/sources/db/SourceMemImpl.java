package smart.in.sources.db;

import java.util.ArrayList;

import rx.Observable;
import smart.in.sources.entity.SourceUserEntity;

/**
 * Implementation of Sources in Memory , which in turn reflects in DB
 *
 * @author ranjithsuda
 */

public class SourceMemImpl implements SourcesMemInterface {

  private final static Object LOCK = new Object();
  private static SourcesMemInterface memInterface;
  private ArrayList<SourceUserEntity> entities;
  private SourcesDBInterface dbInterface;

  private SourceMemImpl() {
    // Nothing to do ..
  }

  public static SourcesMemInterface getInstance() {
    synchronized (LOCK) {
      if (memInterface == null) {
        synchronized (LOCK) {
          memInterface = new SourceMemImpl();
        }
      }
    }
    return memInterface;
  }

  @Override
  public void init() {
    dbInterface = SourcesDbImpl.getInstance();
    entities = dbInterface.getAllSources();
  }

  @Override
  public boolean addOrRemoveSourceToProfile(SourceUserEntity entity) {
    if (entities.contains(entity)) {
      entities.remove(entity);
      dbInterface.removeSource(entity.getId());
      return false; // Removed
    } else {
      entities.add(entity);
      dbInterface.addSource(entity);
      return true; // Added
    }
  }

  @Override
  public void removeAllSources() {
    entities.clear();
    dbInterface.deleteAllSources();
  }

  @Override
  public Observable<SourceUserEntity> getUserSources() {
    return Observable.from(entities);
  }

  @Override
  public boolean isSourceInProfile(SourceUserEntity entity) {
    return entities.contains(entity);
  }
}
