<%-- 
    Document   : Q4_Xorcoder
    Created on : Nov 25, 2017, 5:05:11 AM
    Author     : nboeh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.io.*, java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Q4_XorCoder</title>
    </head>
    <body>
        <h1>Xor Coder</h1>
        <%
            String userIn = request.getParameter("in");
            String choice = request.getParameter("todo");
            
            
            System.out.println(choice);
            if(choice.equals("Encode")) {
                
                String outMessage = new String();
                
                for(int i = 0; i < userIn.length(); i++) {
                    if(!Character.isWhitespace(userIn.charAt(i))) {
                        
                        int val = (int) (userIn.charAt(i) ^ 1234);
                        if(i == 0)
                            outMessage = outMessage.concat(Integer.toString(val));
                        else
                            outMessage = outMessage.concat(" " + val);
                    }
                }
                out.print("<p>Encoded Message: " + outMessage + "</p>");
            }
            else {
                String[] charAsInt = userIn.split("\\s");
                char[] charArr = new char[charAsInt.length];
                for(int i = 0; i < charAsInt.length; i++) {
                    int val = Integer.parseInt(charAsInt[i]);
                    charArr[i] = (char) (val ^ 1234);
                }
                String toPrint = new String(charArr);
                out.print("<p>Decoded Message: " + toPrint + "</p>");
            }
        %>
    </body>
</html>
