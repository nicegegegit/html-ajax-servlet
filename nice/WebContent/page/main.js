/**
 * 
 */

var mainJS={
	dataList:[],
	init:function(){
		var me = this;
		
		$("#query").click(function(){
			me.queryOpt();
		});
		
		$("#add").click(function(){
			me.addOpt();
		});
		
		$("#update").click(function(){
			me.updateOpt();
		});
		
		$("#delete").click(function(){
			me.deleteOpt();
		});
		
	},
	queryOpt:function(){//查询
		var opt = {
				txtype : "query",
				data : {},
				success : function(data) {
					console.log(JSON.stringify(data));
					mainJS.dataList=data.BK_DATA.DB_RESULT;
					$("#optArea").empty();
					$("#optArea").append("<table id='optTable' class='nice_table'></table>");
					$("#optTable").append("<tr><th></th><th>编号</th><th>名称</th><th>点击量</th><th>国家</th><th>URL</th></tr>")
					for(var i in data.BK_DATA.DB_RESULT){
						var id = data.BK_DATA.DB_RESULT[i].id;
						$("#optTable").append("<tr><td><input type='radio' name='radio_site' value='"+ id +"-" + i +"' id='radio_"+ id + "'/>"+
								"</td><td>"+ id + 
								"</td><td>"+data.BK_DATA.DB_RESULT[i].name + 
								"</td><td>" + data.BK_DATA.DB_RESULT[i].num +
								"</td><td>" + data.BK_DATA.DB_RESULT[i].country +
								"</td><td>" + data.BK_DATA.DB_RESULT[i].url +
						"</td></tr>");
					}
				},
				failure : function(data) {
					console.log(JSON.stringify(data));
				}
			}
		comm.Submit(opt);
	},
	addOpt:function(){//新增
		comm.LoadPage("optArea", "/nice/page/add.html", function(data,status, xhr){
			if(status == "success"){
				$("#submit").click(function(){
					var opt={
							txtype:"add",
							data:{
								name: $("#name").val(),
								num: $("#num").val(),
								country: $("#country").val(),
								url: $("#url").val()
							},
							success:function(data){
								console.log(JSON.stringify(data));
								comm.showMessage(true);
							},
							failure:function(data){
								console.log(JSON.stringify(data));
								comm.showMessage(false);
							}
					};
					comm.Submit(opt);
				});
			}
		});
	},
	updateOpt:function(){//修改
		var id = $(":checked").val();
		if(id== "" || id == undefined){
			alert("请先选择一条记录！")
			return;
		}
		comm.LoadPage("optArea", "/nice/page/update.html", function(data,status,xhr){
			if(status == "success"){
				var idValue = id.split('-')[0]
				var index = id.split('-')[1];
				var rowValue = mainJS.dataList[index];
				$("#id").val(rowValue.id);
				$("#name").val(rowValue.name);
				$("#num").val(rowValue.num);
				$("#country").val(rowValue.country);
				$("#url").val(rowValue.url);
				
				$("#submit").click(function(){//绑定提交事件
					var opt={
							txtype:"update",
							data:{
								id:idValue,
								name: $("#name").val(),
								num: $("#num").val(),
								country: $("#country").val(),
								url: $("#url").val()
							},
							success:function(data){
								console.log(JSON.stringify(data));
								comm.showMessage(true);
							},
							failure:function(data){
								console.log(JSON.stringify(data));
								comm.showMessage(false);
							}
					};
					comm.Submit(opt);
				});
			}
		});
	},
	deleteOpt:function(){//删除
		var id = $(":checked").val();
		if(id== "" || id == undefined){
			alert("请先选择一条记录！")
			return;
		}
		var opt = {
				txtype : "delete",
				data : {id: id.split('-')[0]},
				success : function(data) {
					comm.showMessage(true);
				},
				failure : function(data) {
					console.log(JSON.stringify(data));
					comm.showMessage(false);
				}
		};
		comm.Submit(opt);
	}
}

$(function(){
	mainJS.init();
});