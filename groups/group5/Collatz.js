function main(inputs) {
    const start = Number(inputs[0]);

    // validates for positive odd integer
    if (!Number.isInteger(start) || start <= 0 || start % 2 === 0) {
        throw new Error("Error: You must provide a positive odd integer.");
    }

    let current = start;
    let maxVal = start;
    const arr = [current];

    // continue gen until sequence reaches 1
    while (current > 1) {
        if (current % 2 === 0) {
            // half it if even
            current = current / 2;
        } else {
            current = (3 * current) + 1;
            // if odd, x by 3 & + 1
        }
        if (current > maxVal) maxVal = current; //update peak if current is larger
        arr.push(current);
    }

    return `Collatz sequence for ${start}:\n` +
           `${arr.join(", ")}\n\n` +
           `Terms: ${arr.length}\n` +
           `Steps: ${arr.length - 1}\n` +
           `Peak value: ${maxVal}\n` +
           `Initial value: ${start}`;
}