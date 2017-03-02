# AndroidBasicLibs
#中文说明：[戳我](https://github.com/wwah/AndroidBasicLibs/blob/master/ZH_README.md)

A basic library about Android, including tools (network, pictures, check, IO, etc.), custom components.

Add it in your root build.gradle at the end of repositories:
 ```
  allprojects {
  	repositories {
		...
		maven { 
			url 'https://jitpack.io' 
		}
	}
}
   ```
  Add the dependency：
   ```
  dependencies {
         compile 'com.github.wwah.AndroidBasicLibs:basekit:0.3.3'
  }
   ```
     Add the AndroidManifes:
  ```

      <application
        >
        ....
              <meta-data
              android:name="design_width"
              android:value="1280" />
          <meta-data
              android:name="design_height"
              android:value="800" />
      </application>

  </manifest>

  ```
  build.gradle:

  ```
  build.gradle
   defaultConfig {
          minSdkVersion 19
          ....
          ｝
  ```
