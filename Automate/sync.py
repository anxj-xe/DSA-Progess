from pathlib import Path
import subprocess
import time
import sys
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler

ROOT = Path(__file__).resolve().parent
WATCH_DIRS = ["LeetCode", "HackerRank", "Codeforces", "GeeksForGeeks"]
ALLOWED_SUFFIXES = {
    ".java", ".cpp", ".cc", ".c", ".h", ".hpp", ".py",
    ".js", ".jsx", ".ts", ".tsx", ".sql", ".html", ".css",
    ".md", ".txt"
}
IGNORE_NAMES = {".DS_Store", "Thumbs.db"}
DEBOUNCE_SECONDS = 4

def git(*args, check=True):
    return subprocess.run(
        ["git", *args], cwd=ROOT, text=True,
        capture_output=True, check=check
    )

def sync():
    # Only track the platform folders, not arbitrary files at repository root.
    git("add", "-A", "--", *WATCH_DIRS)
    status = git("status", "--porcelain").stdout.strip()
    if not status:
        return

    stamp = time.strftime("%Y-%m-%d %H:%M")
    result = git("commit", "-m", f"Update coding solutions: {stamp}", check=False)
    if result.returncode != 0:
        if "nothing to commit" in (result.stdout + result.stderr).lower():
            return
        print(result.stderr or result.stdout)
        return

    pushed = git("push", check=False)
    if pushed.returncode == 0:
        print(f"[{time.strftime('%H:%M:%S')}] Synced changes to GitHub.")
    else:
        print("Commit created, but push failed. Check your connection/authentication.")
        print(pushed.stderr or pushed.stdout)

class Handler(FileSystemEventHandler):
    def __init__(self):
        self.last_change = 0

    def on_any_event(self, event):
        if event.is_directory:
            return
        path = Path(event.src_path)
        if path.name in IGNORE_NAMES or path.suffix.lower() not in ALLOWED_SUFFIXES:
            return
        try:
            path.resolve().relative_to(ROOT)
        except ValueError:
            return
        if not any(part in WATCH_DIRS for part in path.relative_to(ROOT).parts):
            return
        self.last_change = time.time()

def main():
    for folder in WATCH_DIRS:
        (ROOT / folder).mkdir(exist_ok=True)

    print("Watching coding-platform folders. Press Ctrl+C to stop.")
    print("Only common code and text files inside the platform folders are committed.")
    handler = Handler()
    observer = Observer()
    for folder in WATCH_DIRS:
        observer.schedule(handler, str(ROOT / folder), recursive=True)
    observer.start()

    try:
        while True:
            time.sleep(1)
            if handler.last_change and time.time() - handler.last_change >= DEBOUNCE_SECONDS:
                handler.last_change = 0
                sync()
    except KeyboardInterrupt:
        print("\nStopping watcher...")
        observer.stop()
    observer.join()

if __name__ == "__main__":
    main()
