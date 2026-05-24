const Tribonacci = (() => {
  const MIN_TERMS = 3;

  function validate(input) {
    const trimmed = String(input ?? "").trim();

    if (trimmed === "") {
      return { ok: false, message: "Please enter a value before calculating." };
    }

    if (!/^\d+$/.test(trimmed)) {
      return { ok: false, message: "Invalid input — please enter a whole number." };
    }

    const value = Number(trimmed);

    if (!Number.isSafeInteger(value)) {
      return {
        ok: false,
        message: "Invalid input — the number of terms is too large to generate safely.",
      };
    }

    if (value <= 0) {
      return { ok: false, message: "Invalid input — please enter a positive integer." };
    }

    if (value <= MIN_TERMS) {
      return {
        ok: false,
        message: `Invalid input — please enter an integer greater than ${MIN_TERMS}.`,
      };
    }

    return { ok: true, message: "", value };
  }

  function compute(n) {
    let first = 0n;
    let second = 0n;
    let third = 1n;

    for (let i = 0; i < n; i += 1) {
      [first, second, third] = [second, third, first + second + third];
    }

    return first;
  }

  function getSequence(terms) {
    const sequence = [];
    let first = 0n;
    let second = 0n;
    let third = 1n;

    for (let i = 0; i < terms; i += 1) {
      sequence.push(first);
      [first, second, third] = [second, third, first + second + third];
    }

    return sequence;
  }

  return { MIN_TERMS, validate, compute, getSequence };
})();

function main(inputs) {
  const rawValue = inputs[0];
  const validation = Tribonacci.validate(rawValue);

  if (!validation.ok) {
    throw new Error(validation.message);
  }

  const terms = validation.value;
  const sequence = Tribonacci.getSequence(terms).map((value) => value.toString());

  return [
    "Tribonacci sequence:",
    sequence.join(", "),
    "",
    `Number of terms: ${terms}`,
  ].join("\n");
}
