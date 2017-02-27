# AndroidBasicLibs

#简书说明：http://www.jianshu.com/p/06d417b554ef

- 一个android基础依赖库，包含了一下工具（网络，图片加载，检查,IO，ect），和自定位控件。
你需要添加到项目中的话只需要如下集成。

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
       compile 'com.github.wwah:AndroidBasicLibs:0.3.3'
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