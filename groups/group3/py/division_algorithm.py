def get_positive_integer(prompt):
    while True:
        raw = input(prompt).strip()
        if '.' in raw:
            print("Decimals are not allowed. Please enter a whole number.")
        elif not raw.lstrip().lstrip('+').isdigit():
            print("Invalid input. Please enter a positive integer.")
        else:
            value = int(raw)
            if value <= 0:
                print("Please enter a positive integer greater than zero.")
            else:
                return value

def division_algorithm():
    a = get_positive_integer("Enter the first integer: ")
    b = get_positive_integer("Enter the second integer: ")

    m = max(a, b)
    n = min(a, b)

    q = m // n
    r = m % n

    print("\nSOLUTION:")
    print(f"{m} = {n}({q}) + {r}")
    print(f"The dividend is {m}")
    print(f"The divisor is {n}")
    print(f"The quotient is {q} and the remainder is {r}")

division_algorithm()