<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Final</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/Styles.css">
</head>
<body class="clocked-body">
    <div class="clocked-container">
        <!-- Welcome Header -->
        <h1 class="welcome-title" id="welcome-title">Welcome</h1>

        <!-- Action Buttons -->
        <div class="action-buttons">
            <button class="btn btn-action" id="clock-in-btn">Clock In</button>
            <button class="btn btn-action" id="clock-out-btn">Clock Out</button>
            <button class="btn btn-action" id="sign-out-btn">Sign Out</button>
        </div>

        <!-- Clock Entries Table -->
        <table class="clock-table" id="clock-table">
            <thead>
                <tr>
                    <th>User</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="clock-tbody">
                <!-- Rows populated by JavaScript -->
            </tbody>
        </table>
    </div>

    <script src="/scripts/clocked.js"></script>
</body>
</html>
