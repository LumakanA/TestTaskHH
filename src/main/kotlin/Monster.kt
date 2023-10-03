import kotlin.random.Random
import kotlin.random.nextInt

class Monster(attack: Int, defense: Int, health: Int, damageRange: IntRange) :
    Creature(attack, defense, health, damageRange) {
    fun showStatus() {
        println("Показатели монстра:")
        println("Атака: $attack")
        println("Защита: $defense")
        println("Здоровье: $health")
    }

    companion object {
        fun createMonster(): Monster {
            val attack: Int = Random.nextInt(1..30)
            val defense: Int = Random.nextInt(1..30)
            val health = 100
            val damageRange: IntRange = 1..30
            return Monster(attack, defense, health, damageRange)
        }
    }
}