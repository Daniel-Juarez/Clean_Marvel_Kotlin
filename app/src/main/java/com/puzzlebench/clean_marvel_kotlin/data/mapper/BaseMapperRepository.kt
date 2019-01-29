package com.puzzlebench.clean_marvel_kotlin.data.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers and innter data source layers
 *
 * @param <E> the domain model input type
 * @param <D> the cached model input type
 */
interface BaseMapperRepository<E, D> {
    fun transform(type: E): D
    fun transformToResponse(type: D): E
}
