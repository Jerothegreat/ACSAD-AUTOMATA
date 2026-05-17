function calculateDivAlgo(m, n) {
  const q = Math.floor(m / n);
  const r = m % n;

  return [
    "",
    "SOLUTION:",
    `${m} = ${n} (${q}) + ${r}`,
    "",
    `The dividend is ${m}`,
    `The divisor is ${n}`,
    `The quotient is ${q} and the remainder is ${r}`,
  ].join("\n");
}

function parsePositiveInt(rawValue) {
  const trimmed = String(rawValue ?? "").trim();

  if (!/^-?\d+$/.test(trimmed)) {
    throw new Error("Invalid input. Please enter an integer.");
  }

  const value = Number.parseInt(trimmed, 10);

  if (value <= 0) {
    throw new Error("Invalid input. Please enter a positive integer.");
  }

  return value;
}

function main(inputs) {
  const firstInput = parsePositiveInt(inputs[0]);
  const secondInput = parsePositiveInt(inputs[1]);

  const m = Math.max(firstInput, secondInput);
  const n = Math.min(firstInput, secondInput);

  return calculateDivAlgo(m, n);
}
