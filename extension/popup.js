const status = document.getElementById("status");
document.getElementById("save").addEventListener("click", async () => {
  status.textContent = "Reading LeetCode page...";
  const [tab] = await chrome.tabs.query({active:true,currentWindow:true});
  if (!tab?.id || !tab.url?.includes("leetcode.com/problems/")) {
    status.textContent = "Open a LeetCode problem page first."; return;
  }
  try {
    const page = await chrome.tabs.sendMessage(tab.id, {type:"GET_METADATA"});
    if (!page?.ok) { status.textContent = page?.error || "Could not read page metadata."; return; }

    // Run in the page's MAIN world so the code can inspect page-owned editor objects.
    const injected = await chrome.scripting.executeScript({
      target: {tabId: tab.id},
      world: "MAIN",
      func: () => {
        const monaco = window.monaco;
        if (monaco?.editor?.getModels) {
          const models = monaco.editor.getModels();
          if (models.length) return models[0].getValue();
        }
        const cm = document.querySelector(".CodeMirror");
        if (cm?.CodeMirror) return cm.CodeMirror.getValue();
        const ta = document.querySelector("textarea");
        return ta?.value || "";
      }
    });
    const code = injected?.[0]?.result || "";
    if (!code.trim()) {
      status.textContent = "Could not read the editor contents. LeetCode may have changed its editor; this capture method needs adjustment.";
      return;
    }
    const data = {...page.data, code, status:"Accepted"};
    const response = await fetch("http://127.0.0.1:8765/submission", {
      method:"POST", headers:{"Content-Type":"application/json"}, body:JSON.stringify(data)
    });
    const saved = await response.json();
    if (!response.ok) throw new Error(saved.error || "Receiver error");
    status.textContent = `Saved ${saved.folder}/${saved.file}`;
  } catch (e) {
    status.textContent = `Error: ${e.message}\nCheck that receiver.py is running and reload the LeetCode tab.`;
  }
});