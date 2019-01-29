package com.puzzlebench.clean_marvel_kotlin.data.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models to outer data source layers
 *
 * @param <E> the remote model input type
 * @param <D> the cached model input type
 */
interface BaseMapperResponse<E, D> {
    fun transform(type: E): D
}
