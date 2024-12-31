<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    
    <!-- Displaying user information passed from the servlet -->
    <p><strong>Name:</strong> ${name}</p>
    <p><strong>Email:</strong> ${email}</p>
    <p><strong>Account Number:</strong> ${accountNumber}</p>
    
    <!-- Link to go back to the registration page -->
    <a href="registrationForm.jsp">Back to Registration</a>
</body>
</html>
