package com.example.cmp_mvi_template.core.domain


class Paginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> ResultWrapper<Item, DataError>,
    private val getNextKey: suspend (currentKey: Key, result: Item) -> Key,
    private val onError: suspend (DataError) -> Unit,
    private val onSuccess: suspend (result: Item, newKey: Key) -> Unit,
    private val endReached: (currentKey: Key, result: Item) -> Boolean
) {

    private var currentKey = initialKey
    private var isMakingRequest = false
    private var isEndReached = false

    suspend fun loadNextItems() {
        if(isMakingRequest || isEndReached) {
            return
        }

        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey)
        isMakingRequest = false

        result.onError {
            onError(it)
            onLoadUpdated(false)
            return
        }.onSuccess {item->
            currentKey = getNextKey(currentKey, item)

            onSuccess(item, currentKey)

            onLoadUpdated(false)

            isEndReached = endReached(currentKey, item)
        }
    }

    fun reset() {
        currentKey = initialKey
        isEndReached = false
    }
}