package smart.in.common.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import smart.in.common.R;
import smart.in.common.entity.BottomNavigationType;

/**
 * Application Bottom Navigation View representing the 4 sections of the Application
 *
 * @author ranjithsuda
 */
public class BottomNavigationView extends LinearLayout implements View.OnClickListener {

  private LinearLayout sourcesView;
  private LinearLayout headLinesView;
  private LinearLayout inboxView;
  private LinearLayout profileView;
  private TextView sourcesTextView;
  private TextView headLinesTextView;
  private TextView inboxTextView;
  private TextView profileTextView;

  private float selectedTextSize;
  private float defaultTextSize;

  public BottomNavigationView(Context context) {
    super(context);
    initView();
  }

  public BottomNavigationView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView();
  }

  public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView();
  }

  /**
   * Method to initialize the View ..
   */
  private void initView() {
    sourcesView = (LinearLayout) findViewById(R.id.bottom_nav_sources);
    headLinesView = (LinearLayout) findViewById(R.id.bottom_nav_headlines);
    inboxView = (LinearLayout) findViewById(R.id.bottom_nav_inbox);
    profileView = (LinearLayout) findViewById(R.id.bottom_nav_profile);
    sourcesTextView = (TextView) findViewById(R.id.bottom_nav_sourcesText);
    headLinesTextView = (TextView) findViewById(R.id.bottom_nav_headLinesText);
    inboxTextView = (TextView) findViewById(R.id.bottom_nav_inboxText);
    profileTextView = (TextView) findViewById(R.id.bottom_nav_profileText);

    //Adding on Click listeners to update the Current Section ..
    sourcesView.setOnClickListener(this);
    headLinesView.setOnClickListener(this);
    inboxView.setOnClickListener(this);
    profileView.setOnClickListener(this);

    selectedTextSize = getResources().getDimension(R.dimen.bottom_nav_view_text_sizeSelect);
    defaultTextSize = getResources().getDimension(R.dimen.bottom_nav_view_text_sizeDefault);

    //TODO Update Current Selection, When Preference is Implemented
    updateSectionSelectionStatus(BottomNavigationType.SOURCES);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.bottom_nav_headlines) {
      updateSectionSelectionStatus(BottomNavigationType.HEADLINES);
    }
    if (v.getId() == R.id.bottom_nav_sources) {
      updateSectionSelectionStatus(BottomNavigationType.SOURCES);
    }
    if (v.getId() == R.id.bottom_nav_inbox) {
      updateSectionSelectionStatus(BottomNavigationType.INBOX);
    }
    if (v.getId() == R.id.bottom_nav_profile) {
      updateSectionSelectionStatus(BottomNavigationType.PROFILE);
    }
  }

  /**
   * Methdd to update the bottom selection state [UI] + update current nav type to preference
   *
   * @param navType -- current Nav Type
   */
  private void updateSectionSelectionStatus(BottomNavigationType navType) {
    switch (navType) {
      case HEADLINES:
        headLinesTextView.setTextSize(selectedTextSize);
        headLinesView.setSelected(true);

        sourcesTextView.setTextSize(defaultTextSize);
        sourcesView.setSelected(false);
        profileTextView.setTextSize(defaultTextSize);
        profileTextView.setSelected(false);
        inboxTextView.setTextSize(defaultTextSize);
        inboxTextView.setSelected(false);
        break;
      case PROFILE:
        headLinesTextView.setTextSize(defaultTextSize);
        headLinesView.setSelected(false);
        sourcesTextView.setTextSize(defaultTextSize);
        sourcesView.setSelected(false);

        profileTextView.setTextSize(selectedTextSize);
        profileTextView.setSelected(true);

        inboxTextView.setTextSize(defaultTextSize);
        inboxTextView.setSelected(false);
        break;
      case INBOX:
        headLinesTextView.setTextSize(defaultTextSize);
        headLinesView.setSelected(false);
        sourcesTextView.setTextSize(defaultTextSize);
        sourcesView.setSelected(false);
        profileTextView.setTextSize(defaultTextSize);
        profileTextView.setSelected(false);

        inboxTextView.setTextSize(selectedTextSize);
        inboxTextView.setSelected(true);
        break;
      case SOURCES:
        headLinesTextView.setTextSize(defaultTextSize);
        headLinesView.setSelected(false);

        sourcesTextView.setTextSize(selectedTextSize);
        sourcesView.setSelected(true);

        profileTextView.setTextSize(defaultTextSize);
        profileTextView.setSelected(false);
        inboxTextView.setTextSize(defaultTextSize);
        inboxTextView.setSelected(false);
      default:
        break;
    }

    //TODO Update Current Selection, When Preference is Implemented
  }

}