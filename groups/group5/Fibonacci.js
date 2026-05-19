function main(inputs) {
    const terms = Number(inputs[0]);

    if (!Number.isInteger(terms) || terms <= 2) {
        throw new Error("Input must be a whole number greater than 2.");
    }

    let a = 0n, b = 1n;
    let result = [];

    // iterate
    for (let i = 0; i < terms; i++) {
        if (i === 0) {
            result.push(a.toString()); // push 1st
        } else if (i === 1) {
            result.push(b.toString()); //push 2nd
        } else { // compute next term & shift
            let next = a + b;
            result.push(next.toString());
            a = b;
            b = next;
        }
    }

    return `Fibonacci sequence:\n${result.join(", ")}\n\nNumber of terms: ${terms}`;
}