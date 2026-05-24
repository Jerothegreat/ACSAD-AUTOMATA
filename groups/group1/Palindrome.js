function getLength(word) {
  return String(word ?? "").length;
}

function toLowerCase(char) {
  const code = char.charCodeAt(0);

  if (code >= 65 && code <= 90) {
    return String.fromCharCode(code + 32);
  }

  return char;
}

function removeSpaces(word, length) {
  let clean = "";

  for (let index = 0; index < length; index += 1) {
    if (word.charAt(index) !== " ") {
      clean += word.charAt(index);
    }
  }

  return clean;
}

function isPalindrome(word, length) {
  let left = 0;
  let right = length - 1;

  while (left < right) {
    if (toLowerCase(word.charAt(left)) !== toLowerCase(word.charAt(right))) {
      return false;
    }

    left += 1;
    right -= 1;
  }

  return true;
}

function main(inputs) {
  const word = String(inputs[0] ?? "");
  const originalLength = getLength(word);
  const clean = removeSpaces(word, originalLength);
  const cleanLength = getLength(clean);
  const palindrome = isPalindrome(clean, cleanLength);

  return [
    `String: ${word}`,
    `Length: ${originalLength}`,
    palindrome
      ? `${word} is a palindrome`
      : `${word} is not a palindrome`,
  ].join("\n");
}
