<!--
	A JSP file to display the details of a book 
	entered through the above shown form.
-->

<html>
<title> Libray Management System </title>
<body>
<font color="brown" face="times new roman" size="6"><b>
<center>
Library Book Catalog
</center>
</b></font><br/>
<table bgcolor="lightgray" align="center" width="500" height="300">
	<tr>
		<td><b>Accession No</b></td>
		<td>:</td>
		<td><b><%=request.getParameter("accno")%></b></td>
	</tr>
	<tr>
		<td><b>Title</b></td>
		<td>:</td>
		<td><b><%=request.getParameter("title")%></b></td>
	</tr>
	<tr>
		<td><b>Publisher</b></td>
		<td>:</td>
		<td><b><%=request.getParameter("publ")%></b></td>
	</tr>
	<tr>
		<td><b>Author</b></td>
		<td>:</td>
		<td><b><%=request.getParameter("author")%></b></td>
	</tr>
	<tr>
		<td><b>Publishing Date</b></td>
		<td>:</td>
		<td><b><%=request.getParameter("pubdate")%></b></td>
	</tr>
	<tr>
		<td><b>Purchasing Date</b></td>
		<td>:</td>
		<td><b><%=request.getParameter("purdate")%></b></td>
	</tr>
	<tr>
		<td><b>Status</b></th>
		<td>:</td>
		<td><b><%=request.getParameter("status")%></b></td>
	</tr>
</table>
</body>
</html>