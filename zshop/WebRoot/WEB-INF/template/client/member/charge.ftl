<!DOCTYPE html>
<html  lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>充值表单</title>
    <link rel="stylesheet" href="${base}/resource/client/css/bootstrap.min.css" />
    <style>
    	.row:first-child{ margin-top:100px;margin-bottom:30px; }
    </style>
</head>
<body>

<div class="container">
	
	<div class="row"><h1 class="text-center">充值表单</h1></div>
    
    <div class="col-md-offset-3 col-md-6">
    <form class="form-horizontal" action="${base}/member/chargeMoney" method="post">

        <div class="form-group">
            <label for="username" class="control-label col-md-4">充值数额</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="money" name="money" placeholder="请输入金额">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <button type="submit" class="btn btn-default"> 提 交  </button>
            </div>
        </div>

    </form>
	</div>

</div>

<!--[if lt IE 9]>
<script src="${base}/resource/client/js/html5shiv.min.js"></script>
<script src="${base}/resource/client/js/respond.min.js"></script>
<![endif]-->

<script src="${base}/resource/client/js/jquery.min.js"></script>
<script src="${base}/resource/client/js/bootstrap.min.js"></script>
</body>
</html>