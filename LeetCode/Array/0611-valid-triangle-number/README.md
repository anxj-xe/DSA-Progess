# 611. Valid Triangle Number

**Difficulty:** Medium  
**Problem Link:** [https://leetcode.com/problems/valid-triangle-number/](https://leetcode.com/problems/valid-triangle-number/)

![Difficulty: Medium](https://img.shields.io/badge/Difficulty-Medium-orange)

## Submission Stats

| Metric | Value | Percentile |
| :--- | :--- | :--- |
| **Language** | `java` | - |
| **Runtime** | 27 ms | Beats 90.5% |
| **Memory** | 45 MB | Beats 94.7% |

### Tags

`Array` `Two Pointers` `Binary Search` `Greedy` `Sorting`

## Problem Description

<p>Given an integer array <code>nums</code>, return <em>the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,3,4]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

---
*Captured automatically by [CodeRelay](https://github.com).*
