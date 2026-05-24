function parseInput(input) {
    if (!input) throw new Error("Field is empty. Enter a positive integer.");
    if (input.includes(".")) throw new Error("Input must be a positive integer.");
    if (!/^[+-]?\d+$/.test(input)) throw new Error("Input must be a positive integer.");

    const number = parseInt(input, 10);
    if (number <= 0) throw new Error("Input must be a positive integer.");
    if (number <= 3) throw new Error("Input must be greater than 3.");
    return number;
}

function generateTribonacci(n) {
    if (n <= 0) return "";

    let trib = [];
    trib[0] = 0n;
    if (n > 1) trib[1] = 0n;
    if (n > 2) trib[2] = 1n;

    for (let i = 3; i < n; i++) {
        trib[i] = trib[i - 1] + trib[i - 2] + trib[i - 3];
    }

    let sequence = [];
    for (let i = 0; i < n; i++) {
        sequence.push(trib[i].toLocaleString());
    }

    return `The Tribonacci numbers are: ${sequence.join(", ")}`;
}

function main(inputs) {
    const number = parseInput(String(inputs[0]));
    return generateTribonacci(number);
}
