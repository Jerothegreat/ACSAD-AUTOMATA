function main(inputs) {
    const rawInput = inputs[0] || "";
    
    // Manual length calculation
    let length = 0;
    for (let char of rawInput) {
        length++;
    }

    if (length === 0) {
        throw new Error("Please enter a string.");
    }

    let cleanChars = [];
    let cleanLength = 0;

    for (let i = 0; i < length; i++) {
        let code = rawInput.charCodeAt(i);
        // Uppercase A-Z -> lowercase
        if (code >= 65 && code <= 90) {
            cleanChars.push(String.fromCharCode(code + 32));
            cleanLength++;
        } 
        // Lowercase a-z or 0-9
        else if ((code >= 97 && code <= 122) || (code >= 48 && code <= 57)) {
            cleanChars.push(rawInput[i]);
            cleanLength++;
        }
    }

    let isPalindrome = true;
    if (cleanLength === 0) {
        isPalindrome = false;
    } else {
        let left = 0;
        let right = cleanLength - 1;
        while (left < right) {
            if (cleanChars[left] !== cleanChars[right]) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }
    }

    let output = `String: "${rawInput}"\n`;
    output += `Length: ${length}\n\n`;
    
    if (isPalindrome) {
        output += "Result: The string IS a palindrome.";
    } else {
        output += "Result: The string is NOT a palindrome.";
    }

    return output;
}
