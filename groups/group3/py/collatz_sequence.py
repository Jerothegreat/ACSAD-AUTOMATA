def collatz_sequence():
    print("This program will find all the terms of the Collatz sequence.")

    try:
        n = int(input("Input the initial value: "))

        if n <= 0 or n % 2 == 0:
            print("INVALID OUTPUT")
            return

        sequence = []
        current = n

        while current != 1:
            sequence.append(current)
            if current % 2 == 0:
                current = current // 2
            else:
                current = 3 * current + 1

        sequence.append(1)

        print(f"The Collatz sequence are: {', '.join(map(str, sequence))}")

    except ValueError:
        print("INVALID OUTPUT")

if __name__ == "__main__":
    collatz_sequence()