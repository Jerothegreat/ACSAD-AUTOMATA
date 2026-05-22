function parseInput(input) {
    if (!input) throw new Error("Field is empty. Enter a positive integer.");
    if (input.includes(".")) throw new Error("Input must be a positive integer.");
    if (!/^[+-]?\d+$/.test(input)) throw new Error("Input must be a positive integer.");

    const number = parseInt(input);
    if (number <= 0) throw new Error("Input must be a positive integer.");
    if (number <= 2) throw new Error("Input must be greater than 2.");
    return number;
}

function generateFibonacci(n) {
    if (n <= 0) return "";

    let fib = [];
    fib[0] = 0n;
    if (n > 1) fib[1] = 1n;

    for (let i = 2; i < n; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }

    let sequence = [];
    for (let i = 0; i < n; i++) {
        sequence.push(fib[i].toLocaleString());
    }

    return `The Fibonacci numbers are: ${sequence.join(", ")}`;
}

function main(inputs) {
    const number = parseInput(String(inputs[0]));
    return generateFibonacci(number);
}
