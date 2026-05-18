function main(inputs) {
    const rawA = String(inputs[0] ?? "").trim();
    const rawB = String(inputs[1] ?? "").trim();

    if (!rawA || !rawB) throw new Error("Please complete all required inputs.");
    if (rawA.includes(".") || rawB.includes(".")) throw new Error("Decimals are not allowed. Please enter a whole number.");
    if (!/^[+-]?\d+$/.test(rawA) || !/^[+-]?\d+$/.test(rawB)) throw new Error("Invalid input. No letters or symbols allowed.");

    const a = parseInt(rawA, 10);
    const b = parseInt(rawB, 10);

    if (a <= 0 || b <= 0) throw new Error("Only positive integers (greater than 0) are accepted.");

    let m = Math.max(a, b);
    let n = Math.min(a, b);
    const originalM = m;
    const originalN = n;

    let output = "";
    let gcd = n;

    while (true) {
        const q = Math.floor(m / n);
        const r = m % n;

        if (r === 0) {
            output += `${m} = ${n}(${q})\n`;
            gcd = n;
            break;
        } else {
            output += `${m} = ${n}(${q}) + ${r}\n`;
        }

        m = n;
        n = r;
    }

    const lcm = (originalM * originalN) / gcd;

    output += `\nThe integers are ${originalM} and ${originalN}\n`;
    output += `The greatest common divisor of ${originalM} and ${originalN} is ${gcd}\n`;
    output += `The least common multiple of ${originalM} and ${originalN} is ${lcm}.`;

    return output;
}
