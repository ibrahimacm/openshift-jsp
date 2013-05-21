<%@ page import="java.io.File"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Files </title>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0">
<%
        File folder= new File(System.getenv("OPENSHIFT_DATA_DIR"));
        for (File f : folder.listFiles()) {
%>
 <%if(f.isFile())
{%>
<tr>
<td>

<a href="<%= "uploads/"+f.getName() %>" > <%= f.getName() %> </a>
</td>
</tr>
<% } %>
<% } %>
</table>

<form action="/uploadFile" enctype="multipart/form-data" method="post">
    <input type="file" name="uploadFile" />
    <input type="submit" value="upload !"/>
</form>
</body>
</html>
