def fibonacci_sequence():
    print("This program will generate the Fibonacci sequence.")
    print("Note: Positive n generates F(0) to F(n-1), negative n generates F(n) to F(-1)")
    
    try:
        n = int(input("Enter the number of terms (can be negative): "))
        
        if n == 0:
            print("INVALID OUTPUT: Number of terms cannot be zero")
            return
        
        sequence = []
        
        if n > 0:
            # Positive: Generate F(0) to F(n-1)
            a, b = 0, 1
            for _ in range(n):
                sequence.append(a)
                a, b = b, a + b
        else:
            # Negative: Generate F(n) to F(-1)
            # For negative Fibonacci: F(-n) = (-1)^(n+1) * F(n)
            a, b = 0, 1
            for _ in range(abs(n)):
                sequence.append(a)
                a, b = b - a, a
            sequence.reverse()
        
        print(f"The Fibonacci sequence: {', '.join(map(str, sequence))}")
    
    except ValueError:
        print("INVALID OUTPUT")

if __name__ == "__main__":
    fibonacci_sequence()
