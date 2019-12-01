@file:Suppress("ConvertToStringTemplate", "DuplicatedCode", "FunctionName", "LiftReturnOrAssignment", "SimplifyBooleanWithConstants", "SpellCheckingInspection")

package pl.jutupe.bad

val plansza= arrayOf(arrayOf('-', '-', '-'), arrayOf('-', '-', '-'), arrayOf('-', '-', '-'))
var aktualny_gracz = 'O'
var zwyciezca = '?'

fun main() {
    while (true) {
        println("wybierz pole")
        val wyborString = readLine()?.split("")
        val x = wyborString?.get(1)?.toInt()
        val y = wyborString?.get(2)?.toInt()
        println("aktualny gracz: " + aktualny_gracz + " wybral: " + x + ":" + y)

        if (plansza[x!!][y!!] == '-') plansza[x][y] = aktualny_gracz
        else {
            println("zajete pole")
            continue
        }

        for (y1 in 0..2) {
            for (x1 in 0..2) print(plansza[x1][y1])
            println()
        }

        if (czy_ktos_wygral()) {
            zwyciezca = aktualny_gracz
            println("koniec gry, wygrał: " + zwyciezca)
            break
        }

        if (plansza[0][0] != '-' && plansza[0][1] != '-' && plansza[0][2] != '-' &&
                plansza[1][0] != '-' && plansza[1][1] != '-' && plansza[1][2] != '-'
                && plansza[2][0] != '-' && plansza[2][1] != '-' && plansza[2][2] != '-') {
            println("remis, nikt nie wygrał")
        }

        if (aktualny_gracz == 'O') aktualny_gracz = 'X'
        else aktualny_gracz = 'O'
    }
}

private fun czy_ktos_wygral(): Boolean {

    if (plansza[0][0] == 'X' && plansza[0][1] == 'X' && plansza[0][2] == 'X') {
        return true
    }
    if (plansza[1][0] == 'X' && plansza[1][1] == 'X' && plansza[1][2] == 'X') {
        return true
    }
    if (plansza[2][0] == 'X' && plansza[2][1] == 'X' && plansza[2][2] == 'X') {
        return true
    }

    if (plansza[0][0] == 'X' && plansza[1][0] == 'X' && plansza[2][0] == 'X') {
        return true
    }
    if (plansza[0][1] == 'X' && plansza[1][1] == 'X' && plansza[2][1] == 'X') {
        return true
    }
    if (plansza[0][2] == 'X' && plansza[1][2] == 'X' && plansza[2][2] == 'X') {
        return true
    }

    if (plansza[0][0] == 'X' && plansza[1][1] == 'X' && plansza[2][2] == 'X') {
        return true
    }
    if (plansza[0][2] == 'X' && plansza[1][1] == 'X' && plansza[2][0] == 'X') {
        return true
    }

    if (plansza[0][0] == 'O' && plansza[0][1] == 'O' && plansza[0][2] == 'O') {
        return true
    }
    if (plansza[1][0] == 'O' && plansza[1][1] == 'O' && plansza[1][2] == 'O') {
        return true
    }
    if (plansza[2][0] == 'O' && plansza[2][1] == 'O' && plansza[2][2] == 'O') {
        return true
    }

    if (plansza[0][0] == 'O' && plansza[1][0] == 'O' && plansza[2][0] == 'O') {
        return true
    }
    if (plansza[0][1] == 'O' && plansza[1][1] == 'O' && plansza[2][1] == 'O') {
        return true
    }
    if (plansza[0][2] == 'O' && plansza[1][2] == 'O' && plansza[2][2] == 'O') {
        return true
    }

    if (plansza[0][0] == 'O' && plansza[1][1] == 'O' && plansza[2][2] == 'O') {
        return true
    }
    if (plansza[0][2] == 'O' && plansza[1][1] == 'O' && plansza[2][0] == 'O') {
        return true
    }

        return false
    }