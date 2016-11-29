package smart.in.sources.db;

import rx.Observable;
import smart.in.sources.entity.SourceUserEntity;

/**
 * In memory Interface for Sources
 *
 * @author ranjithsuda
 */

public interface SourcesMemInterface {

  /**
   * Method to be called when u want to add / remove source to profile
   *
   * @param entity -- entity
   * @return -- true , if added or else removed
   */
  boolean addOrRemoveSourceToProfile(SourceUserEntity entity);

  /**
   * Method to be called when u want to remove all sources from DB
   */
  void removeAllSources();

  /**
   * Method to be called , when u want to get all user sources from profile
   *
   * @return -- observable of user sources
   */
  Observable<SourceUserEntity> getUserSources();
}
