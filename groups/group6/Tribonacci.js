function tribonacci(n) {
  if (n === 0 || n === 1) return 0;
  if (n === 2) return 1;
  return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
}

function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();

  if (raw === "") {
    throw new Error("Please enter the number of terms.");
  }

  const terms = parseInt(raw, 10);

  if (isNaN(terms) || !Number.isInteger(terms)) {
    throw new Error("Input must be a whole number.");
  }

  if (terms <= 3) {
    throw new Error("Number of terms must be greater than 3.");
  }

  if (terms > 25) {
    throw new Error("Please enter 25 or fewer terms.");
  }

  const seq = [];
  for (let i = 0; i < terms; i++) {
    seq.push(tribonacci(i));
  }

  return (
    `This program will find all the terms of the Tribonacci numbers.\n` +
    `\n` +
    `Number of terms: ${terms}\n` +
    `\n` +
    `The Tribonacci numbers are:\n${seq.join(", ")}`
  );
}