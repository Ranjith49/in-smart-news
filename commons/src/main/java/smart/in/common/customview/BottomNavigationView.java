package smart.in.common.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import smart.in.common.R;
import smart.in.common.entity.AppSectionType;
import smart.in.common.helper.CommonNavigator;
import smart.in.common.helper.pref.GenericPreferenceType;
import smart.in.common.helper.pref.PreferenceManager;

/**
 * Application Bottom Navigation View representing the 4 sections of the Application
 *
 * @author ranjithsuda
 */
public class BottomNavigationView extends LinearLayout implements View.OnClickListener {

  private Context context;

  private LinearLayout sourcesView;
  private LinearLayout headLinesView;
  private LinearLayout inboxView;
  private LinearLayout profileView;
  private TextView sourcesTextView;
  private TextView headLinesTextView;
  private TextView inboxTextView;
  private TextView profileTextView;
  private ImageView headLinesImage;
  private ImageView sourcesImage;
  private ImageView profileImage;
  private ImageView inBoxImage;


  private int selectedTextSize;
  private int defaultTextSize;

  public BottomNavigationView(Context context) {
    super(context);
    this.context = context;
    initView();
  }

  public BottomNavigationView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    initView();
  }

  public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
    initView();
  }

  /**
   * Method to initialize the View ..
   */
  private void initView() {
    View rootView = LayoutInflater.from(context).inflate(R.layout.customview_bottom_nav_view, this, true);

    sourcesView = (LinearLayout) rootView.findViewById(R.id.bottom_nav_sources);
    headLinesView = (LinearLayout) rootView.findViewById(R.id.bottom_nav_headlines);
    inboxView = (LinearLayout) rootView.findViewById(R.id.bottom_nav_inbox);
    profileView = (LinearLayout) rootView.findViewById(R.id.bottom_nav_profile);
    sourcesTextView = (TextView) rootView.findViewById(R.id.bottom_nav_sourcesText);
    headLinesTextView = (TextView) rootView.findViewById(R.id.bottom_nav_headLinesText);
    inboxTextView = (TextView) rootView.findViewById(R.id.bottom_nav_inboxText);
    profileTextView = (TextView) rootView.findViewById(R.id.bottom_nav_profileText);

    headLinesImage = (ImageView) rootView.findViewById(R.id.bottom_nav_headlines_image);
    inBoxImage = (ImageView) rootView.findViewById(R.id.bottom_nav_inbox_image);
    sourcesImage = (ImageView) rootView.findViewById(R.id.bottom_nav_source_image);
    profileImage = (ImageView) rootView.findViewById(R.id.bottom_nav_profile_image);

    //Adding on Click listeners to update the Current Section ..
    sourcesView.setOnClickListener(this);
    headLinesView.setOnClickListener(this);
    inboxView.setOnClickListener(this);
    profileView.setOnClickListener(this);

    selectedTextSize = context.getResources().getDimensionPixelSize(R.dimen.bottombar_bottom_textSelected);
    defaultTextSize = context.getResources().getDimensionPixelSize(R.dimen.bottombar_bottom_textNotSelected);

    //Update View with current Selection status
    updateSectionSelectionStatus();
  }

  @Override
  public void onClick(View v) {
    String section = PreferenceManager.getPreference(GenericPreferenceType.APP_SECTION,
        AppSectionType.getStringValue(AppSectionType.SOURCES));
    AppSectionType curSection = AppSectionType.getSection(section);

    if (v.getId() == R.id.bottom_nav_headlines && curSection != AppSectionType.HEADLINES) {
      CommonNavigator.goToSection(AppSectionType.HEADLINES);
      PreferenceManager.savePreference(GenericPreferenceType.APP_SECTION,
          AppSectionType.getStringValue(AppSectionType.HEADLINES));
      updateSectionSelectionStatus();

      // Finishing the current activity holding this ..
      ((Activity) context).finish();
    }
    if (v.getId() == R.id.bottom_nav_sources && curSection != AppSectionType.SOURCES) {
      CommonNavigator.goToSection(AppSectionType.SOURCES);
      PreferenceManager.savePreference(GenericPreferenceType.APP_SECTION,
          AppSectionType.getStringValue(AppSectionType.SOURCES));
      updateSectionSelectionStatus();

      // Finishing the current activity holding this ..
      ((Activity) context).finish();
    }
    if (v.getId() == R.id.bottom_nav_inbox && curSection != AppSectionType.INBOX) {
      CommonNavigator.goToSection(AppSectionType.INBOX);
      PreferenceManager.savePreference(GenericPreferenceType.APP_SECTION,
          AppSectionType.getStringValue(AppSectionType.INBOX));
      updateSectionSelectionStatus();

      // Finishing the current activity holding this ..
      ((Activity) context).finish();
    }
    if (v.getId() == R.id.bottom_nav_profile && curSection != AppSectionType.PROFILE) {
      CommonNavigator.goToSection(AppSectionType.PROFILE);
      PreferenceManager.savePreference(GenericPreferenceType.APP_SECTION,
          AppSectionType.getStringValue(AppSectionType.PROFILE));
      updateSectionSelectionStatus();

      // Finishing the current activity holding this ..
      ((Activity) context).finish();
    }
  }

  /**
   * Methdd to update the bottom selection state [UI] + update current nav type to preference
   */
  private void updateSectionSelectionStatus() {
    String section = PreferenceManager.getPreference(GenericPreferenceType.APP_SECTION,
        AppSectionType.getStringValue(AppSectionType.SOURCES));
    AppSectionType navType = AppSectionType.getSection(section);

    switch (navType) {
      case HEADLINES:
        headLinesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTextSize);
        headLinesImage.setSelected(true);
        headLinesTextView.setSelected(true);

        sourcesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        sourcesView.setSelected(false);
        profileTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        profileTextView.setSelected(false);
        inboxTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        inboxTextView.setSelected(false);
        break;
      case PROFILE:
        profileTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTextSize);
        profileImage.setSelected(true);
        profileTextView.setSelected(true);

        headLinesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        headLinesView.setSelected(false);
        sourcesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        sourcesView.setSelected(false);
        inboxTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        inboxTextView.setSelected(false);
        break;
      case INBOX:
        inboxTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTextSize);
        inBoxImage.setSelected(true);
        inboxTextView.setSelected(true);

        headLinesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        headLinesView.setSelected(false);
        sourcesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        sourcesView.setSelected(false);
        profileTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        profileTextView.setSelected(false);
        break;
      case SOURCES:
      default:
        sourcesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedTextSize);
        sourcesImage.setSelected(true);
        sourcesTextView.setSelected(true);

        headLinesTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        headLinesView.setSelected(false);
        profileTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        profileTextView.setSelected(false);
        inboxTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        inboxTextView.setSelected(false);
        break;
    }
  }
}