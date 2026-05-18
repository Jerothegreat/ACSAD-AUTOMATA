function main(inputs) {
  const rawA = String(inputs[0] ?? "").trim();
  const rawB = String(inputs[1] ?? "").trim();

  if (rawA === "" || rawB === "") {
    throw new Error("Please fill in both integers.");
  }

  const a = parseInt(rawA, 10);
  const b = parseInt(rawB, 10);

  if (isNaN(a) || isNaN(b)) {
    throw new Error("Both inputs must be positive integers.");
  }

  if (a <= 0 || b <= 0) {
    throw new Error("Both integers must be positive (greater than 0).");
  }

  let m = Math.max(a, b);
  let n = Math.min(a, b);

  let steps = "Steps (Euclidean Algorithm):\n";

  while (n !== 0) {
    const q = Math.floor(m / n);
    const r = m % n;
    steps += `  ${m} = ${n}(${q}) + ${r}\n`;
    [m, n] = [n, r];
  }

  const gcd = m;
  const lcm = (a * b) / gcd;

  return steps + `\nGCD: ${gcd}\nLCM: ${lcm.toLocaleString()}`;
}