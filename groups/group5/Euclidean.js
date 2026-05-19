function main(inputs) {

    // large or smol?
    const a = Math.max(Number(inputs[0]), Number(inputs[1]));
    const b = Math.min(Number(inputs[0]), Number(inputs[1]));

    if (!Number.isInteger(a) || a <= 0 || !Number.isInteger(b) || b <= 0) {
        throw new Error("Inputs must be valid positive integers.");
    }

    let x = a;
    let y = b;
    let steps = "\nSOLUTION:\n";

    // loop until remainder is 0
    while (y !== 0) {
        let q = Math.floor(x / y);
        let r = x % y;
        
        if (r === 0) {
            steps += `${x} = ${y} (${q})\n`;
        } else {
            steps += `${x} = ${y} (${q}) + ${r}\n`;
        }

        // shift
        x = y;
        y = r;
    }

    const gcd = x;
    const lcm = (a * b) / gcd; // calc lcm using gcd, lcm & og #

    return `${steps}\nThe integers are ${a} and ${b}\n` +
           `The greatest common divisor of ${a} and ${b} is ${gcd}\n` +
           `The least common multiple of ${a} and ${b} is ${lcm}`;
}