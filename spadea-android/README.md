# Android组开发规范、持续补充更新
一、目标

Android组开发规范用以指导团队成员，更高效，高质量的对APP进行开发，以保证开发出性能优异，稳定性佳，安全性高的产品

二、Android资源文件命名与使用（统一以小写单词加下划线方式命名）

1.资源文件统一携带模块名(module)前缀

2.layout文件的命名方式

Activity 的 layout 使用 module_activity_类名，如通用 使用 common_activity_业务描述
Fragment 的 layout 使用 module_fragment_类名，如通用 使用 common_fragment_业务描述
Dialog 的 layout 使用 module_dialog_类名/业务描述，如通用 使用 common_dialog_业务描述
include 的 layout 使用 module_include_类名/业务描述，如通用 使用 common_include_业务描述
RecyclerView 的 layout 使用 module_rv_item_类名/业务描述，如通用 使用 common_item_业务描述

3.图标资源统一存放在mipmap-xxxhdpi内，由系统自动缩放

png转webp,以减小安装包体积
非通用情况下，采用 module_业务功能描述_状态
通用情况下，采用 common_业务功能描述_状态

4.anim资源存放在drawable内

非通用情况下，采用 module_功能描述_状态
通用情况下，采用 common_功能描述_状态

5.color资源使用具体argb内容命名，放在通用 libcommon的colors.xml里面

无a部分，c_000000
有a部分，c_ff000000

6.暂不考虑dimens资源

7.style资源采用 ‘父style点当前style’命名，写入module_styles.xml文件中

<style name="ParentTheme.currentTheme"></style>

8.暂不考虑string资源

9.id资源，以控件View的缩写为前缀+功能命名

LinearLayout	ll
RelativeLayout	rl
ConstraintLayout	cl
FrameLayout	fl
ListView	recyclerView代替
RecyclerView	rv
ScrollView	sv
TextView	tv
Button	btn
ImageView	iv
CheckBox	cb
RadioButton	rb
EditText	et
ProgressBar	pb
DatePicker	dp

如：ll_功能描述

三、基本组件
1.打印Log
import com.orhanobut.logger.Logger;
Logger.d(TAG, "Some Android Debug info ...");

2.Toast
import com.hjq.toast
如：ToastUtils.show("下载失败请重试");

3.应用内跳转使用Arouter

四、UI与布局

1.布局中不得不使用 ViewGroup 多重嵌套时，不要使用 LinearLayout 嵌套，
改用 RelativeLayout，最好使用ConstraintLayout约束布局，可以有效降低嵌套数。


2.在 Activity 中显示对话框或弹出浮层时，尽量使用 DialogFragment，而非
Dialog/AlertDialog，这样便于随Activity生命周期管理对话框/弹出浮层的生命周期。

3.禁止在设计布局时多次为子 View 和父 View 设置同样背景进而造成页面过
度绘制，推荐将不需要显示的布局进行及时隐藏。

4.灵活使用布局，推荐 merge、ViewStub 来优化布局，尽可能多的减少 UI
布局层级，推荐使用 FrameLayout，LinearLayout、RelativeLayout 次之。


