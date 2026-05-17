function calculateEuclidean(m, n) {
  const origM = m;
  const origN = n;
  const lines = ["", "SOLUTION:"];
  let gcd;

  while (true) {
    const q = Math.floor(m / n);
    const r = m % n;

    if (r === 0) {
      lines.push(`${m} = ${n} (${q})`);
      gcd = n;
      break;
    }

    lines.push(`${m} = ${n} (${q}) + ${r}`);
    m = n;
    n = r;
  }

  const lcm = (origM * origN) / gcd;

  lines.push("");
  lines.push(`The integers are ${origM} and ${origN}`);
  lines.push(`The greatest common divisor of ${origM} and ${origN} is ${gcd}`);
  lines.push(`The least common multiple of ${origM} and ${origN} is ${lcm}`);

  return lines.join("\n");
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

  return calculateEuclidean(m, n);
}
