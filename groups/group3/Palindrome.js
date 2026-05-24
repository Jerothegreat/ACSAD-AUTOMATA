function main(inputs) {
    const text = String(inputs[0] ?? "").trim();
    
    // Validation
    if (text === '') {
        throw new Error('Please enter a text to check');
    }
    
    // Clean the text: ignore spaces, case, and punctuation (default behavior)
    let cleanedText = text;
    cleanedText = cleanedText.replace(/\s+/g, ''); // Remove spaces
    cleanedText = cleanedText.replace(/[^\w\s]|_/g, ''); // Remove punctuation
    cleanedText = cleanedText.toLowerCase(); // Convert to lowercase
    
    // Check if palindrome
    const reversedText = cleanedText.split('').reverse().join('');
    const isPalindrome = cleanedText === reversedText;
    
    // Return formatted result
    if (isPalindrome) {
        return `✅ YES! It's a Palindrome!

Original text: ${text}
Processed text: ${cleanedText}
Reversed: ${reversedText}

✓ The text reads the same forwards and backwards!`;
    } else {
        return `❌ NO, It's Not a Palindrome

Original text: ${text}
Processed text: ${cleanedText}
Reversed: ${reversedText}

✗ The text does NOT read the same forwards and backwards.`;
    }
}
