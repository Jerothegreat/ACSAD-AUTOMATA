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

  const originalM = Math.max(first, second);
  const originalN = Math.min(first, second);
  let a = originalM, b = originalN;
  let gcd = 1;
  const steps = [];

  while (b !== 0) {
    const q = Math.floor(a / b);
    const r = a % b;
    if (r !== 0) {
      steps.push(`${a} = ${b} (${q}) + ${r}`);
      a = b; b = r;
    } else {
      steps.push(`${a} = ${b} (${q})`);
      gcd = b; break;
    }
  }

  const lcm = (originalM * originalN) / gcd;
  return [
    "SOLUTION:",
    ...steps,
    ``,
    `The integers are ${originalM} and ${originalN}`,
    `The greatest common divisor of ${originalM} and ${originalN} is ${gcd}`,
    `The least common multiple of ${originalM} and ${originalN} is ${lcm}.`
  ].join("\n");
}