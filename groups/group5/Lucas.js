function main(inputs) {
    const limit = Number(inputs[0]);

    if (!Number.isInteger(limit) || limit <= 2) { //check if limit is a valid integer >2
        throw new Error("Please enter an integer greater than 2.");
    }

    let prev2 = 2n, prev1 = 1n;
    let seq = [];

    for (let idx = 0; idx < limit; idx++) { // loop to calc
        if (idx === 0) seq.push(prev2.toString()); // store 1st
        else if (idx === 1) seq.push(prev1.toString()); // store 2nd
        else { // compute current and update prev
            let current = prev2 + prev1;
            seq.push(current.toString());
            prev2 = prev1;
            prev1 = current;
        }
    }

    return `Lucas sequence:\n${seq.join(", ")}\n\nNumber of terms: ${limit}`;
}