# AndroidBasicLibs

A basic library about Android, including tools (network, pictures, check, IO, etc.), custom components.

Add it in your root build.gradle at the end of repositories:

  allprojects {
  	repositories {
		...
		maven { 
			url 'https://jitpack.io' 
		}
	}
}
  
  Add the dependency：
  
  dependencies {
  	compile 'com.github.wwah:AndroidBasicLibs:0.2'
  }
