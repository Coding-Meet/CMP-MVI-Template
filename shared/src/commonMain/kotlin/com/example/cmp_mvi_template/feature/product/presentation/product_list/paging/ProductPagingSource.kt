package com.example.cmp_mvi_template.feature.product.presentation.product_list.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.core.utility.AppLogger
import com.example.cmp_mvi_template.feature.product.domain.entity.Product
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductSortOption
import com.example.cmp_mvi_template.feature.product.domain.usecase.GetProductListUseCase

class ProductPagingSource(
    private val getProductListUseCase: GetProductListUseCase,
    private val category: String?,
    private val searchQuery: String?,
    private val sortOption: ProductSortOption
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val skip = params.key ?: 0
        val limit = params.loadSize

        AppLogger.d("ProductPagingSource") {
            "load skip: $skip, limit: $limit, cat: $category, search: $searchQuery, sort: $sortOption"
        }

        return when (
            val result = getProductListUseCase(
                limit = limit,
                skip = skip,
                category = category,
                searchQuery = searchQuery?.ifBlank { null },
                sortBy = sortOption.sortBy,
                order = sortOption.order
            )
        ) {
            is ResultWrapper.Success -> {
                val response = result.data
                val products = response.products
                val nextKey = if (response.skip + response.limit >= response.total || products.isEmpty()) {
                    null
                } else {
                    response.skip + products.size
                }
                val prevKey = if (skip == 0) null else maxOf(0, skip - limit)
                LoadResult.Page(
                    data = products,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            is ResultWrapper.Error -> {
                LoadResult.Error(Exception(result.error.toString()))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize)
                ?: anchorPage?.nextKey?.minus(state.config.pageSize)
        }
    }
}
