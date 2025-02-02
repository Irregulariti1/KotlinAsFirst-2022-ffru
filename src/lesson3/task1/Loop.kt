@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.math.abs
import kotlin.math.pow


// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/** 
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var n = n
    do {
        count++
        n /= 10
    } while (n != 0)
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var ans = 1
    var n = n - 2
    var temp = 1
    var temp2 = 1
    while (n > 0) {
        n--
        temp2 = ans
        ans += temp
        temp = temp2
    }
    return ans
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..(sqrt(n.toDouble())).toInt()) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    if (n % 2 == 0) return n / 2
    for (i in (n / 2 downTo 1)) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var x = x
    var cnt = 0
    while (x != 1) {
        cnt++
        if (x % 2 == 0) x /= 2
        else x = 3 * x + 1
    }
    return cnt

}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun gcdevklid(m: Int, n: Int): Int {
    var m = m
    var n = n
    while (m != 0 && n != 0) {
        if (m > n) m %= n
        else n %= m
    }
    return max(m, n)
}

fun lcm(m: Int, n: Int): Int = (m * n / gcdevklid(m, n))


/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = lcm(m, n) == m * n

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n = n
    var ans: Long = 0
    var digit: Long = 1
    while (digit <= n) {
        digit *= 10
    }
    digit /= 10
    while (n != 0) {
        ans += n % 10 * digit
        n /= 10
        digit /= 10
    }
    return ans.toInt()
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n % 10 == n) return false
    val first = n % 10
    var n = n / 10
    while (n != 0) {
        if (n % 10 != first) return true
        n /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */

fun sin(x: Double, eps: Double): Double {
    var x = x % (2 * PI)
    var sinx = x
    var member = x
    var ind = 3
    var cnt = 0
    while (abs(member) >= eps) {
        member = x.pow(ind) / factorial(ind)
        member = if (cnt % 2 == 0) member * (-1) else member
        sinx += member
        ind += 2
        cnt += 1
    }
    return sinx
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var x = x % (2 * PI)
    var cosx = 1.0
    var member = 1.0
    var ind = 2
    var cnt = 0
    while (abs(member) >= eps) {
        member = x.pow(ind) / factorial(ind)
        member = if (cnt % 2 == 0) member * (-1) else member
        cosx += member
        ind += 2
        cnt += 1
    }
    return cosx
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revertl(n: Long): Long {
    var n = n
    var ans: Long = 0
    var digit: Long = 1
    while (digit <= n) {
        digit *= 10
    }
    digit /= 10
    while (n != 0L) {
        ans += n % 10 * digit
        n /= 10
        digit /= 10
    }
    return ans
}

//
fun digitIndex(index: Int, digit: Long): Int {
    var cntzero = 0
    var index = index
    var digit = digit
    var length = log10(digit.toDouble()).toInt() + 1
    while (digit % 10 == 0L) {
        cntzero += 1
        digit /= 10
    }
    if (length - cntzero < index) return 0
    else {
        digit = revertl(digit)
        var k = digit % 10
        for (i in index until length) {
            k = digit % 10
            digit /= 10
        }
        return k.toInt()
    }
}

fun squareSequenceDigit(n: Int): Int {
    var ind = 1.0
    var square: Long = 1
    var temp = 1
    if (temp == n) return 1
    while (temp < n) {
        ind += 1
        square = (ind * ind).toLong()
        temp += log10(square.toDouble()).toInt() + 1
    }
    return digitIndex(temp - n, square)
}
//
/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var ind = 1.0
    var fib: Long = 1
    var temp = 1
    if (temp == n) return 1
    while (temp < n) {
        ind += 1
        fib = fib(ind.toInt()).toLong()
        temp += log10(fib.toDouble()).toInt() + 1
    }
    return digitIndex(temp - n, fib)
}