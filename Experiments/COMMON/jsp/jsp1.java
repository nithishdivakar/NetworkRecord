<!--
	HTML code to display a form.
	The form helps to enter the details of a book
-->

<html>
<title> Library Management System </title>
<body>
<center>
<font color="brown" face="times new roman" size="7">
	Library Book Log
</font><br/><br/>
<font face="times new roman" size="4"><b>
Fill the following fields with the relevant information.
</b></font>
<form method="post" action="library.jsp"><br/>
	<table border="0" align="center" width="405" bgcolor="lightgray">
		<tr>
			<td><b>
				Accession No
			</b></td><td>:</td>
			<td>
				<input name="accno" size="40">
			</td>
		</tr>
		<tr>
			<td><b>
				Title
			</b></td><td>:</td>
			<td>
				<input name="title" size="40">
			</td>
		</tr>
		<tr>
			<td><b>
				Publisher
			</b></td><td>:</td>
			<td>
				<input name="publ" size="40">
			</td>
		</tr>
		<tr>
			<td><b>
				Author
			</b></td><td>:</td>
			<td>
				<input name="author" size="40">
			</td>
		</tr>
		<tr>
			<td><b>
				Publishing Date
			</b></td><td>:</td>
			<td>
				<input name="pubdate" size="40">
			</td>
		</tr>
		<tr>
			<td><b>
				Purchasing Date
			</b></td><td>:</td>
			<td>
				<input name="purdate" size="40">
			</td>
		</tr>
		<tr>
			<td><b>
				Status
			</b></td><td>:</td>
			<td>
				<select name="status">
				<option value="issued">Issued</option>
				<option value="present in the library">Present in the Library</option>
				<option value="reference">Reference</option>
				<option value="cannot be issued">Cannot be Issued</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
			</td>
			<td>
			</td>
			<td align="right">
				<input type="submit" value="submit" id="submit" name="submit">
			</td>
		</tr>
	</table>
</form>
</center>
</body> 