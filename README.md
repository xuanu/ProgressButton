# ProgressButton
带进度的按钮，类似下载进度

1、很简单的一进度按钮，忘记原出处了，修改了支持Padding属性

2、 支持自定义下载进度的颜色

使用方法：

1、项目的build.gradle中添加 

allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
2、工程的build.gradle中添加 

dependencies {
	        compile 'com.github.xuanu:ProgressButton:0.0.1'
	}
	
	
3、支持自定义背景颜色属性：app:border_color

截图：
![image](https://github.com/xuanu/ProgressButton/raw/master/screenshots/Screenshot_20160827-225400.png)
![image](https://github.com/xuanu/ProgressButton/raw/master/screenshots/Screenshot_20160827-225404.png)

