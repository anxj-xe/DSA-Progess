# Local LeetCode Saver prototype

Copy the `receiver` and `extension` folders into the root of your existing `Coding-Progress` repository.

## Start the local receiver
From IntelliJ terminal at the repository root:

```powershell
py receiver/receiver.py
```

Keep this terminal open. It listens only on 127.0.0.1.

## Load the Brave extension
1. Open `brave://extensions`.
2. Enable Developer mode.
3. Click Load unpacked.
4. Select the `extension` folder copied into your repository.
5. Pin Local LeetCode Saver.

## Capture a solution
1. Open a LeetCode problem page.
2. Submit and wait for Accepted.
3. Keep the problem open with your code in the editor.
4. Click the extension and choose Save accepted solution locally.
5. Check the generated folder under `LeetCode/`.

If your existing Git watcher is running, it should push the new files. Otherwise run:

```powershell
git add LeetCode
git commit -m "Add LeetCode solution"
git push
```

## Prototype limitations
- This first version uses a manual extension click after Accepted; background auto-detection is a later enhancement.
- It attempts to read the currently visible editor from the page's main JavaScript context. LeetCode can change its editor implementation, so verify the first capture.
- Problem title, number, difficulty and language are best-effort and should be checked initially.
- It does not read GitHub credentials or send anything directly to GitHub. Your local Git setup handles pushing.
