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
   * Initialize the data here ..
   */
  void init();

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

  /**
   * Method to check , whether the entity is part of the profile or not
   *
   * @param entity -- entity
   * @return -- whether it is in part of profile or not
   */
  boolean isSourceInProfile(SourceUserEntity entity);
}
