@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException
import java.lang.StringBuilder
import kotlin.math.exp
import kotlin.math.max

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val number = listOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )
    try {
        val splitted = str.split(" ")
        val a1 = splitted[0].toInt()
        val a2 = number.indexOf(splitted[1]) + 1
        val a3 = splitted[2]
        if (a2 == 0 || daysInMonth(a2, a3.toInt()) < a1 || splitted.size != 3) return ""
        return "${twoDigitStr(a1)}.${twoDigitStr(a2)}.$a3"
    } catch (e: IndexOutOfBoundsException) {
        return ""
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val number = listOf(
        "января",
        "февраля",
        "марта",
        "апреля",
        "мая",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октября",
        "ноября",
        "декабря"
    )
    try {
        val splitted = digital.split(".")
        val a1 = splitted[0].toInt().toString()
        val a2 = number[splitted[1].toInt() - 1]
        val a3 = splitted[2]
        if (splitted[1].toInt() == 0 ||
            daysInMonth(splitted[1].toInt(), a3.toInt()) < a1.toInt() ||
            splitted.size != 3
        ) return ""
        return "$a1 $a2 $a3"
    } catch (e: IndexOutOfBoundsException) {
        return ""
    } catch (e: NumberFormatException) {
        return ""
    }
}


/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val phone = phone.replace("[ -]".toRegex(), "")
    if (!phone.matches(Regex("""(\+\d+)?(\(\d+\))?\d+"""))) return ""
    return phone.replace("[()]".toRegex(), "")

}

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var jumps = jumps.replace(" %", "")
    jumps = jumps.replace(" -", "")
    return try {
        jumps.split(" ").map { it.toInt() }.max()
    } catch (e: NumberFormatException) {
        -1
    }
}

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (!jumps.matches(Regex("""(\d+ [+%-]+\s?)+"""))) return -1
    val temp = jumps.split(" ")
    var answer = -1
    var i = 0
    do {
        if ('+' in temp[i + 1]) answer = max(answer, temp[i].toInt())
        i += 2
    } while (i != temp.size)
    return answer
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (!expression.matches(Regex("""\d+( [+-] \d+)*"""))) throw IllegalArgumentException()
    var answer: Int
    val expression = expression.split(" ")
    answer = expression.first().toInt()
    var i = 1
    while (i < expression.size) {
        if ("+" == expression[i]) answer += expression[i + 1].toInt()
        else if ("-" == expression[i]) answer -= expression[i + 1].toInt()
        i += 2
    }
    return answer
}

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val list = str.split(" ").map { it.lowercase() }
    var answer = 0
    for (i in 0..list.size - 2) {
        if (list[i] == list[i + 1]) return answer
        answer += list[i].length + 1
    }
    return -1
}

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String {
    val list = description.replace(";", "").split(" ")
    var answer = 0.0
    var answer2 = ""
    for (i in 1 until list.size step 2) {
        if (list[i].toDouble() >= answer) {
            answer = list[i].toDouble()
            answer2 = list[i - 1]
        }
    }
    return answer2
}

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    var control = roman
    val fromRoman = mapOf(
        'M' to 1000,
        'D' to 500,
        'C' to 100,
        'L' to 50,
        'X' to 10,
        'V' to 5,
        'I' to 1
    )
    for (i in fromRoman.keys) {
        control = control.replace(i.toString(), "")
    }
    if (control != "" || roman == "") return -1
    var ans = 0
    for (i in 0 until roman.length - 1) {
        if (fromRoman[roman[i]]!! < fromRoman[roman[i + 1]]!!) ans -= fromRoman[roman[i]]!!
        else ans += fromRoman[roman[i]]!!
    }
    return ans + fromRoman[roman[roman.length - 1]]!!
}

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val symbols = listOf<Char>('<', '>', '+', '[', ']', '-', ' ')
    var control = commands
    for (i in symbols) {
        control = control.replace(i.toString(), "")
    }
    var ctrl = 0
    for (i in commands) {
        if (i == '[') ctrl += 1
        else if (i == ']') ctrl -= 1
        if (ctrl < 0) throw IllegalArgumentException()
    }
    if (ctrl != 0 || control != "") throw IllegalArgumentException()
    val temporary = mutableListOf<Int>()
    val bracket1 = mutableListOf<Int>()
    val bracket2 = mutableListOf<Int>()
    for (i in commands.indices) {
        if (commands[i] == '[') temporary.add(i)
        else if (commands[i] == ']') {
            bracket1.add(temporary.last())
            bracket2.add(i)
            temporary.removeLast()
        }
    }
    val list = MutableList(size = cells) { 0 }
    if (commands == "") return list
    var i = 0
    var j = cells / 2
    var cnt = 0
    try {
        while (cnt < limit) {
            if (commands[i] == '<') j -= 1
            else if (commands[i] == '>') j += 1
            else if (commands[i] == '+') list[j] += 1
            else if (commands[i] == '-') list[j] -= 1
            else if (commands[i] == '[') {
                if (list[j] == 0) {
                    i = bracket2[bracket1.indexOf(i)]
                }
            } else if (commands[i] == ']') {
                if (list[j] != 0) {
                    i = bracket1[bracket2.indexOf(i)]
                }
            }
            if (j >= list.size || j < 0) throw IllegalStateException()
            if (i == commands.length - 1) break
            i++
            cnt++
        }
    } catch (e: IndexOutOfBoundsException) {
        throw IllegalStateException()
    }
    return list
}

/*fun ak47(table: Map<String, Int>, taxes: String): List<String> {
    val ans = mutableMapOf<String, Int>()
    val taxesToList = taxes.split('\n')
    for (i in taxesToList) {
        if (!i.matches(Regex("""[ ЁёА-яЯ]+ - [ ЁёА-яЯ]+ - \d+"""))) throw IllegalArgumentException()
        else {
            ans[i.split(" - ")[0]] = (i.split(" - ")[2]).toInt() * (table.getOrDefault(i.split(" - ")[1], 13)) / 100
        }
    }
    return ans.keys.sortedBy { ans[it] }
}*/