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
    
    const m = Math.max(a, b);
    const n = Math.min(a, b);
    
    // Euclidean algorithm with steps
    const steps = [];
    let dividend = m;
    let divisor = n;
    let gcd;
    
    while (true) {
        const q = Math.floor(dividend / divisor);
        const r = dividend % divisor;
        
        if (r === 0) {
            steps.push(`${dividend} = ${divisor}(${q})`);
            gcd = divisor;
            break;
        } else {
            steps.push(`${dividend} = ${divisor}(${q}) + ${r}`);
        }
        
        dividend = divisor;
        divisor = r;
    }
    
    const lcm = Math.floor((m * n) / gcd);
    
    // Format output
    const stepsText = steps.join('\n');
    
    return `✅ SOLUTION

${stepsText}

The integers are: ${m.toLocaleString()} and ${n.toLocaleString()}
The greatest common divisor (GCD) of ${m.toLocaleString()} and ${n.toLocaleString()} is: ${gcd.toLocaleString()}
The least common multiple (LCM) of ${m.toLocaleString()} and ${n.toLocaleString()} is: ${lcm.toLocaleString()}`;
}
