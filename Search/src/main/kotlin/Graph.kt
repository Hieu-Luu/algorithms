package org.example.search.algorith

import java.util.LinkedList
import java.util.Queue

class Graph(private val vertices: Int) {
    private val adjacencyList: MutableList<MutableList<Int>> = MutableList(vertices) { mutableListOf() }

    fun addEdge(v: Int, w: Int) {
        adjacencyList[v].add(w)
        adjacencyList[w].add(v)
        println("adjacencyList[$v] ${adjacencyList[v]}")
        println("adjacencyList[$w] ${adjacencyList[w]}")
    }

    fun shortestPath(start: Int, end: Int): List<Int> {
        val queue: Queue<Int> = LinkedList()
        val visited = BooleanArray(vertices)
        val parent = IntArray(vertices) { -1 } // Lưu trữ đỉnh cha để truy vết đường đi ngắn nhất

        queue.add(start)
        visited[start] = true

        while (queue.isNotEmpty()) {
            val currentVertex = queue.poll()

            for (neighbor in adjacencyList[currentVertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true
                    queue.add(neighbor)
                    parent[neighbor] = currentVertex
                }
            }
        }

        parent.forEachIndexed { index, i ->
            println("parent[$index] $i")
        }

        // Truy vết đường đi ngắn nhất từ end đến start
        val shortestPath = mutableListOf<Int>()
        var current = end
        while (current != -1) {
            shortestPath.add(current)
            current = parent[current]
        }
        return shortestPath.reversed()
    }

    fun bfs(startingVertex: Int) {
        val visited = BooleanArray(vertices)
        val queue: Queue<Int> = LinkedList()

        visited[startingVertex] = true
        queue.add(startingVertex)

        while (queue.isNotEmpty()) {
            println("queue $queue")
            val currentVertex = queue.poll()
            println("$currentVertex ")

            for (neighbor in adjacencyList[currentVertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true
                    queue.add(neighbor)
                }
            }
        }
    }

    fun dfs(startingVertex: Int) {
        val visited = BooleanArray(vertices)
        dfsRecursive(startingVertex, visited)
    }

    fun dfsRecursive(vertex: Int, visited: BooleanArray) {
        visited[vertex] = true
        print("$vertex -> ")

        adjacencyList[vertex].forEach {
            if (!visited[it]) {
                dfsRecursive(it, visited)
            }
        }
    }
}