function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();
  if (raw === "") throw new Error("INVALID OUTPUT\nPlease enter a value.");
  const n = parseInt(raw, 10);
  if (isNaN(n) || !Number.isInteger(Number(raw)))
    throw new Error("INVALID OUTPUT\nPlease enter a whole number.");
  if (n <= 3)
    throw new Error("INVALID OUTPUT\nInput must be greater than 3.");

  const seq = [0, 0, 1];
  for (let i = 3; i < n; i++) seq.push(seq[i-1] + seq[i-2] + seq[i-3]);

  let output = "The Tribonacci numbers are: ";
  seq.forEach((val, i) => {
    output += val;
    if (i < seq.length - 1) output += ", ";
    if ((i + 1) % 10 === 0 && i < seq.length - 1) output += "\n";
  });
  return output;
}