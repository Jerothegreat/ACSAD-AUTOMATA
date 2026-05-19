function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();

  if (!raw) {
    throw new Error("Please enter the number of terms.");
  }

  const n = Number.parseInt(raw, 10);

  if (Number.isNaN(n)) {
    throw new Error("Input must be a whole integer.");
  }

  if (n <= 3) {
    throw new Error("Number of terms must be greater than 3.");
  }

  const seq = [];
  let a = 0;
  let b = 0;
  let c = 1;

  for (let i = 0; i < n; i += 1) {
    if (i === 0) {
      seq.push(a);
    } else if (i === 1) {
      seq.push(b);
    } else if (i === 2) {
      seq.push(c);
    } else {
      const next = a + b + c;

      // if (!Number.isSafeInteger(next)) {
      //   throw new Error("Computation overflowed JavaScript safe integer range.");
      // }

      seq.push(next);
      a = b;
      b = c;
      c = next;
    }
  }

  return [
    "This program will find all the terms of the Tribonacci numbers.",
    `Input the number of terms: ${n}`,
    `The Tribonacci numbers are: ${seq.join(", ")}`
  ].join("\n");
}
