<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
</head>
<body>
    <h1>User Registration</h1>
    <!-- Form for user registration, action points to the servlet URL -->
    <form action="user" method="post">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="accountNumber">Account Number:</label><br>
        <input type="text" id="accountNumber" name="accountNumber" required><br><br>
        
        <button type="submit">Register</button>
    </form>
</body>
</html>
