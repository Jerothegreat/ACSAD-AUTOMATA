function main(inputs) {
  const rawFirst = String(inputs[0] ?? "").trim();
  const rawSecond = String(inputs[1] ?? "").trim();

  if (!rawFirst || !rawSecond) {
    throw new Error("Please complete all required inputs.");
  }

  const first = Number.parseInt(rawFirst, 10);
  const second = Number.parseInt(rawSecond, 10);

  if (Number.isNaN(first) || Number.isNaN(second)) {
    throw new Error("Both inputs must be whole integers.");
  }

  if (first <= 0 || second <= 0) {
    throw new Error("Both integers must be greater than zero.");
  }

  const dividend = Math.max(first, second);
  const divisor = Math.min(first, second);
  const quotient = Math.floor(dividend / divisor);
  const remainder = dividend % divisor;

  return [
    "SOLUTION:",
    `${dividend} = ${divisor}(${quotient}) + ${remainder}`,
    `The dividend is ${dividend}`,
    `The divisor is ${divisor}`,
    `The quotient is ${quotient} and the remainder is ${remainder}`
  ].join("\n");
}
