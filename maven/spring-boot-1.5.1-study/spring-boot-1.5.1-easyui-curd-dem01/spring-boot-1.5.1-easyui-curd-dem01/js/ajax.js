var ajax = {
	version: "1.0",
	createXMLHttpRequest: function() {
		var xmlHttp;
		// 适用于大多数浏览器，以及IE7和IE更高版本
		try {
			xmlHttp = new XMLHttpRequest();
		} catch(e) {
			// 适用于IE6
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				// 适用于IE5.5，以及IE更早版本
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch(e) {}
			}
		}
		return xmlHttp;
	},

	/**
	 * Get请求
	 * @param {Object} url
	 * @param {Object} funSucc
	 * @param {Object} fnFaild
	 */
	get: function(url, successed, failed, async) { //1、以函数作为的参数传进来
		var xhr = this.createXMLHttpRequest();
		url = this.getRootPath() + "/" + url;
		xhr.open('GET', url, async);
		xhr.send(null);
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4) {
				if(xhr.status == 200) {
					successed(xhr.responseText) //2、使用传进来的函数
				} else {
					if(failed) {
						failed(xhr.statusText)
					}
				}
			}
		}
	},
	/**
	 * 执行post请求
	 * @param {Object} url
	 * @param {Object} params
	 * @param {Object} successed
	 * @param {Object} failed
	 */
	post: function(url, params, successed, failed, async) {
		if(!async) {
			async = true;
		}
		var xhr = this.createXMLHttpRequest();

		xhr.open("POST", url, async);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send(params);
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4) {
				if(xhr.status == 200) {
					successed(xhr.responseText) //2、使用传进来的函数
				} else {
					if(failed) {
						failed(xhr.responseText);
					}
				}
			}
		}
	},
	/**
	 * 返回
	 * http://servername:port/projectname
	 */
	getRootPath: function() {
		//获取当前网址，如： http://localhost:8083/xxx/xxx/xx.jsp
		var href = window.document.location.href;
		//获取主机地址之后的目录，如： xxx/xxx/xx.jsp
		var pathName = window.document.location.pathname;
		var pos = href.indexOf(pathName);
		//获取主机地址，如： http://localhost:8083
		var serverNameAndPort = href.substring(0, pos);
		//获取带"/"的项目名，如：/xxx
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		var rootPath = serverNameAndPort + projectName;
		return rootPath;
	},

	/**
	 *返回工程名 
	 */
	getRootName: function() {
		//获取主机地址之后的目录，如： xxx/xxx/xx.jsp
		var pathName = window.document.location.pathname;
		//获取带"/"的项目名，如：/xxx
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return projectName;
	}
}