function fibonacci(n) {
  if (n === 0) return 0;
  if (n === 1) return 1;
  return fibonacci(n - 1) + fibonacci(n - 2);
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

  if (terms <= 2) {
    throw new Error("Number of terms must be greater than 2.");
  }

  if (terms > 30) {
    throw new Error("Please enter 30 or fewer terms.");
  }

  const seq = [];
  for (let i = 0; i < terms; i++) {
    seq.push(fibonacci(i));
  }

  return (
    `This program will find all the terms of the Fibonacci numbers.\n` +
    `\n` +
    `Number of terms: ${terms}\n` +
    `\n` +
    `The Fibonacci numbers are:\n${seq.join(", ")}`
  );
}