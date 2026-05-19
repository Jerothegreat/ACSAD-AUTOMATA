function main(inputs) {
  const text = String(inputs[0] ?? "");

  if (text.length === 0) {
    throw new Error("Please enter a non-empty string.");
  }

  const length = text.length;
  let left = 0;
  let right = length - 1;
  let palindrome = true;

  while (left < right) {
    const a = toLowerAscii(text[left]);
    const b = toLowerAscii(text[right]);

    if (a !== b) {
      palindrome = false;
      break;
    }

    left += 1;
    right -= 1;
  }

  return [
    `String: ${text}`,
    `Length: ${length}`,
    `Result: ${palindrome ? "It is a palindrome." : "It is NOT a palindrome."}`
  ].join("\n");
}

function toLowerAscii(ch) {
  const code = ch.charCodeAt(0);

  if (code >= 65 && code <= 90) {
    return String.fromCharCode(code + 32);
  }

  return ch;
}
