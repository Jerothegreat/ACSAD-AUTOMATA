function main(inputs) {
  const raw = String(inputs[0] ?? "");

  if (raw.trim() === "") {
    throw new Error("Please enter a string.");
  }

  if (/[0-9]/.test(raw)) {
    throw new Error("Numbers are not allowed. Please try again.");
  }

  const cleaned = raw.toLowerCase().replace(/[^a-z]/g, "");

  if (cleaned.length === 0) {
    throw new Error("No valid letters found in the input.");
  }

  const reversed = cleaned.split("").reverse().join("");
  const isPalindrome = cleaned === reversed;

  return (
    `String entered : ${raw}\n` +
    `Length         : ${raw.length}\n` +
    `\n` +
    (isPalindrome
      ? `The string is a Palindrome.`
      : `The string is NOT a Palindrome.`)
  );
}