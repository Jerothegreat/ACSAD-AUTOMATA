const Lucas = (() => {
  const MIN_TERMS = 2;

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
    let previous = 2n;
    let current = 1n;

    for (let i = 0; i < n; i += 1) {
      [previous, current] = [current, previous + current];
    }

    return previous;
  }

  function getSequence(terms) {
    const sequence = [];
    let previous = 2n;
    let current = 1n;

    for (let i = 0; i < terms; i += 1) {
      sequence.push(previous);
      [previous, current] = [current, previous + current];
    }

    return sequence;
  }

  return { MIN_TERMS, validate, compute, getSequence };
})();

function main(inputs) {
  const rawValue = inputs[0];
  const validation = Lucas.validate(rawValue);

  if (!validation.ok) {
    throw new Error(validation.message);
  }

  const terms = validation.value;
  const sequence = Lucas.getSequence(terms).map((value) => value.toString());

  return [
    "Lucas sequence:",
    sequence.join(", "),
    "",
    `Number of terms: ${terms}`,
  ].join("\n");
}
