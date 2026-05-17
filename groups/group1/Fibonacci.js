const Fibonacci = (() => {
  const MIN_TERMS = 2;

  function validate(input) {
    const trimmed = String(input ?? "").trim();

    if (trimmed === "") {
      return { ok: false, message: "Please enter a value before calculating." };
    }

    if (!/^\d+$/.test(trimmed)) {
      return { ok: false, message: "Invalid input — please enter a whole number." };
    }

    const value = Number.parseInt(trimmed, 10);

    if (value <= 0) {
      return { ok: false, message: "Invalid input — please enter a positive integer." };
    }

    if (value <= MIN_TERMS) {
      return {
        ok: false,
        message: `Invalid input — please enter an integer greater than ${MIN_TERMS}.`,
      };
    }

    return { ok: true, message: "" };
  }

  function compute(n) {
    if (n === 0) return 0n;
    if (n === 1) return 1n;
    return compute(n - 1) + compute(n - 2);
  }

  function getSequence(terms) {
    const sequence = [];

    for (let i = 0; i < terms; i += 1) {
      sequence.push(compute(i));
    }

    return sequence;
  }

  return { MIN_TERMS, validate, compute, getSequence };
})();

function main(inputs) {
  const rawValue = inputs[0];
  const validation = Fibonacci.validate(rawValue);

  if (!validation.ok) {
    throw new Error(validation.message);
  }

  const terms = Number.parseInt(String(rawValue).trim(), 10);
  const sequence = Fibonacci.getSequence(terms).map((value) => value.toString());

  return [
    "Fibonacci sequence:",
    sequence.join(", "),
    "",
    `Number of terms: ${terms}`,
  ].join("\n");
}
