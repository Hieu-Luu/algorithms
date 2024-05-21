package org.example.search.algorith

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*

fun main() {
    val graph = Graph(6)
    graph.addEdge(0,1)
    graph.addEdge(0, 2)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    graph.addEdge(2, 5)

    println("BFS starting form vertex 0:")
    graph.bfs(0)

    println("Shortest path: ${graph.shortestPath(0, 5)}")

    println("Depth-First Traversal starting from vertex 0:")
    graph.dfs(0)
    println()

    val minHeap = MinHeap()
    minHeap.insert(4)
    minHeap.insert(2)
    minHeap.insert(8)
    minHeap.insert(1)
    minHeap.insert(7)
    minHeap.printHeap()

    println("Extracted min value: ${minHeap.extractMin()}")
    minHeap.printHeap()

    val maxHeap = MaxHeap()
    maxHeap.insert(4)
    maxHeap.insert(2)
    maxHeap.insert(8)
    maxHeap.insert(1)
    maxHeap.insert(7)
    maxHeap.printHeap()

    println("Extracted max value: ${maxHeap.extractMax()}")
    maxHeap.printHeap()

    println("Floyd Marshall:")
    val g = arrayOf(
        intArrayOf(0, 5, Int.MAX_VALUE, 10),
        intArrayOf(Int.MAX_VALUE, 0, 3, Int.MAX_VALUE),
        intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE, 0, 1),
        intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, 0)
    )
    println("Distance table:")
    for (row in g) {
        for (distance in row) {
            if (distance == Int.MAX_VALUE) {
                print("INF\t")
            } else {
                print("$distance\t")
            }
        }
        println()
    }

    val result = floydWarshall(g)

    // In ma trận khoảng cách ngắn nhất
    println("\nShortest Distance table:")
    for (row in result) {
        for (distance in row) {
            if (distance == Int.MAX_VALUE) {
                print("INF\t")
            } else {
                print("$distance\t")
            }
        }
        println()
    }

//    runBlocking {
//        val flow = hotFlow()
//        println("Hot flow")
//        flow.collect { value -> println(value) } // Bắt đầu chảy từ thời điểm hiện tại và in ra các giá trị
//        delay(2000)
//        println("Continue hot flow")
//        flow.collect { value -> println(value) } // Tiếp tục từ thời điểm hiện tại và in ra các giá trị mới
//    }
//
//    runBlocking {
//        val flow = coldFlow()
//        println("Cold flow")
//        flow.collect { value -> println(value) } // Bắt đầu chảy từ đầu và in ra các giá trị
//        delay(2000)
//        println("Continue cold flow")
//        flow.collect { value -> println(value) } // Bắt đầu lại từ đầu và in ra các giá trị
//    }

//    runBlocking {
//        println("flow conflate")
//        val flow = simpleFlow()
//
//        // Collector 1
//        launch {
//            flow.conflate().collect { value ->
//                delay(300) // Collector chậm hơn
//                println("Collector 1: $value")
//            }
//        }
//
//        // Collector 2
//        launch {
//            flow.collect { value ->
//                println("Collector 2: $value")
//            }
//        }
//    }

//    runBlocking {
//        flow.combine(flow2) { i, s -> i.toString() + s }.collect {
//            println(it) // Will print "1a 2a 2b 2c"
//        }
//    }

//    runBlocking {
//        val userIdFlow = (1..5).asFlow().onEach { delay(300) }
//
//        val combinedFlow = userIdFlow
//            .combineTransform(fetchUserStatus) { userId, isUserActive ->
//                if (isUserActive) {
//                    emit("User $userId is active")
//                    val userData = fetchUser(userId).single() // Đợi lấy thông tin người dùng
//                    emit("User data: $userData")
//                } else {
//                    emit("User $userId is inactive")
//                }
//            }
//
//        combinedFlow.collect { value ->
//            println(value)
//        }
//    }

//    runBlocking {
//        channelFlowExample().collect { value -> println("ChannelFlow: $value") }
//
//        println("--------")
//
//        callbackFlowExample().collect { value -> println("CallbackFlow: $value") }
//        callbackFlowExample().cancellable()
//    }

//    runBlocking {
//        val channel = Channel<Int>()
//        launch {
//            // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
//            for (x in 1..5) channel.send(x * x)
//            channel.close() // we're done sending
//        }
//
//        // here we print five received integers:
//        for (y in channel) println(y)
//        println("Done!")
//    }

//    runBlocking {
//        val numbers = produceNumbers() // produces integers from 1 and on
//        val squares = square(numbers) // squares integers
//        repeat(5) {
//            println(squares.receive()) // print first five
//        }
//        println("Done!") // we are done
//        coroutineContext.cancelChildren() // cancel children coroutines
//    }

//    runBlocking {
//        var cur = numbersFrom(2)
//        repeat(10) {
//            val prime = cur.receive()
//            println(prime)
//            cur = filter(cur, prime)
//        }
//        coroutineContext.cancelChildren() // cancel all children to let main finish
//    }

//    runBlocking {
//        val producer = produceNumbers()
//        repeat(5) { launchProcessor(it, producer) }
//        delay(950)
//        producer.cancel() // cancel producer coroutine and thus kill them all
//    }

//    runBlocking {
//        val channel = Channel<Int>(4) // create buffered channel
//        val sender = launch { // launch sender coroutine
//            repeat(10) {
//                println("Sending $it") // print before sending each element
//                channel.send(it) // will suspend when buffer is full
//            }
//        }
//        // don't receive anything... just wait....
//        delay(1000)
//        sender.cancel() // cancel sender coroutine
//    }

//    runBlocking {
//        val table = Channel<Ball>() // a shared table
//        launch { player("ping", table) }
//        launch { player("pong", table) }
//        table.send(Ball(0)) // serve the ball
//        delay(1000) // delay 1 second
//        coroutineContext.cancelChildren() // game over, cancel them
//    }

//    runBlocking {
//        // Khởi tạo một channel có kiểu dữ liệu là Int
//        val channel = Channel<Int>()
//
//        // Tạo một coroutine gửi dữ liệu vào channel
//        val sender = launch {
//            for (i in 1..5) {
//                println("Sending $i")
//                channel.send(i)
//            }
//            // Đóng channel sau khi gửi xong
//            channel.close()
//        }
//
//        // Tạo một coroutine nhận dữ liệu từ channel
//        val receiver = launch {
//            // Thực hiện việc nhận dữ liệu từ channel cho đến khi channel đóng
//            for (value in channel) {
//                println("Received $value")
//            }
//        }
//
//        val receiver2 = launch {
//            // Thực hiện việc nhận dữ liệu từ channel cho đến khi channel đóng
//            for (value in channel) {
//                println("Receiver 2 Received $value")
//            }
//        }
//
//        // Chờ cả hai coroutine hoàn thành
//        sender.join()
//        receiver.join()
//        receiver2.join()
//    }

//    runBlocking {
//        val job = GlobalScope.launch {
//            println("Throwing exception from launch")
//            throw IndexOutOfBoundsException()
//        }
//        job.join()
//        println("Joined failed job")
//
//        val deferred = GlobalScope.async {
//            println("Throwing exception from async")
//            throw ArithmeticException()
//        }
//        try {
//            deferred.await()
//            println("Unreached")
//        } catch (e: ArithmeticException) {
//            println("Caught ArithmeticException")
//        }
//
//        val handler = CoroutineExceptionHandler { _, exception ->
//            println("CoroutineExceptionHandler got $exception")
//        }
//
//        val job2 = GlobalScope.launch(handler) {
//            throw AssertionError()
//        }
//
//        val deferred2 = GlobalScope.async(handler) {
//            throw ArithmeticException()
//        }
//        joinAll(job2, deferred2)
//    }

    runBlocking {
        val numbers = (1..5).asFlow().onEach { delay(300) }
        val strings = flowOf("one", "two", "three", "four", "five").onEach { delay(400) }

        val combinedFlow = numbers.combine(strings) { a, b -> "$a -> $b" }

        combinedFlow.collect { value ->
            println(value)
        }

        // Output:
        // 1 -> one
        // 2 -> one
        // 2 -> two
        // 3 -> two
        // 3 -> three
        // 4 -> three
        // 4 -> four
        // 5 -> four
        // 5 -> five
    }

//    val n: Int = 50
//    println("$n! = ${factorial(n)}")
}

// Quy nạp
fun factorial(n: Int): Int {
    if (n == 0) return 1
    return n * factorial(n - 1)
}

fun linearSearch(arr: IntArray, target: Int): Int {
    for (i in arr.indices) {
        if (arr[i] == target) {
            return i
        }
    }

    return -1
}

fun binarySearch(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            arr[mid] == target -> return mid
            arr[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return -1
}

fun floydWarshall(graph: Array<IntArray>): Array<IntArray> {
    val n = graph.size
    val dist = Array(n) { IntArray(n) }

    // Khởi tạo ma trận khoảng cách
    for (i in 0 until n) {
        for (j in 0 until n) {
            dist[i][j] = graph[i][j]
        }
    }

    // Bắt đầu thuật toán
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE &&
                    dist[i][k] + dist[k][j] < dist[i][j]
                ) {
                    dist[i][j] = dist[i][k] + dist[k][j]
                }
            }
        }
    }

    return dist
}

val flow = flowOf(1, 2).onEach { delay(10) }
val flow2 = flowOf("a", "b", "c").onEach { delay(15) }

fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(100) // Giả định có một công việc chờ đợi
        emit(i)
    }
}

fun hotFlow(): Flow<Int> = callbackFlow {
    for (i in 1..3) {
        delay(1000)
        send(i) // Gửi dữ liệu đến tất cả các collector
    }
    close() // Kết thúc hot flow khi không còn dữ liệu
}

fun coldFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000) // Giả định có một công việc chờ đợi
        emit(i)
    }
}

data class UserData(val userId: Int, val userName: String)

fun fetchUser(userId: Int): Flow<UserData> = flow {
    // Giả định có một hàm fetchUser chạy bất đồng bộ
    delay(1000)
    emit(UserData(userId, "User $userId"))
}

fun fetchUserStatus(userId: Int): Flow<Boolean> = flow {
    // Giả định có một hàm fetchUserStatus chạy bất đồng bộ
    delay(800)
    emit(userId % 2 == 0) // Kết quả true nếu userId là số chẵn, ngược lại là false
}

fun channelFlowExample(): Flow<Int> = channelFlow {
    for (i in 1..3) {
        delay(1000)
        send(i)
    }
}

fun callbackFlowExample(): Flow<Int> = callbackFlow {
    for (i in 1..3) {
        delay(1000)
        send(i)
    }
}

fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100) // wait 0.1s
    } // infinite stream of integers starting from 1
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}

suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

fun CoroutineScope.numbersFrom(start: Int) = produce {
    var x = start
    while (true) send(x++) // infinite stream of integers from start
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce {
    for (x in numbers) if (x % prime != 0) send(x)
}

data class Ball(var hits: Int)

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) { // receive the ball in a loop
        ball.hits++
        println("$name $ball")
        delay(300) // wait a bit
        table.send(ball) // send the ball back
    }
}