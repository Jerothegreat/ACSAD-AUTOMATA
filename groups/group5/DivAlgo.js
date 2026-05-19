function main(inputs) {

    const val1 = Number(inputs[0]);
    const val2 = Number(inputs[1]);

    if (!Number.isInteger(val1) || val1 <= 0 || !Number.isInteger(val2) || val2 <= 0) {
        throw new Error("Invalid input: Both inputs must be positive integers.");
    }

    const dividend = Math.max(val1, val2);
    const divisor = Math.min(val1, val2);

    // we caclulate
    const quotient = Math.floor(dividend / divisor);
    const remainder = dividend % divisor;

    // formatting and return
    let output = "\nSOLUTION:\n";
    output += `${dividend} = ${divisor} (${quotient}) + ${remainder}\n\n`;
    output += `The dividend is ${dividend}\n`;
    output += `The divisor is ${divisor}\n`;
    output += `The quotient is ${quotient} and the remainder is ${remainder}`;

    return output;
}