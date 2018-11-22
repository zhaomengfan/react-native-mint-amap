require 'json'
package = JSON.parse(File.read(File.join(__dir__, 'package.json')))
Pod::Spec.new do |s|
  s.name         = "RCTAMapLocation"
  s.version      = package['version']
  s.summary      = "RN高德地图"
  s.description  = package['description']
  s.homepage     = package['homepage']
  s.license      = package['license']
  s.author       = package['author']
  
  s.source       = { :git => "https://github.com/zhaomengfan/react-native-mint-amap.git", :tag => s.version }
  s.platform     = :ios, "8.0"
  s.source_files  = "ios/RCTAMapLocation/*.{h,m}"
  s.dependency 'React'
  s.dependency 'AMapLocation'
end
