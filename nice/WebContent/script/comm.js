/**
 * opt.url
 * opt.data
 * opt.txtype query, add, update, delete
 * opt.success
 * opt.failure
 */

var comm = {
		showMessage:function(flag){
			if(flag){//成功
				$("#optArea").empty();
				$("#optArea").append("<div class='nice_success'><img src='/nice/script/succ.png' width='80px' height='60px'><span style='font-size:3rem;margin-left: 20px;font-weight: bold;'>交易成功</span></div>");
			}else{//失败
				$("#optArea").empty();
				$("#optArea").append("<div class='nice_success'><img src='/nice/script/fail.png' width='80px' height='60px'><span style='font-size:3rem;margin-left: 20px;font-weight: bold;'>交易失败</span></div>");
			}
		},
		LoadPage:function(domid, url, func){
			if(url){
				$("#"+domid).load(url, func||null);
			}else{
				console.log("url="+url);
			}
		},
		Submit:function(opt){
			if(opt){
				$.ajax({
					url:opt.url||"/nice/CommServlet",
					type:"POST",
					timeout:1000,
					dataType : 'json',
					data: {"txtype": opt.txtype, "jsonData": JSON.stringify(opt.data)},
					success:function(result,status,xhr){
						if(opt.success && typeof(opt.success) == "function"){
							var data = {
									BK_CODE:"00",
									BK_MSG:"交易成功",
									BK_DATA:result
							}
							opt.success(data);
						}else{
							console.log("no success function "+result);
						}
					},
					error:function(xhr,status,error){
						if(opt.failure && typeof(opt.failure) == "function"){
							var data = {
									BK_CODE:"01",
									BK_MSG:error,
							}
							opt.failure(data);
						}else{
							console.log("no failure function " + status + error);
						}
					},
					complete:function(xhr,status){
						console.log("complete="+status);
					}

				});
			}
			return;
		}	
};