import kotlin.random.Random
import kotlin.random.nextInt

open class Creature(val attack: Int, val defense: Int, var health: Int, val damageRange: IntRange) {
    fun attackModifier(targetDefense: Int): Int {
        return attack - targetDefense + 1
    }

    fun rollDice(modifier: Int): Boolean {
        var modifiedModifier = modifier
        if (modifiedModifier <= 0) {
            modifiedModifier = 1  //гарантируем, что хотя бы один кубик будет брошен
        }

        val diceRoll = List(modifiedModifier) { Random.nextInt(1..6) }
        return diceRoll.any { it == 5 || it == 6 }
    }

    fun attack(target: Creature, damage: Int) {
        target.health -= damage
        println("Произведен урон: $damage")
    }
}