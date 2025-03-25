document.getElementById('addUserForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = event.target.username.value;
    fetch('/addUser?username=' + encodeURIComponent(username), { method: 'POST' })
        .then(response => {
            if (response.ok) {
                document.getElementById('userList').innerHTML += `<li><span>${username}</span> <button class="delete-btn" onclick="if (confirm('Are you sure you want to delete this user?')) { deleteUser('${username}'); }">Delete</button></li>`;
                event.target.username.value = '';
            }
        });
});

function deleteUser(username) {
    fetch('/deleteUser/' + encodeURIComponent(username), { method: 'DELETE' })
        .then(response => {
            if (response.ok) {
                document.querySelectorAll('#userList li').forEach(li => {
                    if (li.querySelector('span').textContent === username) {
                        li.remove();
                    }
                });
            }
        });
}
