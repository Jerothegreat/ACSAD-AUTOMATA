// calculate length of string without using built-in functions
function getLength(str) {
    let count = 0;
    for (let char of str) {
        count++;
    }
    return count;
}

// remove da spaces
function removeSpaces(str) {
    let result = "";
    for (let char of str) {
        if (char !== ' ') {
            result += char;
        }
    }
    return result;
}

// convert uppercase to lowercase using ascii
function lowUpCombo(str) {
    let result = "";
    for (let i = 0; i < str.length; i++) {
        let code = str.charCodeAt(i);
        if (code >= 65 && code <= 90) {
            result += String.fromCharCode(code + 32);
        } else {
            result += str[i];
        }
    }
    return result;
}

// checks if the string can be read vice versa
function isPalindrome(str) {
    let left = 0;
    let right = getLength(str) - 1;

    // loop as long as the left pointer is before the right one
    while (left < right) {
        if (str[left] !== str[right]) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}

function main(inputs) {
    const rawInput = String(inputs[0] ?? "").trim();

    if (rawInput === "") {
        throw new Error("Please enter a string to evaluate.");
    }

    const length = getLength(rawInput);
    const noSpaceStr = removeSpaces(rawInput);
    const finalStr = lowUpCombo(noSpaceStr);
    const palindrome = isPalindrome(finalStr);

    let output = `String: ${rawInput}\n`;
    output += `Length: ${length}\n`;

    if (palindrome) {
        output += `${rawInput} is a palindrome!!!!`;
    } else {
        output += `${rawInput} is NOT a palindrome!!!!`;
    }

    return output;
}