As currently finding react native modules for different ad network is very big issue, this my effort to provide all ad network using a single react native module. If there are good react native module already available then I will list down here, so it can serve as single point for all ad networks.

---


**Currently Supported Ad Networks:**
| Ad Network | Supported Ad Types | Reference links | Test Ads/device | 
| ----------- | ----------- | ----------- | ----------- |
| Facebook Audience Network | Interstitial |  | Yes |

**Future Plans:**

- [ ] Unity Ads


#Facebook Audience Network

##Facebook Interstitial Ad Example :

Import the module.
```
import MobileAdsAllModule from 'react-native-mobile-ads-all';
```

Initialize Ads earliest in your app.
```
/* Initialize Facebook Ads */
MobileAdsAllModule.initializeFacebookAds();
```

Using the apis.
```
/* this hook take care for FACEBOOK ad call backs */
React.useEffect(async() => {

const fbAdsEventEmitter = new NativeEventEmitter();
const fbAdsEventListener = fbAdsEventEmitter.addListener(
  'FB_INTERSTITIAL_AD', 
  (event) => {
	console.log("=====> FB Ad Event")
	console.log(event)

	if(event.adStatus == "loaded"){
	  // set loaded true.
	  setAdLoaded(true);
	}

	if(event.adStatus == "displayed"){
	}

	if(event.adStatus == "impression logged"){
	}

	if(event.adStatus == "dismissed"){
	  // when dismissed load again
	  setAdLoaded(false)
	  MobileAdsAllModule.loadFbInterstitialAd("IMG_16_9_APP_INSTALL#1336564646926304_1336568296925939");
	}
});

MobileAdsAllModule.loadFbInterstitialAd("IMG_16_9_APP_INSTALL#1336564646926304_1336568296925939");

/* Unsubscribe from events on unmount */
return () => {
  fbAdsEventListener.remove();
};

}, []);

```