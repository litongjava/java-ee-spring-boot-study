<!DOCTYPE html>
<html >
<head>
    <title>test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>${name}</h1>
<h1>${age}</h1>
<h2>列表</h2>

<ul>
    <#list list as item>
        <li>${item}</li>
    </#list>
</ul>

</body>
</html>
