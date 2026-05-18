function main(inputs) {
    const rawA = String(inputs[0] ?? "").trim();
    const rawB = String(inputs[1] ?? "").trim();

    if (!rawA || !rawB) throw new Error("Please complete all required inputs.");
    if (rawA.includes(".") || rawB.includes(".")) throw new Error("Decimals are not allowed. Please enter a whole number.");
    if (!/^[+-]?\d+$/.test(rawA) || !/^[+-]?\d+$/.test(rawB)) throw new Error("Invalid input. No letters or symbols allowed.");

    const a = parseInt(rawA, 10);
    const b = parseInt(rawB, 10);

    if (a <= 0 || b <= 0) throw new Error("Only positive integers (greater than 0) are accepted.");

    const m = Math.max(a, b);
    const n = Math.min(a, b);

    const q = Math.floor(m / n);
    const r = m % n;

    return `${m} = ${n}(${q}) + ${r}\nThe dividend is ${m}\nThe divisor is ${n}\nThe quotient is ${q} and the remainder is ${r}`;
}
