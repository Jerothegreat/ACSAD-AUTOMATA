function main(inputs) {
    const input1 = String(inputs[0] ?? "").trim();
    const input2 = String(inputs[1] ?? "").trim();
    
    // Validate first integer
    if (input1 === '') {
        throw new Error('Please enter the first integer');
    }
    
    if (input1.includes('.')) {
        throw new Error('Decimals are not allowed. Please enter a whole number.');
    }
    
    const a = parseInt(input1);
    
    if (isNaN(a)) {
        throw new Error('Invalid input. Please enter a positive integer.');
    }
    
    if (a <= 0) {
        throw new Error('Please enter a positive integer greater than zero.');
    }
    
    // Validate second integer
    if (input2 === '') {
        throw new Error('Please enter the second integer');
    }
    
    if (input2.includes('.')) {
        throw new Error('Decimals are not allowed. Please enter a whole number.');
    }
    
    const b = parseInt(input2);
    
    if (isNaN(b)) {
        throw new Error('Invalid input. Please enter a positive integer.');
    }
    
    if (b <= 0) {
        throw new Error('Please enter a positive integer greater than zero.');
    }
    
    // Calculate using division algorithm
    const m = Math.max(a, b);
    const n = Math.min(a, b);
    
    const q = Math.floor(m / n);
    const r = m % n;
    
    // Return formatted result
    return `✅ SOLUTION

Formula: ${m} = ${n}(${q}) + ${r}

The dividend is: ${m}
The divisor is: ${n}
The quotient is: ${q}
The remainder is: ${r}`;
}
