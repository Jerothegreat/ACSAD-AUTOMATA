def palindrome_test():
    print("This program will check if a string is a palindrome.")
    
    try:
        text = input("Enter a string to check: ").strip()
        
        if not text:
            print("INVALID OUTPUT: Please enter a non-empty string")
            return
        
        # Remove spaces and convert to lowercase for comparison
        cleaned_text = text.replace(" ", "").lower()
        
        # Check if palindrome
        is_palindrome = cleaned_text == cleaned_text[::-1]
        
        print(f"\nOriginal text: {text}")
        print(f"Cleaned text: {cleaned_text}")
        
        if is_palindrome:
            print(f"[YES] '{text}' IS a palindrome!")
        else:
            print(f"[NO] '{text}' is NOT a palindrome.")
            print(f"Reversed: {cleaned_text[::-1]}")
    
    except Exception as e:
        print(f"INVALID OUTPUT: {e}")

if __name__ == "__main__":
    palindrome_test()
