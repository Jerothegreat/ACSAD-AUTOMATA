def tribonacci_sequence():
    print("This program will generate the Tribonacci sequence.")
    print("Note: Positive n generates T(0) to T(n-1), negative n generates T(n) to T(-1)")
    
    try:
        n = int(input("Enter the number of terms (can be negative): "))
        
        if n == 0:
            print("INVALID OUTPUT: Number of terms cannot be zero")
            return
        
        sequence = []
        
        if n > 0:
            # Positive: Generate T(0) to T(n-1)
            a, b, c = 0, 1, 1
            for _ in range(n):
                sequence.append(a)
                a, b, c = b, c, a + b + c
        else:
            # Negative: Generate T(n) to T(-1)
            # For negative Tribonacci: T(n-3) = T(n) - T(n-1) - T(n-2)
            a, b, c = 0, 1, 1
            for _ in range(abs(n)):
                sequence.append(a)
                a, b, c = b - c - a, a, b
            sequence.reverse()
        
        print(f"The Tribonacci sequence: {', '.join(map(str, sequence))}")
    
    except ValueError:
        print("INVALID OUTPUT")

if __name__ == "__main__":
    tribonacci_sequence()
