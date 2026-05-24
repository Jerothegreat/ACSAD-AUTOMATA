function main(inputs) {
  const raw = String(inputs[0] ?? "");
  if (raw.trim() === "")
    throw new Error("INVALID OUTPUT\nPlease enter a string.");

  const length = raw.length;
  const cleaned = raw.toLowerCase();
  let left = 0, right = length - 1;
  let isPalindrome = true;

  while (left < right) {
    if (cleaned[left] === " ") { left++; continue; }
    if (cleaned[right] === " ") { right--; continue; }
    if (cleaned[left] !== cleaned[right]) { isPalindrome = false; break; }
    left++; right--;
  }

  return [
    `You entered: ${raw}`,
    `Result: The string ${isPalindrome ? "IS" : "is NOT"} a palindrome.`,
    `Length of the string: ${length}`
  ].join("\n");
}