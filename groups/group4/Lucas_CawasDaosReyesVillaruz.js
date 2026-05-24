function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();

  if (!raw) {
    throw new Error("Please enter the number of terms.");
  }

  const n = Number.parseInt(raw, 10);

  if (Number.isNaN(n)) {
    throw new Error("Input must be a whole integer.");
  }

  if (n <= 2) {
    throw new Error("Number of terms must be greater than 2.");
  }

  const seq = [];
  let a = 2;
  let b = 1;

  for (let i = 0; i < n; i += 1) {
    if (i === 0) {
      seq.push(a);
    } else if (i === 1) {
      seq.push(b);
    } else {
      const c = a + b;

      // if (!Number.isSafeInteger(c)) {
      //   throw new Error("Computation overflowed JavaScript safe integer range.");
      // }

      seq.push(c);
      a = b;
      b = c;
    }
  }

  return [
    "This program will find all the terms of the Lucas numbers.",
    `Input the number of terms: ${n}`,
    `The Lucas numbers are: ${seq.join(", ")}`
  ].join("\n");
}
