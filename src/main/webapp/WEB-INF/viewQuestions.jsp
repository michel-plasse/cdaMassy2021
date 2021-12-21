<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Questions</title>
</head>
<body>
<%
String libelleQuestion = request.getAttribute("libelleQuestion").toString();
String libelleProposition = request.getAttribute("libelleProposition").toString();
String undefined = request.getAttribute("undefined").toString();
String intituleQuestion = request.getAttribute("intituleQuestion").toString();
String correct = request.getAttribute("correct").toString();
String incorrect = request.getAttribute("incorrect").toString();

 out.println("<h2>Please verify the details</h2>"); 
 %>
 <table border="1" style="width: 80%; margin-left: auto; margin-right: auto; ">
  <tr>
     <td><b>Contact No</b></td>
     <td><%= intituleQuestion %></td>
    </tr>
    <tr>
     <td><b>First Name</b></td>
     <td><%= libelleQuestion %></td>
    </tr>
    <tr>
     <td><b>Last Name</b></td>
     <td><%= libelleProposition %></td>
    </tr>
    <tr>
     <td><b>UserName</b></td>
     <td><%= undefined %></td>
    </tr>
    <tr>
     <td><b>Address</b></td>
     <td><%= correct %></td>
    </tr>
    <tr>
     <td><b>Address</b></td>
     <td><%= incorrect %></td>
    </tr>
   </table>
</body>
</html>