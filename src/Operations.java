public class Operations {

    public Operations(){
    }

    public void addition(int fNum, int sNum) {
        int sum = fNum^sNum;
        System.out.println("> Результат сложения: " + Integer.toBinaryString(fNum) + " + "
                + Integer.toBinaryString(sNum) + " = " + Integer.toBinaryString(sum));
    }

    public void subtraction(int fNum, int sNum) {
        int sub =  fNum^sNum;
        System.out.println("> Результат вычитания: " + Integer.toBinaryString(fNum) + " - "
                + Integer.toBinaryString(sNum) + " = " + Integer.toBinaryString(sub));
    }

    public void multiplication(int fNum, int sNum) {
        final int m = 8 ;     // степень полинома  x8 + x4 + x3 + x2 + 1
        final int  n  = 256;    // n=2**m-1
        final int GF = 256;
        int[] p = new int[] {1, 0, 1, 1, 1, 0, 0, 0, 1};
        int[] alpha_to = new int[n + 1];        // таблица степеней примитивного члена
        int[] index_of= new int[n + 1];        // индексная таблица для быстрого умножения
        int i, mask;
        mask = 1; alpha_to[m] = 0;
        for (i = 0; i < m; i++) {
            alpha_to[i] = mask;
            index_of[alpha_to[i]] = i;
            if (p[i] != 0)
                Math.pow(alpha_to[m], mask);
            mask <<= 1;
        }
        index_of[alpha_to[m]] = m;
        mask >>= 1;

        for (i = m+1; i < n; i++) {
            if (alpha_to[i-1] >= mask)
                alpha_to[i] = (int) Math.pow(alpha_to[m], ((int)Math.pow(alpha_to[i-1],mask) << 1));
            else
                alpha_to[i] = alpha_to[i-1]<<1;
            index_of[alpha_to[i]] = i;
        }
        index_of[0] = -1;

        //умножение
            int sum;
            if (fNum == 0 || sNum == 0)
                System.out.println("> Результат умножения: 0");
            sum = alpha_to[fNum] + alpha_to[sNum];  // вычисляем сумму индексов полиномов
            if (sum >= GF-1)
                sum -= GF-1;     // приводим сумму к модулю
            int mult =  index_of[sum];             // переводим результат в полиномиальную форму
        System.out.println("> Результат умножения по модулю: " + fNum + " * " + sNum +
                " = " + mult);
    }

    public void division(int fNum, int sNum) {
        final int m = 8 ;     // степень полинома  x8 + x4 + x3 + x2 + 1
        final int  n  = 256;    // n=2**m-1   (длина кодового слова)
        final int GF = 256;
        int[] p = new int[] {1, 0, 1, 1, 1, 0, 0, 0, 1};
        int[] alpha_to = new int[n + 1];        // таблица степеней примитивного члена
        int[] index_of= new int[n + 1];        // индексная таблица для быстрого умножения
        int i, mask;
        mask = 1; alpha_to[m] = 0;
        for (i = 0; i < m; i++) {
            alpha_to[i] = mask;
            index_of[alpha_to[i]] = i;
            if (p[i] != 0)
                Math.pow(alpha_to[m], mask);
            mask <<= 1;
        }
        index_of[alpha_to[m]] = m;
        mask >>= 1;

        for (i = m+1; i < n; i++) {
            if (alpha_to[i-1] >= mask)
                alpha_to[i] = (int) Math.pow(alpha_to[m], ((int)Math.pow(alpha_to[i-1],mask) << 1));
            else
                alpha_to[i] = alpha_to[i-1]<<1;
            index_of[alpha_to[i]] = i;
        }
        index_of[0] = -1;

        //деление
        int diff;
        if (fNum == 0)
            System.out.println("> Результат деления: 0");
        if (sNum == 0)
            System.out.println("Ошибка: деление на 0 невозможно");
        diff = alpha_to[fNum] - alpha_to[sNum]; // вычисляем разность индексов
        if (diff < 0) diff += GF-1;       // приводим разность к модулю
        int div = index_of[diff];            // переводим результат в полиномиальную…
        System.out.println("> Результат деления: " + fNum + " / " + sNum + " = " + div);
    }

    public void additiveInverse(int fNum, int sNum) {
        System.out.println("> Аддитивная инверсия  полинома есть сам полином: " + fNum +
                " и " + sNum);
    }
}