import kotlin.random.Random
import kotlin.random.nextInt

class Player(
    attack: Int,
    defense: Int,
    health: Int,
    damageRange: IntRange,
    var level: Int,
    private val maxHealth: Int
) :
    Creature(attack, defense, health, damageRange) {
    private var numberOfHealsRemaining = 4

    fun heal() {
        if (numberOfHealsRemaining > 0) {
            val healingAmount = (maxHealth * 0.3).toInt()
            if (health <= 70) {
                health += healingAmount
            } else health = 100
            numberOfHealsRemaining--
            println("Исцеление успешно! Ваше здоровье: $health HP. Осталось исцелений: $numberOfHealsRemaining")
        } else {
            println("У вас больше нет доступных исцелений.")
        }
    }

    fun easterEgg() {
        println("Поздравляю! Ты просто потерял зелье ;)")
        numberOfHealsRemaining--
        println("Их осталось - $numberOfHealsRemaining")
    }

    fun showStatus() {
        println("Показатели игрока:")
        println("Уровень: $level")
        println("Атака: $attack")
        println("Защита: $defense")
        println("Здоровье: $health")
    }

    companion object {
        fun createPlayer(): Player {
            val attack: Int = Random.nextInt(1..30)
            val defense: Int = Random.nextInt(1..30)
            val health = 100
            val damageRange: IntRange = 1..30
            val level = 1
            val maxHealth = 100
            return Player(attack, defense, health, damageRange, level, maxHealth)
        }

        fun levelUp(player: Player, levelsToGain: Int) {
            player.level += levelsToGain
        }
    }
}