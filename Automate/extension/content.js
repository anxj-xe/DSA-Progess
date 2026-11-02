
function getProblemSlug(url) {
  const match = url.match(/\/problems\/([^/?#]+)/);
  return match ? match[1] : "unknown";
}

function formatTitle(slug) {
  return slug
      .split("-")
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(" ");
}

function getProblemMetadata() {
  const url = window.location.href;
  const slug = getProblemSlug(url);

  // Extract problem title and number
  const heading = document.querySelector(
      '[data-cy="question-title"], div[data-cy="question-title"], h1'
  );

  let title = heading?.innerText?.trim() || formatTitle(slug);

// Extract problem number from the page's metadata
  let number = "Not detected";

  const pageText = document.body.innerText;

  const numberMatch = pageText.match(
      /(?:^|\n)\s*(\d+)\.\s+[A-Z]/m
  );

  if (numberMatch) {
    number = numberMatch[1];
  }

// Fallback for common LeetCode title formats
  if (number === "Not detected") {
    const titleMatch = title.match(/^(\d+)\s*\.?\s*(.*)$/);

    if (titleMatch) {
      number = titleMatch[1];
      title = titleMatch[2] || title;
    }
  }

  // Extract difficulty
  const difficultyElements = document.querySelectorAll(
      '[diff], [class*="text-difficulty"], [class*="difficulty"]'
  );

  let difficulty = "Not detected";

  for (const element of difficultyElements) {
    const text = (
        element.getAttribute("diff") ||
        element.innerText ||
        ""
    ).trim();

    const match = text.match(/\b(Easy|Medium|Hard)\b/i);

    if (match) {
      difficulty =
          match[1].charAt(0).toUpperCase() +
          match[1].slice(1).toLowerCase();
      break;
    }
  }

  // Fallback: search page text for difficulty
  if (difficulty === "Not detected") {
    const match = document.body.innerText.match(
        /\b(Easy|Medium|Hard)\b/i
    );

    if (match) {
      difficulty =
          match[1].charAt(0).toUpperCase() +
          match[1].slice(1).toLowerCase();
    }
  }

  // Extract programming language from LeetCode editor controls
  let language = "unknown";

  const languageSelectors = [
    '[data-cy="lang-select"]',
    'button[aria-label*="Language"]',
    'button[class*="lang"]',
    '[class*="language-selector"]'
  ];

  for (const selector of languageSelectors) {
    const element = document.querySelector(selector);

    if (element) {
      const text = element.innerText?.trim();

      if (text) {
        language = text.split("\n")[0].trim();
        break;
      }
    }
  }

  // Fallback: look for a visible button containing a known language
  if (language === "unknown") {
    const knownLanguages = [
      "Java",
      "JavaScript",
      "TypeScript",
      "Python",
      "Python3",
      "C",
      "C++",
      "C#",
      "Go",
      "Rust",
      "Kotlin",
      "Swift",
      "Ruby",
      "PHP",
      "Scala"
    ];

    const buttons = document.querySelectorAll("button");

    for (const button of buttons) {
      const text = button.innerText?.trim();

      if (text && knownLanguages.includes(text)) {
        language = text;
        break;
      }
    }
  }

  return {
    url,
    slug,
    title,
    number,
    difficulty,
    language
  };
}

// Listen for requests from popup.js
chrome.runtime.onMessage.addListener(
    (message, sender, sendResponse) => {
      if (message.type !== "GET_METADATA") {
        return;
      }

      const pageText = document.body.innerText;

      // Check whether an Accepted submission is visible
      if (!/\bAccepted\b/i.test(pageText)) {
        sendResponse({
          ok: false,
          error: "No Accepted result found. Submit your solution successfully first."
        });

        return true;
      }

      const metadata = getProblemMetadata();

      sendResponse({
        ok: true,
        data: metadata
      });

      return true;
    }
);
