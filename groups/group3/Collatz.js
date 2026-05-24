function main(inputs) {
    const input = String(inputs[0] ?? "").trim();
    
    // Validation
    if (input === '') {
        throw new Error('Please enter a value');
    }
    
    if (input.includes('.')) {
        throw new Error('Decimals are not allowed. Please enter a whole number.');
    }
    
    const n = parseInt(input);
    
    if (isNaN(n)) {
        throw new Error('INVALID OUTPUT: Please enter a valid integer');
    }
    
    if (n <= 0) {
        throw new Error('INVALID OUTPUT: Number must be positive');
    }
    
    if (n % 2 === 0) {
        throw new Error('INVALID OUTPUT: Number must be odd');
    }
    
    // Generate Collatz sequence
    const sequence = [];
    let current = n;
    
    while (current !== 1) {
        sequence.push(current);
        if (current % 2 === 0) {
            current = Math.floor(current / 2);
        } else {
            current = 3 * current + 1;
        }
    }
    sequence.push(1);
    
    // Return formatted result
    return `✅ Collatz Sequence

Initial value: ${n}
Sequence: ${sequence.join(', ')}
Number of steps: ${sequence.length - 1}`;
}
