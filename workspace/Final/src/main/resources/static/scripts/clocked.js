/**
 * CS3220 Final - Time Clock Page
 * Handles clock in/out actions and displays all records.
 */

document.addEventListener('DOMContentLoaded', () => {
    // Elements
    const welcomeTitle = document.getElementById('welcome-title');
    const clockInBtn = document.getElementById('clock-in-btn');
    const clockOutBtn = document.getElementById('clock-out-btn');
    const signOutBtn = document.getElementById('sign-out-btn');
    const clockTbody = document.getElementById('clock-tbody');

    // Get current user from session
    const userJson = sessionStorage.getItem('user');
    if (!userJson) {
        // Not logged in, redirect to login page
        window.location.href = '/';
        return;
    }

    const user = JSON.parse(userJson);

    // Display welcome message with user's name
    welcomeTitle.textContent = `Welcome ${user.name}`;

    // Load all clock entries on page load
    loadClockEntries();

    // Clock In button
    clockInBtn.addEventListener('click', async () => {
        await addClockEntry('Clock In');
    });

    // Clock Out button
    clockOutBtn.addEventListener('click', async () => {
        await addClockEntry('Clock Out');
    });

    // Sign Out button - redirect to login page
    signOutBtn.addEventListener('click', () => {
        sessionStorage.removeItem('user');
        window.location.href = '/';
    });

    /**
     * Fetch all clock entries from API and render the table.
     */
    async function loadClockEntries() {
        try {
            const response = await fetch('/api/clocked');
            if (!response.ok) {
                console.error('Failed to load clock entries');
                return;
            }

            const entries = await response.json();
            renderTable(entries);

        } catch (error) {
            console.error('Error loading clock entries:', error);
        }
    }

    /**
     * Add a new clock entry (Clock In or Clock Out).
     */
    async function addClockEntry(action) {
        try {
            const response = await fetch('/api/clocked', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    userId: user.id,
                    action: action
                })
            });

            if (!response.ok) {
                const data = await response.json();
                console.error('Failed to add clock entry:', data.error);
                return;
            }

            // Reload table to show new entry
            loadClockEntries();

        } catch (error) {
            console.error('Error adding clock entry:', error);
        }
    }

    /**
     * Render the clock entries table.
     * @param {Array} entries - Array of ClockedEntryDto objects
     */
    function renderTable(entries) {
        clockTbody.innerHTML = '';

        entries.forEach(entry => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${escapeHtml(entry.userName)}</td>
                <td>${escapeHtml(entry.date)}</td>
                <td>${escapeHtml(entry.time)}</td>
                <td>${escapeHtml(entry.action)}</td>
            `;
            clockTbody.appendChild(row);
        });
    }

    /**
     * Escape HTML to prevent XSS.
     */
    function escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }
});
