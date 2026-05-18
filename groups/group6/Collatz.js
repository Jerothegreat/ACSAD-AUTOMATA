function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();

  if (raw === "") {
    throw new Error("Please enter a starting number.");
  }

  const n = parseInt(raw, 10);

  if (isNaN(n) || n <= 0) {
    throw new Error("Input must be a positive integer.");
  }

  if (n % 2 === 0) {
    throw new Error("Input must be an odd integer.");
  }

  let num = n;
  const seq = [];

  while (num !== 1) {
    seq.push(num);
    num = num % 2 === 1 ? 3 * num + 1 : num / 2;
  }
  seq.push(1);

  return (
    `This program will find all the terms of the Collatz sequence.\n` +
    `\n` +
    `Starting value : ${n}\n` +
    `Total terms    : ${seq.length}\n` +
    `\n` +
    `The Collatz sequence are:\n${seq.join(", ")}`
  );
}