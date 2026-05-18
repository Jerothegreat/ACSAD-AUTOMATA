function main(inputs) {
    return generateSequence(inputs[0], "Lucas", 2, [2n, 1n]);
}

function generateSequence(rawInput, name, minInput, initialTerms) {
    rawInput = String(rawInput ?? "").trim();
    if (!rawInput) throw new Error("Please enter a value.");
    if (rawInput.includes(".")) throw new Error("Decimals are not allowed. Please enter a whole number.");
    if (!/^\d+$/.test(rawInput)) {
        if (rawInput.startsWith("-")) throw new Error("Negative numbers are not allowed. Please enter a positive whole number.");
        throw new Error("Invalid input. Only positive whole numbers are accepted (no letters, symbols, or decimals).");
    }

    const n = parseInt(rawInput, 10);
    if (n <= minInput) throw new Error(`Input must be greater than ${minInput}. You entered: ${n}`);
    if (n > 500) throw new Error("Input exceeds the maximum allowed limit of 500 terms.");

    let seq = [...initialTerms];
    for (let i = initialTerms.length; i < n; i++) {
        let nextVal = 0n;
        for (let j = 1; j <= initialTerms.length; j++) {
            nextVal += seq[i - j];
        }
        seq.push(nextVal);
    }

    return `This program will find all the terms of the ${name} numbers.\n\tInput the number of terms: ${n}\n\tThe ${name} numbers are: ${seq.join(", ")}`;
}
