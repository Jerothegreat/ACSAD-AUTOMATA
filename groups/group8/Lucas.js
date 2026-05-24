function parseInput(input) {
    if (!input) throw new Error("Field is empty. Enter a positive integer.");
    if (input.includes(".")) throw new Error("Input must be a positive integer.");
    if (!/^[+-]?\d+$/.test(input)) throw new Error("Input must be a positive integer.");

    const number = parseInt(input, 10);
    if (number <= 0) throw new Error("Input must be a positive integer.");
    if (number <= 2) throw new Error("Input must be greater than 2.");
    return number;
}

function generateLucas(n) {
    if (n <= 0) return "";

    let lucas = [];
    lucas[0] = 2n;

    if (n > 1) lucas[1] = 1n;

    for (let i = 2; i < n; i++) {
        lucas[i] = lucas[i - 1] + lucas[i - 2];
    }

    let sequence = [];
    for (let i = 0; i < n; i++) {
        sequence.push(lucas[i].toLocaleString());
    }

    return `The Lucas numbers are: ${sequence.join(", ")}`;
}

function main(inputs) {
    const number = parseInput(String(inputs[0]));
    return generateLucas(number);

}
