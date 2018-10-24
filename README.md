# LvmamaKitTest
利用图像识别技术解决非原生控件的定位问题

#### 缘起
前天同桌妹纸问一个控件怎么定位的问题，我张口就来，ID，xpath。。。。。妹纸说这些我都试过了，还是无法定位,不信我Debug给你看。N种方法尝试后，只能接受现实，非原生控件，于是老脸一红，告诉妹纸，这是非原生控件，无法通过常规方式定位。搜索了一下，很多帖子都反映过这个问题，但是都没给出解决策略。不知道这些小伙伴最终有没有解决。遂决定，吃个大闸蟹，研究一下看能否解决。

#### 非原生控件给自动化带来的痛点
 - View里控件都定位不到  
 - 控件随机变化（如支付密码键）
密码键盘每次页面打开或者页面刷新键位都不一样
![](https://github.com/512433465/LvmamaKitTest/tree/master/src/main/java/com/lvmama/cn/image/mima.png)

 - 常规定位方式无法识别
智能货架，经常变换的显示位置
![](/uploads/photo/2018/219fbfeb-6d8d-4c00-9345-bea785b58a9a.png!large)
 
#### 常规解决方式
 - 坐标大法：但是坐标大法对于有些随机变化密码键就无法操作了。
 - 开发配合：给特定的app包，解除安全键盘。这种操作无异于饮鸩止渴。

#### 图像识别尝试
由于我们页面控件不会像验证码那么多干扰难识别，现在图形识别技术Java，Python，C++等都非常成熟。一通百度后在OpenCV 发现了matchTemplate 方法，在图片中找指定的图片。主要用的以下两种方法：
 - 使用OpenCV函数 matchTemplate 在模板块和输入图像之间寻找匹配,获得匹配结果图像
 -  使用OpenCV函数 minMaxLoc 在给定的矩阵中寻找最大和最小值(包括它们的位置).

#### 具体实现

1. 截取图
  1. 滑动到非原生控件，使元素可见
  2. 截取截目画面即为原图像
  3. 用画图工具打开原图像（不要人为放大或者缩小原图像），截取非原生控件的图像即为模板图像
  4. 原图像： ![](/uploads/photo/2018/682b6abc-b31a-4546-b1c4-cd071be6c33d.png!large)
  5. 如下用画图工具打开，保证分别率不变下截取目标图像作为模板图像 ![](/uploads/photo/2018/6bbbac76-74f5-4574-aac7-f0f02bffbc1c.png!large)
  6.  模板图像（非原生控件图像）  ![](/uploads/photo/2018/667f744d-4468-475c-8e86-dbfc501b3768.png!large)


2. 取坐标
  1. 执行OpenCV函数 matchTemplate获取坐标
  2. 为方便公司同事和社区伸手党使用，直接上码 **[LvmamaKit代码工程Git传送门](https://github.com/512433465/LvmamaKitTest)**，调用LvmamaKit.find2BElementPoint4Debug会在原图像上标识出是否匹配成功
  3. 第二步成功后，调用LvmamaKit.find2BElementPoint返回Json格式坐标
  4.  回显示意图，忽略图片失真 ![](/uploads/photo/2018/a198f808-781b-49f1-91e5-3ab35f4ff11e.png!large)
  5.返回坐标，自行计算出中心坐标 {"x":"309","y":"854","width":"105","height":"82"} ![](/uploads/photo/2018/87245630-9a40-4614-9fbb-a3abf23097c1.png!large)

3. 点坐标
  1. 拷贝返回的坐标，进行相对计算
  2. 点击相对坐标，完成你的自动化用例

4. 识别密码
  1.密码键盘，1-9随机变，图像识别妥妥的搞定。 示意图 ![](/uploads/photo/2018/895b9e52-4530-476a-9261-b8e97b114f8c.png!large)

5. 搞定收工
  1. 明天可以给妹子解决非原生控件的定位问题咯

#### 参考资料
 -  **[中文OpenCV 官网教程](http://www.opencv.org.cn/opencvdoc/2.3.2/html/doc/tutorials/imgproc/histograms/template_matching/template_matching.html)**
 - **[图片部分匹配技术之 openCV 与 javaCV](https://testerhome.com/topics/1401)**
 - **[【解锁UI自动化新姿势】- Macaca+计算机视觉](https://testerhome.com/topics/8357)**
 - **[图像识别 OpenCV 解决安全键盘输入密码问题](https://testerhome.com/topics/9499)**


