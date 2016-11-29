package smart.in.sources.db;

import java.util.ArrayList;

import smart.in.sources.entity.SourceUserEntity;

/**
 * Class Holding the Methods / accessors for the Sources DB
 *
 * @author ranjithsuda
 */

public interface SourcesDBInterface {

  /**
   * Method to be called , to add source into DB..
   *
   * @param sourceEntity - entity data.
   */
  void addSource(SourceUserEntity sourceEntity);

  /**
   * Method to be called , to remove source from DB ..
   *
   * @param sourceId -- source key
   */
  void removeSource(String sourceId);

  /**
   * Method to be called , to delete all sources from DB ..
   */
  void deleteAllSources();

  /**
   * Method to be called , to give list of sources in DB ..
   *
   * @return -- list in DB
   */
  ArrayList<SourceUserEntity> getAllSources();
}
