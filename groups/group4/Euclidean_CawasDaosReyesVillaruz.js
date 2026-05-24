function main(inputs) {
  const rawA = String(inputs[0] ?? "").trim();
  const rawB = String(inputs[1] ?? "").trim();

  if (!rawA || !rawB) {
    throw new Error("Please complete all required inputs.");
  }

  const a = Number.parseInt(rawA, 10);
  const b = Number.parseInt(rawB, 10);

  if (Number.isNaN(a) || Number.isNaN(b)) {
    throw new Error("Both inputs must be whole integers.");
  }

  if (a <= 0 || b <= 0) {
    throw new Error("Both integers must be greater than zero.");
  }

  let m = Math.max(a, b);
  let n = Math.min(a, b);
  const originalM = m;
  const originalN = n;
  const lines = ["SOLUTION:"];

  while (n !== 0) {
    const q = Math.floor(m / n);
    const r = m % n;

    if (r !== 0) {
      lines.push(`${m} = ${n}(${q}) + ${r}`);
    } else {
      lines.push(`${m} = ${n}(${q})`);
    }

    m = n;
    n = r;
  }

  const gcd = m;
  const lcm = (originalM / gcd) * originalN;

  lines.push("");
  lines.push(`The integers are ${originalM} and ${originalN}`);
  lines.push(`The Greatest Common Divisor of ${originalM} and ${originalN} is ${gcd}.`);
  lines.push(`The Least Common Multiple of ${originalM} and ${originalN} is ${lcm}.`);

  return lines.join("\n");
}
