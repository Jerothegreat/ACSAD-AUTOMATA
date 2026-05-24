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
    
    if (n === 0) {
        throw new Error('INVALID OUTPUT: Number of terms cannot be zero');
    }
    
    // Generate Fibonacci sequence
    const sequence = [];
    
    if (n > 0) {
        // Positive: F(0) to F(n-1)
        let a = 0, b = 1;
        for (let i = 0; i < n; i++) {
            sequence.push(a);
            [a, b] = [b, a + b];
        }
    } else {
        // Negative: F(n) to F(-1)
        let a = 0, b = 1;
        for (let i = 0; i < Math.abs(n); i++) {
            sequence.push(a);
            [a, b] = [b - a, a];
        }
        sequence.reverse();
    }
    
    // Return formatted result
    const rangeText = n > 0 ? `F(0) to F(${n-1})` : `F(${n}) to F(-1)`;
    
    return `✅ Fibonacci Sequence

Number of terms: ${Math.abs(n)}
Range: ${rangeText}
Sequence: ${sequence.join(', ')}`;
}
