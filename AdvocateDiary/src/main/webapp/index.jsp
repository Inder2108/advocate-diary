<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="utf-8" />
<title>CSS3 multicolor menu | Script Tutorials</title>
<link href="<c:url value="/resources/css/layout.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<header>
		<h2>CSS3 multicolor menu</h2>
		<a href="http://www.script-tutorials.com/css3-multicolor-menu/"
			class="stuts">Back to original tutorial on <span>Script
				Tutorials</span></a>
	</header>
	<div class="container">

		<span id="red"></span> <span id="orange"></span> <span id="pink"></span>
		<span id="green"></span> <span id="blue"></span> <span id="indigo"></span>
		<span id="violet"></span> <span id="grey"></span>

		<div class="colorScheme">
			<a href="#red" class="red"></a> <a href="#orange" class="orange"></a>
			<a href="#pink" class="pink"></a> <a href="#green" class="green"></a>
			<a href="#blue" class="blue"></a> <a href="#indigo" class="indigo"></a>
			<a href="#violet" class="violet"></a> <a href="#grey" class="grey"></a>
		</div>

		<ul id="nav">
			<li><a href="http://www.script-tutorials.com/">Home</a></li>
			<li><a class="hsubs" href="<c:url value="/clients"/>">Clients</a>
				<ul class="subs">
					<li><a href="#">Submenu 1</a></li>
					<li><a href="#">Submenu 2</a></li>
					<li><a href="#">Submenu 3</a></li>
					<li><a href="#">Submenu 4</a></li>
					<li><a href="#">Submenu 5</a></li>
				</ul></li>
			<li><a class="hsubs" href="#">Menu 2</a>
				<ul class="subs">
					<li><a href="#">Submenu 2-1</a></li>
					<li><a href="#">Submenu 2-2</a></li>
					<li><a href="#">Submenu 2-3</a></li>
					<li><a href="#">Submenu 2-4</a></li>
					<li><a href="#">Submenu 2-5</a></li>
					<li><a href="#">Submenu 2-6</a></li>
					<li><a href="#">Submenu 2-7</a></li>
					<li><a href="#">Submenu 2-8</a></li>
				</ul></li>
			<li><a class="hsubs" href="#">Menu 3</a>
				<ul class="subs">
					<li><a href="#">Submenu 3-1</a></li>
					<li><a href="#">Submenu 3-2</a></li>
					<li><a href="#">Submenu 3-3</a></li>
					<li><a href="#">Submenu 3-4</a></li>
					<li><a href="#">Submenu 3-5</a></li>
				</ul></li>
			<li><a href="#">Menu 4</a></li>
			<li><a href="#">Menu 5</a></li>
			<li><a href="#">Menu 6</a></li>
			<li><a
				href="http://www.script-tutorials.com/css3-multicolor-menu/">Back</a></li>
		</ul>

	</div>
</body>
</html>