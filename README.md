# 信息通（V0.1.5)

2016-06-08 更新信息

1. ToolBar上面的菜单项增加“分享”和“编辑工作”两项；
2. 增加编辑工作操作界面，还未实现具体的业务逻辑；
3. 增加数据库操作类和工作信息记录类。

----------

2016-06-06 更新信息

1. 修正首页面点击ToolBar菜单无法弹出导航页面的BUG；
2. 重新编写了“天气信息”和“新闻信息”的页面（初步）；
3. 对系统所有页面进行了整理，将部分过去编写的页面放入到“old20160606”文件夹。


----------

2016-06-01 更新信息

1. 借鉴cheesesquare项目修改首页，增加了自动带标题的明细页面；
2. 集成了cheesesquare项目的相关信息。


----------

2016-04-27 更新信息

1. 增加新闻信息显示页面，调用百度API一次可以获取五条新闻，暂未实现多次获取和点击新闻标题查看内容功能，采用RecycirView控件实现；
2. 修改侧面菜单，增加新闻信息菜单，并添加点击后的方法。


----------


2016-04-26 更新信息

1. 利用AndroidStudio自动生成带侧边栏的应用框架；
2. 首页加入CardView测试内容；
3. 增加天气信息功能项，利用百度天气API和网络代码完成天气查看，目前不支持城市切换仅能够查看武汉天气；
4. 在天气信息中增加Google的Swip刷新控件实现下拉自动更新天气功能。