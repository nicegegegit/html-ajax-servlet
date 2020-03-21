/**
 * 
 */

var indexJS = {
		init:function(){
			console.log("init ok")

			$("#info1").text("hello jquery!");

			var vm = new Vue({
				el: '#app',
				data: {
					message: 'Hello Vue!'
				}
			});

			var example1 = new Vue({
				el: '#example-1',
				data: {
					counter: 0
				}
			});

			var example2 = new Vue({
				el: '#example-2',
				data: {
					name: 'Vue.js'
				},
				// 在 `methods` 对象中定义方法
				methods: {
					greet: function (event) {
						// `this` 在方法里指当前 Vue 实例
						alert('Hello ' + this.name + '!');
						// `event` 是原生 DOM 事件
						alert(event.target.tagName);
					}
				}
			});
			
			$("#submit").click(function(){
				comm.LoadPage("div_info", "/nice/page/a.html");
			});
			
		}
};

/*
 * 启动入口
 */
$(function(){
	indexJS.init();
});
