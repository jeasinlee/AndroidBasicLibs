# AndroidBasicLibs

#简书说明：http://www.jianshu.com/p/06d417b554ef

AndroidBasicLibs是一套android应用层依赖开发框架。将业务层无关的依赖进行了拆分。对第三方依赖进行二次封装解耦操作。系统架构采用了MVP模式+RxJava。具有快速集成、支持扩展和可维护性的特点。包含了（网络，图片加载，检查,IO，ect）和自定义控件。


# 你需要添加到项目中的话只需要如下集成。

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
  
  添加 dependency：

```
  dependencies {
       compile 'com.github.wwah.AndroidBasicLibs:basekit:0.3.3'
  }
```
添加 AndroidManifes:
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