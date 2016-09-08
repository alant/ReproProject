package repro.ads.com.carouseladsscrollbarrepro;

import android.app.Activity;
import android.view.*;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.search.*;

import android.content.*;
import android.content.res.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;

/**
 * Created by aarati on 9/7/16.
 */
public class TestAdActivity extends Activity {
	public static final String SINGLE_ROW_AD_HEIGHT = "150";
	public static final String ADS_THEME = "tuna";
	public static final String COLOR_PALETTE = "6f6f6f";
	public static String CAROUSEL_VIEW_CLIENT_ID = "XXX";
	private ViewGroup adsContainer;
	SearchAdView searchAdView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		adsContainer = (ViewGroup) findViewById(R.id.bottom_ads_container);
		showAds();
		//adsHelper.manageDisplayOfAds(bottomAdsContainer, "fish costume", AdDestinationType.ADS_ON_ITEM_DETAIL_PAGE, AdPlacementLocatiom.BOTTOM);
	}

	private void showAds() {
		searchAdView = new SearchAdView(this);
		searchAdView.setAdSize(AdSize.SEARCH);
		searchAdView.setAdUnitId(CAROUSEL_VIEW_CLIENT_ID);
		adsContainer.addView(searchAdView);
		searchAdView.loadAd(getFullWidthSearchAdRequest("fish costume", null));
	}

	private DynamicHeightSearchAdRequest getFullWidthSearchAdRequest(@NonNull String query, @Nullable String channel) {
		DynamicHeightSearchAdRequest.Builder addRequestBldr = new DynamicHeightSearchAdRequest.Builder();
		addRequestBldr.setAdTest(true);
		addRequestBldr.setAdvancedOptionValue("csa_adType", "plas");
		addRequestBldr.setQuery(query);
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float width = metrics.widthPixels;
		addRequestBldr.setAdvancedOptionValue("csa_height", SINGLE_ROW_AD_HEIGHT);
		addRequestBldr.setAdvancedOptionValue("csa_width", String.valueOf(convertPixelsToDp(width, getApplicationContext())));
		addRequestBldr.setAdvancedOptionValue("csa_theme", ADS_THEME);
		addRequestBldr.setAdvancedOptionValue("csa_textColorPalette", COLOR_PALETTE);
		if(channel != null) {
			addRequestBldr.setChannel(channel);
		}
		return addRequestBldr.build();
	}

	private static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
	}
}

