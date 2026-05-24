function parseInput(input) {
    if (!input) throw new Error("Field is empty. Enter a positive integer.");
    if (input.includes(".")) throw new Error("Input must be a positive integer.");
    if (!/^[+-]?\d+$/.test(input)) throw new Error("Input must be a positive integer.");

    const number = parseInt(input, 10);
    
    if (number <= 0) throw new Error("Input must be a positive integer.");

    return number;
}


class EuclidianAlgoLogic {
    constructor(a, b) {
        this.num1 = a;
        this.num2 = b;
        this.steps = [];
        this.gcd = 0;
        this.lcm = a * b; 
    }

    calculate() {
        let dividend = Math.max(this.num1, this.num2);
        let divisor = Math.min(this.num1, this.num2);

        while (divisor !== 0) {
            let temp = dividend;
            let quotient = 0;

            while (temp >= divisor) {
                temp -= divisor;
                quotient++;
            }

            let remainder = temp;

            if (remainder !== 0) {
                this.steps.push(`${dividend.toLocaleString()} = ${divisor.toLocaleString()} (${quotient.toLocaleString()}) + ${remainder.toLocaleString()}`);
            } else {
                this.steps.push(`${dividend.toLocaleString()} = ${divisor.toLocaleString()} (${quotient.toLocaleString()})`);
            }

            dividend = divisor;
            divisor = remainder;
        }

        this.gcd = dividend;
        this.lcm = this.lcm / this.gcd;
    }

    buildString() {
        return [
            "SOLUTION:",
            ...this.steps,
            "",
            `The integers are ${this.num1.toLocaleString()} and ${this.num2.toLocaleString()}`,
            `The greatest common divisor is ${this.gcd.toLocaleString()}`,
            `The least common multiple is ${this.lcm.toLocaleString()}`
        ].join("\n");
    }
}

function main(inputs) {
    const number1 = parseInput((inputs[0] ?? "").trim());
    const number2 = parseInput((inputs[1] ?? "").trim());

    const logic = new EuclidianAlgoLogic(number1, number2);
    logic.calculate();

    return logic.buildString();
}
