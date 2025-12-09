class MessageBoardApp {
    constructor() {
        this.apiBase = '/api';
        this.userKey = 'cs3220-user';
        this.root = document.getElementById('app-root');
        this.logoutBtn = document.getElementById('logout-btn');
        this.statusBanner = null;
    }

    init() {
        if (this.logoutBtn) {
            this.logoutBtn.addEventListener('click', () => this.logout());
        }

        if (document.getElementById('register-form')) {
            this.initAuthForms();
        }

        if (this.root) {
            this.ensureAuthenticated();
            this.renderBoard();
        }
    }

    initAuthForms() {
        document.getElementById('register-form').addEventListener('submit', async (e) => {
            e.preventDefault();
            const payload = {
                fullName: document.getElementById('register-full-name').value,
                username: document.getElementById('register-username').value,
                password: document.getElementById('register-password').value,
            };
            try {
                const data = await this.request('/auth/register', payload);
                document.getElementById('register-feedback').textContent = `User ${data.user.username} created. You can log in now.`;
                document.getElementById('register-form').reset();
            } catch (err) {
                document.getElementById('register-feedback').textContent = err.message;
            }
        });

        document.getElementById('login-form').addEventListener('submit', async (e) => {
            e.preventDefault();
            const payload = {
                username: document.getElementById('login-username').value,
                password: document.getElementById('login-password').value,
            };
            try {
                const data = await this.request('/auth/login', payload);
                sessionStorage.setItem(this.userKey, JSON.stringify(data.user));
                window.location.href = '/messageboard';
            } catch (err) {
                document.getElementById('login-feedback').textContent = err.message;
            }
        });
    }

    async renderBoard() {
        this.user = this.getStoredUser();
        this.root.innerHTML = `
            <section class="status-banner alert d-none" role="alert"></section>
            <section class="card shadow-sm mb-4">
                <div class="card-body">
                    <h2 class="h4 mb-3">Create Message</h2>
                    <form id="message-form">
                        <div class="mb-3">
                            <textarea id="message-body" class="form-control" placeholder="Share something..." required maxlength="500"></textarea>
                        </div>
                        <button class="btn btn-primary" type="submit">Post Message</button>
                    </form>
                </div>
            </section>
            <section class="card shadow-sm">
                <div class="card-body">
                    <h2 class="h4 mb-3">Message Board</h2>
                    <div class="table-responsive">
                        <table class="table align-middle" id="message-table">
                            <thead>
                                <tr>
                                    <th>Message</th>
                                    <th>Author</th>
                                    <th>Created</th>
                                    <th>Updated</th>
                                    <th class="text-end">Actions</th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </section>`;

        this.statusBanner = this.root.querySelector('.status-banner');
        document.getElementById('message-form').addEventListener('submit', async (e) => {
            e.preventDefault();
            await this.createMessage(document.getElementById('message-body').value);
        });

        await this.loadMessages();
    }

    async loadMessages() {
        const tableBody = document.querySelector('#message-table tbody');
        tableBody.innerHTML = '<tr><td colspan="5" class="text-center text-muted">Loading...</td></tr>';

        try {
            const messages = await this.request('/messages');
            if (messages.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="5" class="text-center text-muted">No messages yet.</td></tr>';
                return;
            }

            tableBody.innerHTML = messages.map(m => `
                <tr data-id="${m.id}">
                    <td>
                        <p class="mb-1">${this.escape(m.body)}</p>
                        ${this.user && this.user.id === m.author.id ? `<small class="text-muted">You wrote this.</small>` : ''}
                    </td>
                    <td>${this.escape(m.author.fullName)}</td>
                    <td>${this.formatDate(m.createdAt)}</td>
                    <td>${this.formatDate(m.updatedAt)}</td>
                    <td class="text-end">
                        ${this.user && this.user.id === m.author.id ? `
                        <button class="btn btn-link btn-sm btn-edit">Edit</button>
                        <button class="btn btn-link btn-sm btn-delete">Delete</button>` : ''}
                    </td>
                </tr>`).join('');

            tableBody.querySelectorAll('.btn-edit').forEach(btn => {
                btn.addEventListener('click', (evt) => this.startEdit(evt.target.closest('tr').dataset.id));
            });
            tableBody.querySelectorAll('.btn-delete').forEach(btn => {
                btn.addEventListener('click', (evt) => this.deleteMessage(evt.target.closest('tr').dataset.id));
            });
        } catch (err) {
            tableBody.innerHTML = `<tr><td colspan="5" class="text-danger">${err.message}</td></tr>`;
        }
    }

    async createMessage(body) {
        await this.request('/messages', {
            userId: this.user.id,
            body
        });
        document.getElementById('message-form').reset();
        await this.loadMessages();
        this.showBanner('Message posted', 'alert-success');
    }

    startEdit(id) {
        const row = document.querySelector(`tr[data-id="${id}"]`);
        const text = row.querySelector('p').textContent;
        row.querySelector('p').outerHTML = `<textarea class="form-control" data-edit-body>${this.escape(text)}</textarea>`;
        const actions = row.querySelector('.text-end');
        actions.innerHTML = `
            <button class="btn btn-sm btn-primary me-2" data-save>Edit</button>
            <button class="btn btn-sm btn-secondary" data-cancel>Cancel</button>`;
        actions.querySelector('[data-save]').addEventListener('click', () => this.saveEdit(id));
        actions.querySelector('[data-cancel]').addEventListener('click', () => this.loadMessages());
    }

    async saveEdit(id) {
        const body = document.querySelector(`tr[data-id="${id}"] textarea[data-edit-body]`).value;
        await this.request(`/messages/${id}`, { userId: this.user.id, body }, 'PUT');
        await this.loadMessages();
        this.showBanner('Message updated', 'alert-info');
    }

    async deleteMessage(id) {
        if (!confirm('Delete this message?')) return;
        await this.request(`/messages/${id}?userId=${this.user.id}`, null, 'DELETE');
        await this.loadMessages();
        this.showBanner('Message deleted', 'alert-warning');
    }

    logout() {
        sessionStorage.removeItem(this.userKey);
        window.location.href = '/';
    }

    ensureAuthenticated() {
        const user = this.getStoredUser();
        if (!user) {
            window.location.href = '/';
        }
        this.user = user;
        const header = document.querySelector('header .text-muted');
        if (header && user) {
            header.textContent = `Logged in as ${user.fullName}`;
        }
    }

    getStoredUser() {
        const raw = sessionStorage.getItem(this.userKey);
        return raw ? JSON.parse(raw) : null;
    }

    async request(path, body = null, method = null) {
        // Auto-detect method: GET for fetching messages, POST for others unless specified
        if (method === null) {
            method = body === null && !path.includes('?') ? 'GET' : 'POST';
        }

        const options = {
            method,
            headers: {
                'Content-Type': 'application/json'
            }
        };

        // Only attach body for POST/PUT requests
        if (body && (method === 'POST' || method === 'PUT')) {
            options.body = JSON.stringify(body);
        }

        const response = await fetch(`${this.apiBase}${path}`, options);

        if (!response.ok) {
            const errorText = await response.text();
            let message = 'Unexpected error';
            try {
                const parsed = JSON.parse(errorText);
                message = parsed.message || parsed.error || errorText;
            } catch (_) {
                message = errorText || response.statusText;
            }
            throw new Error(message);
        }

        if (response.status === 204) {
            return null;
        }
        return response.json();
    }

    formatDate(value) {
        return new Date(value).toLocaleString();
    }

    showBanner(text, cls) {
        if (!this.statusBanner) return;
        this.statusBanner.className = `status-banner alert ${cls}`;
        this.statusBanner.textContent = text;
        this.statusBanner.classList.remove('d-none');
        setTimeout(() => this.statusBanner.classList.add('d-none'), 2500);
    }

    escape(str) {
        return str.replace(/[&<>"']/g, (c) => ({
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#39;'
        }[c]));
    }
}

window.addEventListener('DOMContentLoaded', () => {
    const app = new MessageBoardApp();
    app.init();
});
