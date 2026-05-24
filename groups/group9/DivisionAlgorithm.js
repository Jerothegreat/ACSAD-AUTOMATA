function main(inputs) {
  const raw1 = String(inputs[0] ?? "").trim();
  const raw2 = String(inputs[1] ?? "").trim();
  if (!raw1 || !raw2)
    throw new Error("INVALID OUTPUT\nPlease complete all required inputs.");
  const first = parseInt(raw1, 10);
  const second = parseInt(raw2, 10);
  if (isNaN(first) || !Number.isInteger(Number(raw1)))
    throw new Error("INVALID OUTPUT\nFirst input must be a whole number.");
  if (isNaN(second) || !Number.isInteger(Number(raw2)))
    throw new Error("INVALID OUTPUT\nSecond input must be a whole number.");
  if (first <= 0)
    throw new Error("INVALID OUTPUT\nFirst input must be a positive integer.");
  if (second <= 0)
    throw new Error("INVALID OUTPUT\nSecond input must be a positive integer.");

  const m = Math.max(first, second);
  const n = Math.min(first, second);
  const q = Math.floor(m / n);
  const r = m % n;

  return [
    "SOLUTION:",
    `${m} = ${n} (${q}) + ${r}`,
    ``,
    `The dividend is ${m}`,
    `The divisor is ${n}`,
    `The quotient is ${q} and the remainder is ${r}`
  ].join("\n");
}