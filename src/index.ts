import { NativeModules } from 'react-native';

type MobileAdsAllType = {
  initializeFacebookAds() : Promise<boolean>;
  loadFbInterstitialAd(AdId : string) : Promise<boolean>;
  showFbInterstitialAd() : Promise<boolean>;
};

const { MobileAdsAllModule } = NativeModules;

export default MobileAdsAllModule as MobileAdsAllType;