<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
<tiles:insertAttribute name="title" ignore="true" />
</title>
</head>
<body>
<div>
	<tiles:insertAttribute name="body" />
</div>
</body>
</html>