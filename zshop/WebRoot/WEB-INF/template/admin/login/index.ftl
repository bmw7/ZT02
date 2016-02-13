<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
</head>
<body>
<div id="title">欢迎 <span style="color:#18b4ed">${loginName!"系统管理员"}</span> 登陆后台管理系统</div>
<div id="title_sm">这是您第 <span style="color:#18b4ed">${loginCount}</span> 次访问后台管理系统。上一次访问是在 <span style="color:#18b4ed">${loginDate?string("yyyy-MM-dd HH:mm:ss")}</span>，访问地址是 <span style="color:#18b4ed">北京</span></div>

<br>
<br>

<div class="col-md-12 admin-grid">
    <div class="panel sort-disable" id="p0">
        <div class="panel-heading"><span class="panel-title">网站概况</span></div>
        <div class="panel-body mnw700 of-a">
            <div class="row">

                <!-- 第一栏：总览 -->
                <div class="col-md-4 pln br-r mvn15">
                    <h5 class="ml5 mt20 ph10 pb5 br-b fw700">技术架构 <small class="pull-right fw600">Details</small> </h5>
                    <div id="high-column2" style="width: 100%; height: 255px; margin: 0 auto">&nbsp;&nbsp;&nbsp;&nbsp;Spring + Hibernate + SpringMVC + FreeMarker + Redis + Solr </div>
                </div>

                <!-- 第二栏：服务器信息/技术架构 -->
                <div class="col-md-4 br-r">
                    <h5 class="mt5 mbn ph10 pb5 br-b fw700">服务器信息 <small class="pull-right fw700 text-primary">Details </small> </h5>
                    <table class="table mbn tc-med-1 tc-bold-last tc-fs13-last">

                        <tbody>
                            <tr>
                                <td><i class="fa fa-circle text-warning fs8 pr15"></i><span>操作系统</span></td>
                                <td>CentOS 6.5</td>
                            </tr>
                            <tr>
                                <td><i class="fa fa-circle text-warning fs8 pr15"></i><span>HTTP服务器</span></td>
                                <td>Nginx1.6</td>
                            </tr>
                            <tr>
                                <td><i class="fa fa-circle text-warning fs8 pr15"></i><span>web服务器</span></td>
                                <td>tomcat7</td>
                            </tr>
            
                            <tr>
                                <td><i class="fa fa-circle text-warning fs8 pr15"></i><span>JAVA</span></td>
                                <td>JDK1.7</td>
                            </tr>
                            <tr>
                                <td><i class="fa fa-circle text-warning fs8 pr15"></i><span>数据库</span></td>
                                <td>MySQL5.5</td>
                            </tr>
                            <tr>
                                <td><i class="fa fa-circle text-warning fs8 pr15"></i><span>NoSQL</span></td>
                                <td>Redis2.8</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <!-- 第三栏：技术维护 -->
                <div class="col-md-4">
                    <h5 class="mt5 ph10 pb5 br-b fw700">技术支持/后期维护 <small class="pull-right fw700 text-primary">Details </small> </h5>
                    <table class="table mbn">
                        <thead>
                            <tr class="hidden">
                                <th>#</th>
                                <th>First Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="va-m fw600 text-muted"><i class="fa fa-circle text-warning fs8 pr15"></i>服务提供商</td>
                                <td class="fs15 fw700 text-right">沧海软件(北京)有限公司</td>
                            </tr>
                            <tr>
                                <td class="va-m fw600 text-muted"><i class="fa fa-circle text-warning fs8 pr15"></i>服务商网站</td>
                                <td class="fs15 fw700 text-right"><a href="http://www.webm.com.cn" target="_blank">www.webm.com.cn</a></td>
                            </tr>
                            <tr>
                                <td class="va-m fw600 text-muted"><i class="fa fa-circle text-warning fs8 pr15"></i>联系电话</td>
                                <td class="fs15 fw700 text-right">010-59790590</td>
                            </tr>
                            <tr>
                                <td class="va-m fw600 text-muted"><i class="fa fa-circle text-warning fs8 pr15"></i>联系邮件</td>
                                <td class="fs15 fw700 text-right">a@mavict.com</td>
                            </tr>

                            <tr>
                                <td class="va-m fw600 text-muted"><i class="fa fa-circle text-warning fs8 pr15"></i>联系手机</td>
                                <td class="fs15 fw700 text-right">17600801298</td>
                            </tr>
                            <tr>
                                <td class="va-m fw600 text-muted"><i class="fa fa-circle text-warning fs8 pr15"></i>联系QQ</td>
                                <td class="fs15 fw700 text-right">51556526</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>

