function getCollatzSequence(n) {
  const sequence = [];
  let current = n;

  while (current !== 1) {
    sequence.push(current);
    current = current % 2 !== 0 ? (3 * current) + 1 : Math.floor(current / 2);
  }

  sequence.push(1);
  return sequence;
}

function parsePositiveOddInt(rawValue) {
  const trimmed = String(rawValue ?? "").trim();

  if (!trimmed) {
    throw new Error("Please enter a value before calculating.");
  }

  if (!/^-?\d+$/.test(trimmed)) {
    throw new Error("Invalid input — please enter a valid integer.");
  }

  const value = Number.parseInt(trimmed, 10);

  if (value <= 0 || value % 2 === 0) {
    throw new Error("Invalid input — please enter a positive odd integer.");
  }

  return value;
}

function main(inputs) {
  const startingNumber = parsePositiveOddInt(inputs[0]);
  const sequence = getCollatzSequence(startingNumber);
  const peak = Math.max(...sequence);

  return [
    `Collatz sequence for ${startingNumber}:`,
    sequence.join(", "),
    "",
    `Terms: ${sequence.length}`,
    `Steps: ${sequence.length - 1}`,
    `Peak value: ${peak}`,
    `Initial value: ${startingNumber}`,
  ].join("\n");
}
