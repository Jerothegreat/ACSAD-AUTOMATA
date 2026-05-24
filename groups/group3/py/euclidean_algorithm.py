def get_positive_integer(prompt):
    while True:
        raw = input(prompt).strip()
        if '.' in raw:
            print("Decimals are not allowed. Please enter a whole number.")
        elif not raw.lstrip('+').isdigit():
            print("Invalid input. Please enter a positive integer.")
        else:
            value = int(raw)
            if value <= 0:
                print("Please enter a positive integer greater than zero.")
            else:
                return value

def euclidean_algorithm():
    a = get_positive_integer("Enter the first integer: ")
    b = get_positive_integer("Enter the second integer: ")

    m = max(a, b)
    n = min(a, b)

    print("\nSOLUTION:")

    dividend, divisor = m, n
    while True:
        q = dividend // divisor
        r = dividend % divisor
        if r == 0:
            print(f"{dividend} = {divisor}({q})")
            gcd = divisor
            break
        else:
            print(f"{dividend} = {divisor}({q}) + {r}")
        dividend, divisor = divisor, r

    lcm = (m * n) // gcd

    print(f"\nThe integers are {m:,} and {n:,}")
    print(f"The greatest common divisor of {m:,} and {n:,} is {gcd:,}")
    print(f"The least common multiple of {m:,} and {n:,} is {lcm:,}.")

euclidean_algorithm()