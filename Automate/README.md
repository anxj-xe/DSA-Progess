# Coding Progress Sync

A small local watcher that commits and pushes coding solutions to your GitHub repository.

## Platform folders

- `LeetCode/`
- `HackerRank/`
- `Codeforces/`
- `GeeksForGeeks/`

Add your solution files inside the relevant folder. The watcher tracks common code and text formats (`.java`, `.cpp`, `.c`, `.py`, `.js`, `.ts`, `.md`, etc.).

## One-time setup (Windows)

1. Install Git for Windows and Python 3.
2. Create an **empty** GitHub repository named `Coding-Progress` (or choose another name).
3. Open PowerShell in this folder.
4. Run:

```powershell
git init
git branch -M main
git add .gitignore README.md requirements.txt sync.py LeetCode HackerRank Codeforces GeeksForGeeks
git commit -m "Initial coding progress setup"
git remote add origin https://github.com/YOUR-USERNAME/Coding-Progress.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username. On the first push, Git Credential Manager may open a browser window for GitHub sign-in. Do not paste a GitHub token into this script.

5. Install the Python dependency:

```powershell
py -m pip install -r requirements.txt
```

6. Start automatic syncing:

```powershell
py sync.py
```

Leave that PowerShell window open while solving problems. Press `Ctrl+C` to stop.

## Important

- The script only stages the four platform folders, not files elsewhere in the repository.
- `.gitignore` excludes common secret and credential files, but always review `git status` before your first push.
- Automatic syncing cannot fetch solutions from LeetCode/HackerRank/Codeforces by itself. Save or export your submissions into the matching local folder first.
- This is a local Git automation, not a browser extension. It does not need a GitHub OAuth app or a personal access token in the script.
