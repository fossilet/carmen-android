# 卡门项目

帮助用户最大化利用信用卡的免息期

# 配置环境

## OS X

1. `brew install gradle`
1. 安装[Android SDK](http://developer.android.com/sdk/installing/index.html?pkg=tools)
1. 在根目录新建`local.properties`文件,添加`sdk.dir=/path/to/android/sdk`
1. 阅读Android SDK的README
1. 打开Android SDK,安装如下组件:
    1. Android SDK Tools
    1. Android SDK Platform-tools
    1. Android SDK Build-tools
    1. Android 6.0 (API 23)
    1. Android Support Repository
    1. Android Support Library
    1. Google Play Services
    1. Google Repository
1. `./gradlew`
1. 如果AVD出现[分辨率的错误](http://stackoverflow.com/q/33253381/547578),可以把如下模块从Android SDK移除:
    1. Android Wear ARM EABI v7a System Image
    2. Android Wear Intel x86 Atom System Image


## Gradle

1. 列出任务: `gradle tasks`
2. 安装app: `gradle installDebug`