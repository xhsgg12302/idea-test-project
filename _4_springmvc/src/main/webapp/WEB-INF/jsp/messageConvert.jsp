<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>MessageConverter</title>

    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .form-container {
            width: 30%;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .form-container label {
            display: inline-block;
            width: 80px;
            margin-bottom: 5px;
        }
        .form-container input[type="text"] {
            width: calc(100% - 100px);
            padding: 6px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        .form-container input[type="button"] {
            width: 100%;
            padding: 8px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .form-container input[type="button"]:hover {
            background-color: #0056b3;
        }
        #response {
            margin-top: 20px;
        }
    </style>

    <script src="<%= request.getContextPath() %>/static/js/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#submitBtn').click(function() {
                //const formData = $('#myForm').serialize();
                const formData = {
                    name: $('#name').val(),
                    age: $('#age').val(),
                    account: $('#account').val(),
                    password: $('#password').val(),
                    time: $('#time').val()
                };
                $.ajax({
                    type: 'POST',
                    url: '<%= request.getContextPath() %>/msg/convert/ldt',
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
                    success: function(response) {
                        $('#response').text(JSON.stringify(response));
                    }
                });
            });
        });
    </script>
</head>
<body>
<h2>Request</h2>
<div class="form-container">
    <form id="myForm">
        <label for="name">Name:</label><input type="text" id="name" name="name"><br>
        <label for="age">Age:</label><input type="text" id="age" name=age><br>
        <label for="account">Account:</label><input type="text" id="account" name=account><br>
        <label for="password">Password:</label><input type="text" id="password" name=password><br>
        <label for="time">Time:</label><input type="text" id="time" name=time><br><br>
        <input type="button" value="Submit" id="submitBtn">
    </form>
</div>

<h2 style="margin-top: 50px">Response</h2>
<hr>
<div id="response"></div>
</body>
</html>


