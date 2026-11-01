from http.server import BaseHTTPRequestHandler, HTTPServer
from pathlib import Path
from urllib.parse import urlparse
import json, re, time

ROOT = Path(__file__).resolve().parent.parent
OUT = ROOT / "LeetCode"
HOST, PORT = "127.0.0.1", 8765

def slugify(value):
    value = re.sub(r"[^a-zA-Z0-9-]+", "-", str(value).strip().lower())
    return re.sub(r"-+", "-", value).strip("-")[:70] or "untitled"

def filename(language):
    return {
        "java":"Solution.java", "python":"solution.py", "python3":"solution.py",
        "cpp":"solution.cpp", "c++":"solution.cpp", "c":"solution.c",
        "javascript":"solution.js", "typescript":"solution.ts",
        "mysql":"solution.sql", "golang":"solution.go", "go":"solution.go",
        "rust":"solution.rs", "kotlin":"Solution.kt", "swift":"Solution.swift"
    }.get(str(language).lower(), "solution.txt")

class Handler(BaseHTTPRequestHandler):
    def reply(self, code, obj):
        data = json.dumps(obj).encode()
        self.send_response(code)
        self.send_header("Content-Type", "application/json")
        self.send_header("Access-Control-Allow-Origin", self.headers.get("Origin", "null"))
        self.send_header("Access-Control-Allow-Methods", "POST, GET, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "Content-Type")
        self.end_headers()
        self.wfile.write(data)

    def do_OPTIONS(self):
        self.reply(204, {})

    def do_GET(self):
        if urlparse(self.path).path == "/health":
            return self.reply(200, {"ok": True})
        self.reply(404, {"error":"Not found"})

    def do_POST(self):
        if urlparse(self.path).path != "/submission":
            return self.reply(404, {"error":"Not found"})
        try:
            size = int(self.headers.get("Content-Length", "0"))
            if size <= 0 or size > 2_000_000:
                return self.reply(400, {"error":"Invalid request size"})
            d = json.loads(self.rfile.read(size).decode())
            if d.get("status") != "Accepted":
                return self.reply(400, {"error":"Only Accepted submissions can be saved"})
            for key in ("title", "url", "language", "code"):
                if not str(d.get(key, "")).strip():
                    return self.reply(400, {"error":f"Missing {key}"})
            number = str(d.get("number", "")).strip()
            folder = OUT / ((number.zfill(4) if number.isdigit() else "xxxx") + "-" + slugify(d.get("slug") or d["title"]))
            folder.mkdir(parents=True, exist_ok=True)
            code_file = folder / filename(d["language"])
            code_file.write_text(d["code"], encoding="utf-8")
            readme = f"""# {d['title']}

- Platform: LeetCode
- Problem: {number or 'Not detected'}
- Difficulty: {d.get('difficulty') or 'Not detected'}
- Language: {d['language']}
- Status: Accepted
- Problem URL: {d['url']}
- Captured: {time.strftime('%Y-%m-%d %H:%M:%S')}

## Approach

_Add your explanation here._

## Complexity

- Time:
- Space:
"""
            (folder / "README.md").write_text(readme, encoding="utf-8")
            self.reply(200, {"ok":True, "folder":str(folder.relative_to(ROOT)), "file":code_file.name})
        except Exception as e:
            self.reply(500, {"error":str(e)})

    def log_message(self, fmt, *args):
        print("[receiver] " + (fmt % args))

if __name__ == "__main__":
    OUT.mkdir(parents=True, exist_ok=True)
    print(f"Local LeetCode receiver listening on http://{HOST}:{PORT}")
    print("Keep this terminal open. Press Ctrl+C to stop.")
    HTTPServer((HOST, PORT), Handler).serve_forever()
