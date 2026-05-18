function main(inputs) {
    const rawInput = String(inputs[0] ?? "").trim();

    if (!rawInput) throw new Error("Please enter a value.");
    if (!/^-?\d+$/.test(rawInput)) throw new Error("Input must be a whole number (no letters, symbols, or decimals).");

    const n = Number(rawInput);
    
    if (n <= 0) throw new Error("The initial value must be a positive integer (greater than 0).");
    if (n % 2 === 0) throw new Error("The initial value must be a positive ODD integer.");

    let sequence = [];
    let current = n;
    let stepCount = 0;

    while (current !== 1) {
        sequence.push(current);
        if (current % 2 !== 0) {
            current = (3 * current) + 1;
        } else {
            current = current / 2;
        }
        stepCount++;
    }
    sequence.push(1);
    stepCount++;

    return `The Collatz sequence are: ${sequence.join(", ")}\n\nTotal steps: ${stepCount}`;
}
