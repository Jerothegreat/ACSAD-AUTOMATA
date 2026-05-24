function parseInput(input) {
    if (!input) throw new Error("Field is empty. Enter a positive integer.");
    if (input.includes(".")) throw new Error("Input must be a positive integer.");
    if (!/^[+-]?\d+$/.test(input)) throw new Error("Input must be a positive integer.");

    number = parseInt(input);
    
    if (number <= 0) throw new Error("Input must be a positive integer.");
    if (number % 2 == 0) throw new Error("Input must be an odd number");

    return number;
}

function compute(number) {
    let sequence = [number];

    while (number != 1) {
        if ((number % 2) == 1) {
            number = (3 * number) + 1;
        } else number /= 2
    sequence.push(number);
    }

    return `The Collatz sequence are: ${sequence.join(", ")}`;
}


function main(inputs) {
    const number = parseInput((inputs[0] ?? "").trim());
    return compute(number);
}
