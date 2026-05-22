function parseInput(input) {
    
    if (!input) throw new Error("Field is empty. Enter a positive integer.");
    if (input.includes(".")) throw new Error("Input must be a positive integer.");
    if (!/^[+-]?\d+$/.test(input)) throw new Error("Input must be a positive integer.");

    let number = parseInt(input, 10);

    if (number <= 0) throw new Error("Input must be a positive integer.");

    return number;
}

function buildString(dividend, divisor, quotient, remainder) {

    let formattedString = ["SOLUTION:"];
    formattedString.push(`${dividend} = ${divisor}(${quotient}) + ${remainder}`);
    formattedString.push("");
    formattedString.push(`The dividend is ${dividend.toLocaleString()}`);
    formattedString.push(`The divisor is ${divisor.toLocaleString()}`);
    formattedString.push(`The quotient is ${quotient.toLocaleString()} and the remainder is ${remainder}`);

    return formattedString.join("\n");
}


function main(inputs) {
    const number1 = parseInput((inputs[0] ?? "").trim());
    const number2 = parseInput((inputs[1] ?? "").trim());

    let dividend = number1;
    let divisor = number2;

    if (number1 < number2) {
        dividend = number2;
        divisor = number1;
    }

    let quotient = 0;
    let remainder = dividend;

    while (remainder >= divisor) {
        remainder -= divisor;
        quotient++;
    }

    return buildString(dividend, divisor, quotient, remainder);
}
