<HTML>
<HEAD>
        <TITLE>Computing Pi</TITLE>
        <%@ page import="javax.servlet.http.HttpUtils,java.util.Enumeration" %>
        <%@ page import="java.lang.management.*" %>
        <%@ page import="java.util.*" %>
</HEAD>
<BODY>


<%
        long chron= System.currentTimeMillis();
        double pi= 0.;
        for(int i=0; i != 100000000; ++i){
            pi += ((i % 2==0) ? 1 : -1)/(2*i+1.0) ;
        }
        pi *= 4;
       chron = System.currentTimeMillis()-chron;
%>
<%= pi %>
computed in
<%= chron %> ms.

</BODY>
</HTML>
