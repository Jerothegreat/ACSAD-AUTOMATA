function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();
  if (raw === "") throw new Error("INVALID OUTPUT\nPlease enter a value.");
  const n = parseInt(raw, 10);
  if (isNaN(n) || !Number.isInteger(Number(raw)))
    throw new Error("INVALID OUTPUT\nPlease enter a whole number.");
  if (n <= 0 || n % 2 === 0)
    throw new Error("INVALID OUTPUT\nInput must be a positive odd integer.");

  let current = n;
  const terms = [];
  while (current !== 1) {
    terms.push(current);
    current = current % 2 === 0 ? current / 2 : 3 * current + 1;
  }
  terms.push(1);

  let output = "The Collatz sequence are: ";
  terms.forEach((val, i) => {
    output += val;
    if (i < terms.length - 1) output += ", ";
    if ((i + 1) % 10 === 0 && i < terms.length - 1) output += "\n";
  });
  return output;
}