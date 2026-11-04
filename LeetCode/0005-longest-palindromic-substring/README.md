# 5. Longest Palindromic Substring

**Difficulty:** Medium  
**Problem Link:** [https://leetcode.com/problems/longest-palindromic-substring/](https://leetcode.com/problems/longest-palindromic-substring/)

![Difficulty: Medium](https://img.shields.io/badge/Difficulty-Medium-orange)

## Submission Stats

| Metric | Value | Percentile |
| :--- | :--- | :--- |
| **Language** | `java` | - |
| **Runtime** | 13 ms | Beats 96.8% |
| **Memory** | 42.9 MB | Beats 93.8% |

### Tags

`Two Pointers` `String` `Dynamic Programming` `Manacher`

## Problem Description

<p>Given a string <code>s</code>, return <em>the longest</em> <span data-keyword="palindromic-string"><em>palindromic</em></span> <span data-keyword="substring-nonempty"><em>substring</em></span> in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;babad&quot;
<strong>Output:</strong> &quot;bab&quot;
<strong>Explanation:</strong> &quot;aba&quot; is also a valid answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbbd&quot;
<strong>Output:</strong> &quot;bb&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consist of only digits and English letters.</li>
</ul>

---
*Captured automatically by [CodeRelay](https://github.com).*
