import kotlin.system.exitProcess

class Game {
    private var player: Player? = null
    private var monster: Monster? = null

    //начало игры
    fun start() {
        println("----------------------------------------------------------------")
        println("1. Играть")
        println("2. Выход")
        print("Введите цифру: ")
        when (readLine()?.toIntOrNull()) {
            1 -> startGame()
            2 -> exitProcess(0)
            else -> println("Недопустимый вариант")
        }
    }

    //стартовый экран
    private fun startGame() {
        while (true) {
            println("----------------------------------------------------------------")
            println("1. Просмотр персонажа")
            println("2. Создать нового персонажа")
            println("3. Начать сражение")
            println("4. Выпить зелье")
            println("5. Выход")
            print("Введите цифру: ")

            when (readLine()?.toIntOrNull()) {
                1 -> displayPlayerStatus()
                2 -> createNewPlayer()
                3 -> startBattle()
                4 -> healing()
                5 -> return
                else -> println("Недопустимый вариант")
            }
        }
    }

    //просмотр статуса игрока
    private fun displayPlayerStatus() {
        if (player != null) {
            println("----------------------------------------------------------------")
            player!!.showStatus()
        } else {
            println("----------------------------------------------------------------")
            println("Персонаж не создан. Создайте нового персонажа с помощью опции 2.")
        }
    }

    //создание нового персонажа
    private fun createNewPlayer() {
        println("----------------------------------------------------------------")
        println("Новый персонаж создан.")
        player = Player.createPlayer()
        player!!.showStatus()
    }

    //начать сражение
    private fun startBattle() {
        if (player != null) {
            monster = Monster.createMonster()
            println("----------------------------------------------------------------")
            monster!!.showStatus()

            battleLoop@ while (true) {
                println("----------------------------------------------------------------")
                println("1. Начать атаку")
                println("2. Сбежать")
                print("Введите цифру: ")

                when (readLine()?.toIntOrNull()) {
                    1 -> {
                        handlePlayerAttack()
                        if (monster!!.health <= 0) {
                            monster!!.health = 0
                            handlePlayerVictory()
                            break@battleLoop
                        }
                        handleMonsterAttack()
                    }

                    2 -> break@battleLoop
                    else -> println("Недопустимый вариант")
                }
            }
        } else {
            println("----------------------------------------------------------------")
            println("Персонаж не создан. Создайте нового персонажа с помощью опции 2.")
        }
    }

    //исцеление
    private fun healing() {
        if (player == null) {
            println("----------------------------------------------------------------")
            println("Персонаж не создан. Создайте нового персонажа с помощью опции 2.")
            return
        }

        if (player!!.health == 100) {
            println("----------------------------------------------------------------")
            println("У тебя здоровье 100HP. Ты хочешь просто потерять зелье?")
            println("1. Да")
            println("2. Нет")
            print("Введите цифру: ")
            when (readLine()?.toIntOrNull()) {
                1 -> player!!.easterEgg()
                2 -> return
                else -> println("Недопустимый вариант")
            }
        } else {
            player!!.heal()
            println("----------------------------------------------------------------")
            player!!.showStatus()
        }
    }


    //атака игрока
    private fun handlePlayerAttack() {
        val attackModifierPlayer = player!!.attackModifier(monster!!.defense)
        val rollDicePlayer = player!!.rollDice(attackModifierPlayer)
        if (rollDicePlayer) {
            println("----------------------------------------------------------------")
            println("Ваша атака удалась :)")
            player!!.attack(monster!!, player!!.damageRange.random())
            println("----------------------------------------------------------------")
            if (monster!!.health <= 0) {
                monster!!.health = 0
                monster!!.showStatus()
            } else monster!!.showStatus()
        } else println("Атака не удалась :(")
    }

    //победа игрока
    private fun handlePlayerVictory() {
        println("Вы победили! Уровень повышен.")
        Player.levelUp(player!!, 1)
    }

    //атака монстра
    private fun handleMonsterAttack() {
        println("----------------------------------------------------------------")
        println("Очередь монстра:")
        val attackModifierMonster = monster!!.attackModifier(player!!.defense)
        val rollDiceMonster = monster!!.rollDice(attackModifierMonster)
        if (rollDiceMonster) {
            println("----------------------------------------------------------------")
            println("Монстр смог нанести вам урон :(")
            monster!!.attack(player!!, monster!!.damageRange.random())
            if (player!!.health <= 0) {
                player!!.health = 0
                player!!.showStatus()
                println("Вы проиграли! Ваш персонаж умер.")
                player = null
            } else println("----------------------------------------------------------------")
            player!!.showStatus()
        } else println("Монстр не смог нанести вам урон :)")
    }
}