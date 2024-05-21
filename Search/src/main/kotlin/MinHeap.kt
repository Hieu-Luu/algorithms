package org.example.search.algorith

class MinHeap {
    private val heapArray = mutableListOf<Int>()

    fun insert(value: Int) {
        heapArray.add(value)
        heapifyUp(heapArray.size - 1)
    }

    private fun heapifyUp(index: Int) {
        var currentIndex = index
        while (currentIndex > 0) {
            val parentIndex = (currentIndex - 1) / 2
            if (heapArray[currentIndex] < heapArray[parentIndex]) {
                swap(currentIndex, parentIndex)
                currentIndex = parentIndex
            } else {
                break
            }
        }
    }

    fun extractMin(): Int? {
        if (heapArray.isEmpty()) {
           return null
        }

        val minValue = heapArray[0]
        heapArray[0] = heapArray.last()
        heapArray.removeAt(heapArray.size - 1)
        heapifyDown(0)

        return minValue
    }

    private fun heapifyDown(index: Int) {
        var currentIndex = index
        while (true) {
           val leftChildIndex = 2 * currentIndex + 1
           val rightChildIndex = 2 * currentIndex + 2

            var smallestIndex = currentIndex

            if (leftChildIndex < heapArray.size && heapArray[leftChildIndex] < heapArray[smallestIndex]) {
                smallestIndex = leftChildIndex
            }

            if (rightChildIndex < heapArray.size && heapArray[rightChildIndex] < heapArray[smallestIndex]) {
                smallestIndex = rightChildIndex
            }

            if (currentIndex != smallestIndex) {
                // Swap elements if the current element is greater than the smallest child
                swap(currentIndex, smallestIndex)
                currentIndex = smallestIndex
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