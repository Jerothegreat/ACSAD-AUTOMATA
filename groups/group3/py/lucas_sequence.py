def lucas_sequence():
    print("This program will generate the Lucas sequence.")
    print("Note: Positive n generates L(0) to L(n-1), negative n generates L(n) to L(-1)")
    
    try:
        n = int(input("Enter the number of terms (can be negative): "))
        
        if n == 0:
            print("INVALID OUTPUT: Number of terms cannot be zero")
            return
        
        sequence = []
        
        if n > 0:
            # Positive: Generate L(0) to L(n-1)
            a, b = 2, 1
            for _ in range(n):
                sequence.append(a)
                a, b = b, a + b
        else:
            # Negative: Generate L(n) to L(-1)
            # For negative Lucas: L(-n) = (-1)^n * L(n)
            a, b = 2, 1
            for _ in range(abs(n)):
                sequence.append(a)
                a, b = b - a, a
            sequence.reverse()
        
        print(f"The Lucas sequence: {', '.join(map(str, sequence))}")
    
    except ValueError:
        print("INVALID OUTPUT")

if __name__ == "__main__":
    lucas_sequence()
