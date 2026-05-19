function main(inputs) {
    const count = Number(inputs[0]);

    if (!Number.isInteger(count) || count <= 3) {
        throw new Error("Tribonacci requires an input integer greater than 3.");
    }

    let t0 = 0n, t1 = 0n, t2 = 1n;
    const items = [];

    for (let k = 0; k < count; k++) { // calc and collect
        if (k === 0) items.push(t0.toString());
        else if (k === 1) items.push(t1.toString());
        else if (k === 2) items.push(t2.toString());
        else { // sum prev 3 terms
            let tNext = t0 + t1 + t2;
            items.push(tNext.toString());
            t0 = t1; // shift fwd
            t1 = t2;
            t2 = tNext;
        }
    }

    return `Tribonacci sequence:\n${items.join(", ")}\n\nNumber of terms: ${count}`;
}