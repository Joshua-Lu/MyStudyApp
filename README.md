# MyStudyApp
Android基础知识的学习与记录  
Github 地址：https://github.com/Joshua-Lu/MyStudyApp

## 主要内容
通过MainActivity启动各个Activity来测试各项功能
- TestDragActivity  
  DragView：自定义View，实现view自身跟随手指拖动效果
  ScrollerView：自定义ViewGroup，通过Scroller实现ViewGroup里面内容随手指拖动
  PlayIconView：自定义View，实现音乐播放时的音量柱效果
- TestViewDragHelperActivity  
  DragViewGroup：自定义ViewGroup，通过ViewDragHelper类，实现内部子View跟随手指拖动效果
- TestDrawActivity  
  TestDrawView：测试canvas的各个draw方法的使用
  AnalogClockView：通过canvas的各个draw方法，实现模拟时钟效果
- TestXmlDrawActivity  
  shape_test.xml：通过xml的shape标签，实现绘制各种图形
- TestImagePsActivity  
  ImageHelper：图片颜色处理及位置变换相关工具类
- TestPorterDuffModeActivity  
  PorterDuffModeSampleView：测试Paint的各个PorterDuffMode对应的效果
  TestPorterDuffModeView：通过使用PorterDuffMode，实现刮刮乐效果
- TestShaderActivity  
  TestShaderView：测试Paint的Shader属性，包括BitmapShader、LinearGradient、SweepGradient、RadialGradient、ComposeShader
- TestSurfaceViewActivity  
  SurfaceViewTemplate：SurfaceView使用模板
  TestSurfaceView：继承SurfaceViewTemplate，测试SurfaceView的使用方法，实现自动绘制正弦曲线，以及绘画板功能
- TestAnimationActivity  
  TestAnimationActivity：测试视图动画animation，包括透明度动画、平移动画、旋转动画、缩放动画、动画集合
- TestAnimatorActivity  
  TestAnimatorActivity：测试属性动画animator，包括透明度动画、平移动画、旋转动画、缩放动画、动画集合
- TestFloatViewActivity  
  FloatingButtonService：通过WindowManager实现悬浮窗效果  
- SystemInfoActivity  
  获取系统信息  
- TestAlarmManagerActivity  
  测试AlarmManager使用  
- TestCustomViewActivity   
  测试自定义View
- AidlClientActivity  
  测试进程间aidl通信，该activity为客户端，服务端APP的Github地址为：https://github.com/Joshua-Lu/AidlService
- TestClipChildrenActivity
  测试clipChildren和clipToPadding属性
  
   
## DesignPatternLib独立Module
 常用设计模式简单例子，具体内容见DesignPatternLib下的README.md

##  JavaCommonLib独立Module  

Java基础、算法练习代码，具体内容见JavaCommonLib下的README.md