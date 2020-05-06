<%@page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Hospitals Managment</title>
    <link rel="stylesheet" href="Views/css/bootstrap.min.css">
    <script src="Components/jquery-3.5.0.min.js"></script>
    <script src="Components/hospitals.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-6">
            <h1>Hospitals Managment </h1>
            <form id="formHospital" name="formHospital">
                hospitalName:
                <input id="hospitalName" name="hospitalName" type="text"
                       class="form-control form-control-sm">
                <br> hospitalAddress:
                <input id="hospitalAddress" name="hospitalAddress" type="text"
                       class="form-control form-control-sm">
                <br> hospitalUsername:
                <input id="hospitalUsername" name="hospitalUsername" type="text"
                       class="form-control form-control-sm">
                <br> hospitalPassword:
                <input id="hospitalPassword" name="hospitalPassword" type="text"
                       class="form-control form-control-sm">
                <br>
                <input id="btnSave" name="btnSave" type="button" value="Save"
                       class="btn btn-primary">
                <input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
            </form>
            <div id="alertSuccess" class="alert alert-success"></div>
            <div id="alertError" class="alert alert-danger"></div>
            <br>
            <div id="divHospitalsGrid">
                <%
                    Hospital hospitalObj = new Hospital();
                    out.print(hospitalObj.readHospital());
                %>
            </div>

        </div>
    </div>
</div>

</body>
</html>