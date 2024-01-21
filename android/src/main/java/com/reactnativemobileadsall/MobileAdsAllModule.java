package com.reactnativemobileadsall;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.ads.*;

import android.util.Log;

public class MobileAdsAllModule extends ReactContextBaseJavaModule {

  ReactApplicationContext reactContext;
  InterstitialAd fbInterstitialAd;

  private void sendEvent(String eventName, WritableMap params) {
    reactContext
    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
    .emit(eventName, params);
  }

  MobileAdsAllModule(ReactApplicationContext context){
    super(context);
    reactContext = context;
  }

  @Override
  public String getName(){
    return "MobileAdsAllModule";
  }

  // Intertitial Ad listener.
  InterstitialAdListener fbInterstitialAdListener = new InterstitialAdListener() {
    @Override
    public void onInterstitialDisplayed(Ad ad) {
      WritableMap params = Arguments.createMap();
      params.putString("adStatus", "displayed");
      sendEvent("FB_INTERSTITIAL_AD", params);
    }

    @Override
    public void onInterstitialDismissed(Ad ad) {
      // Interstitial dismissed callback
      WritableMap params = Arguments.createMap();
      params.putString("adStatus", "dismissed");
      sendEvent("FB_INTERSTITIAL_AD", params);
    }

    @Override
    public void onError(Ad ad, AdError adError) {
      // Ad error callback
      WritableMap params = Arguments.createMap();
      params.putString("adStatus", "error");
      params.putString("message", adError.getErrorMessage());
      sendEvent("FB_INTERSTITIAL_AD", params);
    }

    @Override
    public void onAdLoaded(Ad ad) {
      // Interstitial ad is loaded and ready to be displayed
      WritableMap params = Arguments.createMap();
      params.putString("adStatus", "loaded");
      sendEvent("FB_INTERSTITIAL_AD", params);
    }

    @Override
    public void onAdClicked(Ad ad) {
      // Ad clicked callback
      WritableMap params = Arguments.createMap();
      params.putString("adStatus", "clicked");
      sendEvent("FB_INTERSTITIAL_AD", params);
    }

    @Override
    public void onLoggingImpression(Ad ad) {
      // Ad impression logged callback
      WritableMap params = Arguments.createMap();
      params.putString("adStatus", "impression logged");
      sendEvent("FB_INTERSTITIAL_AD", params);
    }
  };


  /*
   * First Intialise Facebook Ads
   */
  @ReactMethod
  public void initializeFacebookAds(Promise p){
    AudienceNetworkAds.initialize(reactContext.getApplicationContext()); 
  }

  // after intialisation user can call loadAd and monitor events.
  @ReactMethod
  public void loadFbInterstitialAd(String AdId, Promise p){
    fbInterstitialAd = new InterstitialAd(reactContext.getApplicationContext(), AdId);

    fbInterstitialAd.loadAd(
            fbInterstitialAd.buildLoadAdConfig()
                    .withAdListener(fbInterstitialAdListener)
                    .build());
  }

  @ReactMethod
  public void showFbInterstitialAd(Promise p){
    fbInterstitialAd.show();
  }
}
