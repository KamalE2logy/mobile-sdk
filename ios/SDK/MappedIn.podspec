Pod::Spec.new do |s|
  s.name = 'MappedIn'
  s.version = '0.3.1'
  s.summary = 'Integrate MappedIn directories, maps, and wayfinding in your app.'
  s.license = 'MIT'
  s.authors = {"Hassaan Aamir"=>"hassaan@mappedin.ca"}
  s.homepage = 'https://github.com/MappedIn/mappedin-sdk-ios'
  s.requires_arc = true
  s.source = {}

  s.platform = :ios, '7.0'
  s.ios.platform             = :ios, '7.0'
  s.ios.preserve_paths       = 'ios/MappedIn.framework'
  s.ios.public_header_files  = 'ios/MappedIn.framework/Versions/A/Headers/*.h'
  s.ios.resource             = 'ios/MappedIn.framework/Versions/A/Resources/**/*'
  s.ios.vendored_frameworks  = 'ios/MappedIn.framework'

  s.vendored_libraries = 'Proj4/libProj4.a'
  s.frameworks = 'CoreGraphics', 'CoreLocation', 'Foundation', 'QuartzCore', 'UIKit'
  s.libraries = 'Proj4', 'sqlite3', 'z'
end
