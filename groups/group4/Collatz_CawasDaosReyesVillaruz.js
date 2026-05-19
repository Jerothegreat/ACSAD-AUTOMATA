function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();

  if (raw === "") {
    throw new Error("Please enter the initial value.");
  }

  const value = Number.parseInt(raw, 10);

  if (Number.isNaN(value)) {
    throw new Error("Input must be a whole integer.");
  }

  if (value <= 0) {
    throw new Error("Input must be greater than zero.");
  }

  if (value % 2 === 0) {
    throw new Error("Input must be an odd integer.");
  }

  let n = value;
  const terms = [];

  while (n !== 1) {
    terms.push(String(n));

    if (n % 2 === 0) {
      n = Math.floor(n / 2);
    } else {
      n = 3 * n + 1;
    }

    if (!Number.isSafeInteger(n) || n < 0) {
      throw new Error("Computation overflowed JavaScript safe integer range.");
    }
  }

  terms.push("1");

  return [
    "This program will find all the terms of the Collatz sequence.",
    `Input the initial value: ${value}`,
    `The Collatz sequence are: ${terms.join(", ")}`
  ].join("\n");
}
