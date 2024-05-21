package org.example.search.algorith

import kotlin.math.max

class MaxHeap {
    private val heapArray = mutableListOf<Int>()

    fun insert(value: Int) {
        heapArray.add(value)
        heapifyUp(heapArray.size - 1)
    }

    private fun heapifyUp(index: Int) {
        var currentIndex = index
        while (currentIndex > 0) {
            val parentIndex = (currentIndex - 1) / 2
            if (heapArray[currentIndex] > heapArray[parentIndex]) {
                // Swap elements if the current element is greater than its parent
                swap(currentIndex, parentIndex)
                currentIndex = parentIndex
            } else {
                break
            }
        }
    }

    fun extractMax(): Int? {
        if (heapArray.isEmpty()) {
            return null
        }

        val maxValue = heapArray[0]
        heapArray[0] = heapArray.last()
        heapArray.removeAt(heapArray.size - 1)
        heapifyDown(0)

        return maxValue
    }

    private fun heapifyDown(index: Int) {
        var currentIndex = index
        while (true) {
            val leftChildIndex = 2 * currentIndex + 1
            val rightChildIndex = 2 * currentIndex + 2
            var largestIndex = currentIndex

            if (leftChildIndex < heapArray.size && heapArray[leftChildIndex] > heapArray[largestIndex]) {
                largestIndex = leftChildIndex
            }

            if (rightChildIndex < heapArray.size && heapArray[rightChildIndex] > heapArray[largestIndex]) {
                largestIndex = rightChildIndex
            }

            if (currentIndex != largestIndex) {
                // Swap elements if the current element is smaller than the largest child
                swap(currentIndex, largestIndex)
                currentIndex = largestIndex
            } else {
                break
            }
        }
    }

    private fun swap(i: Int, j: Int) {
        val temp = heapArray[i]
        heapArray[i] = heapArray[j]
        heapArray[j] = temp
    }

    fun printHeap() {
        println(heapArray)
    }
}