import java.util.Scanner;

public class Main {

    public static String ArabicToRoman(int input)
    {
        String result = "";
        if (input < 0 || input > 1000)
        {
            System.out.println("Roman cannot be less than 0");
            System.exit(-1);
        }

        switch (input % 10)
        {
            case 1:
                result += "I";
                break;
            case 2:
                result += "II";
                break;
            case 3:
                result += "III";
                break;
            case 4:
                result += "IV";
                break;
            case 5:
                result += "V";
                break;
            case 6:
                result += "VI";
                break;
            case 7:
                result += "VII";
                break;
            case 8:
                result += "VIII";
                break;
            case 9:
                result += "IX";
                break;
        }
        result = switch ((input % 100 - input % 10) / 10) {
            case 1 -> "X" + result;
            case 2 -> "XX" + result;
            case 3 -> "XXX" + result;
            case 4 -> "XL" + result;
            case 5 -> "L" + result;
            case 6 -> "LX" + result;
            case 7 -> "LXX" + result;
            case 8 -> "LXXX" + result;
            case 9 -> "XC" + result;
            default -> result;
        };
        result = switch ((input % 1000 - input % 100 - input % 10) / 100) {
            case 1 -> "C" + result;
            case 2 -> "CC" + result;
            case 3 -> "CCC" + result;
            case 4 -> "CD" + result;
            case 5 -> "D" + result;
            case 6 -> "DC" + result;
            case 7 -> "DCC" + result;
            case 8 -> "DCCC" + result;
            case 9 -> "CM" + result;
            default -> result;
        };
        return result;
    }
    public static int TryParseInt(String input)
    {
        try
        {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            System.out.println("ParseNotPossible");
            System.exit(-1);
            return 0;
        }
    }
    public static String CutStringTillGivenSymbol(String input3, int fromSybolIndex, String symbol)
    {
        String temp = "";
        while (!String.valueOf(input3.charAt(fromSybolIndex)).equals(symbol))
        {
            temp = temp + input3.charAt(fromSybolIndex);
            fromSybolIndex++;
            if (fromSybolIndex == input3.length())
            {
                break;
            }
        }
        return temp;
    }
    public static int Calculate(int input1,int input2, String operationCalculate) {
        switch (operationCalculate) {
            case "+":
                return input1 + input2;
            case "-":
                return input1 - input2;
            case "*":
                return input1 * input2;
            case "/":
                return input1 / input2;
        }
        System.out.println("Incorrect operation/syntax");
        System.exit(-1);
        return -1;
    }
    public static int RomanToArabic(String input)
    {
        int result = 0;
        int currentSymbol = 0;
        int lastSymbol = 0;
        for (int i = 0; input.length() > i; i++)
        {
            switch (String.valueOf(input.charAt(i)))
            {
                case "I":
                    currentSymbol = 1;
                    break;
                case"V":
                    currentSymbol = 5;
                    break;
                case"X":
                    currentSymbol = 10;
                    break;
                default:
                    System.out.println("Incorrect syntax");
                    System.exit(-1);
            }
            if (currentSymbol > lastSymbol)
            {
                result += lastSymbol*-2 + currentSymbol;
            }
            else
            {
                result += currentSymbol;
            }
            lastSymbol = currentSymbol;
        }
        return result;
    }
    public static boolean NumberIsInRange(int numberToCheck, int fromNumber, int toNumber)
    {
        return numberToCheck >= fromNumber && numberToCheck <= toNumber;
    }
    public static String calc(String input)
    {
        int num1;
        int num2;
        String number1 = "";
        String number2 = "";
        String operation = "";
        boolean isRoman;
        try
        {
            number1 = CutStringTillGivenSymbol(input, 0, " ");
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.out.println("Incorrect syntax");
            System.exit(-1);
        }
        isRoman = String.valueOf(number1.charAt(0)).equals("I") || String.valueOf(number1.charAt(0)).equals("V") || String.valueOf(number1.charAt(0)).equals("X");
        if (isRoman)
        {
            num1 = RomanToArabic(number1);
        }
        else
        {
            num1 = TryParseInt(number1);
        }
        if (!NumberIsInRange(num1, 1, 10))
        {
            System.out.println("Number not in range");
            System.exit(-1);
        }

        try
        {
            operation = CutStringTillGivenSymbol(input, number1.length() + 1, String.valueOf(input.charAt(number1.length() + 2)));
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.out.println("Incorrect syntax");
            System.exit(-1);
        }

        try
        {
            number2 = CutStringTillGivenSymbol(input, number1.length() + 3, "");
        }
        catch (StringIndexOutOfBoundsException e)
        {
            System.out.println("Incorrect syntax");
            System.exit(-1);
        }

        if (isRoman)
        {
            num2 = RomanToArabic(number2);
        }
        else
        {
            num2 = TryParseInt(number2);
        }
        if (!NumberIsInRange(num2, 1, 10)) {

            System.out.println("Number not in range");
            System.exit(-1);
        }
        if (isRoman)
        {
            return String.valueOf(ArabicToRoman(Calculate(num1,num2, operation)));
        }
        else
        {
            return String.valueOf(Calculate(num1, num2, operation));
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String equation = sc.nextLine();
        System.out.println(calc(equation));
    }
}