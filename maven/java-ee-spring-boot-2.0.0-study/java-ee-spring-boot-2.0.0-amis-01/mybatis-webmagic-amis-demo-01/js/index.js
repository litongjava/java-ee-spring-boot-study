(function() {
	var amis = amisRequire('amis/embed');
	var root = {
		"$schema": "https://houtai.baidu.com/v2/schemas/page.json#",
		"type": "page",
		"title": "什么值得买优衣库专场",
		"toolbar": [{
			"type": "button",
			"actionType": "dialog",
			"label": "新增",
			"icon": "fa fa-plus pull-left",
			"primary": true,
			"dialog": {
				"title": "新增",
				"body": {
					"type": "form",
					"name": "sample-edit-form",
					"api": "",
					"controls": [{
							"type": "alert",
							"level": "info",
							"body": "因为没有配置 api 接口，不能真正的提交哈！"
						},
						{
							"type": "text",
							"name": "text",
							"label": "文本",
							"required": true
						},
						{
							"type": "divider"
						},
						{
							"type": "image",
							"name": "image",
							"label": "图片",
							"required": true
						},
						{
							"type": "divider"
						},
						{
							"type": "date",
							"name": "date",
							"label": "日期",
							"required": true
						},
						{
							"type": "divider"
						},
						{
							"type": "select",
							"name": "type",
							"label": "选项",
							"options": [{
									"label": "漂亮",
									"value": "1"
								},
								{
									"label": "开心",
									"value": "2"
								},
								{
									"label": "惊吓",
									"value": "3"
								},
								{
									"label": "紧张",
									"value": "4"
								}
							]
						}
					]
				}
			}
		}],
		"body": [{
				"type": "form",
				"title": "条件输入",
				"className": "m-t",
				"wrapWithPanel": false,
				"target": "service1",
				"mode": "inline",
				"controls": [{
					"type": "text",
					"name": "keywords",
					"placeholder": "关键字",
					"addOn": {
						"type": "button",
						"icon": "fa fa-search",
						"actionType": "submit",
						"level": "primary"
					}
				}]
			},
			{
				"type": "crud",
				"api": "http://localhost:8080/smzdm/list",
				"defaultParams": {
					"perPage": 5
				},
				"columns": [{
						"name": "title",
						"label": "标题",
						"type": "text"
					},
					{
						"name": "price",
						"label": "价格",
						"type": "text"
					},
					{
						"name": "url",
						"label": "商品链接",
						"type": "text"
					},
					{
						"type": "image",
						"label": "物品图片",
						"multiple": false,
						"name": "imgurl",
						"popOver": {
							"title": "查看大图",
							"body": "<div class=\"w-xxl\"><img class=\"w-full\" src=\"${imgurl}\"/></div>"
						}
					},
					{
						"name": "fbtime",
						"type": "date",
						"label": "发布日期"
					},
					{
						"type": "container",
						"label": "操作",
						"body": [{
								"type": "button",
								"icon": "fa fa-eye",
								"level": "link",
								"actionType": "dialog",
								"tooltip": "查看",
								"dialog": {
									"title": "查看",
									"body": {
										"type": "form",
										"controls": [{
												"type": "static",
												"name": "title",
												"label": "标题"
											},
											{
												"type": "divider"
											},
											{
												"type": "static",
												"name": "price",
												"label": "价格"
											},
											{
												"type": "divider"
											},
											{
												"type": "static-image",
												"label": "图片",
												"name": "imgurl",
												"popOver": {
													"title": "查看大图",
													"body": "<div class=\"w-xxl\"><img class=\"w-full\" src=\"${imgurl}\"/></div>"
												}
											},
											{
												"type": "divider"
											},
											{
												"name": "fbtime",
												"type": "static",
												"label": "发布时间"
											},
											{
												"type": "divider"
											},
											{
												"name": "url",
												"type": "static",
												"label": "购买链接"
											}
										]
									}
								}
							},
							{
								"type": "button",
								"icon": "fa fa-pencil",
								"tooltip": "编辑",
								"level": "link",
								"actionType": "drawer",
								"drawer": {
									"position": "left",
									"size": "lg",
									"title": "编辑",
									"body": {
										"type": "form",
										"name": "sample-edit-form",
										"controls": [{
												"type": "alert",
												"level": "info",
												"body": "因为没有配置 api 接口，不能真正的提交哈！"
											},
											{
												"type": "hidden",
												"name": "id"
											},
											{
												"type": "text",
												"name": "text",
												"label": "文本",
												"required": true
											},
											{
												"type": "divider"
											},
											{
												"type": "image",
												"name": "image",
												"multiple": false,
												"label": "图片",
												"required": true
											},
											{
												"type": "divider"
											},
											{
												"type": "date",
												"name": "date",
												"label": "日期",
												"required": true
											},
											{
												"type": "divider"
											},
											{
												"type": "select",
												"name": "type",
												"label": "选项",
												"options": [{
														"label": "漂亮",
														"value": "1"
													},
													{
														"label": "开心",
														"value": "2"
													},
													{
														"label": "惊吓",
														"value": "3"
													},
													{
														"label": "漂亮",
														"value": "紧张"
													}
												]
											}
										]
									}
								}
							},
							{
								"type": "button",
								"level": "link",
								"icon": "fa fa-times text-danger",
								"actionType": "ajax",
								"tooltip": "删除",
								"confirmText": "您确认要删除? 没有配置 api 确定了也没用，还是不要确定了",
								"api": ""
							}
						]
					}
				]
			}
		]
	};

	amis.embed('#root', root);
})();
