function main(inputs) {
  const rawA = String(inputs[0] ?? "").trim();
  const rawB = String(inputs[1] ?? "").trim();

  if (rawA === "" || rawB === "") {
    throw new Error("Please fill in both Dividend and Divisor.");
  }

  const a = parseInt(rawA, 10);
  const b = parseInt(rawB, 10);

  if (isNaN(a) || isNaN(b)) {
    throw new Error("Both inputs must be integers.");
  }

  if (b === 0) {
    throw new Error("Divisor cannot be zero.");
  }

  
  let q = Math.floor(a / b);
  let r = a - b * q;
  if (r < 0) {
    q -= Math.sign(b);
    r = a - b * q;
  }

  return (
    `Division Algorithm: a = b · q + r,  where 0 ≤ r < |b|\n` +
    `\n` +
    `Dividend  (a) : ${a}\n` +
    `Divisor   (b) : ${b}\n` +
    `\n` +
    `Quotient  (q) : ${q}\n` +
    `Remainder (r) : ${r}\n` +
    `\n` +
    `Verification  : ${a} = ${b}(${q}) + ${r}  →  ${b * q + r} = ${a}  ✓`
  );
}