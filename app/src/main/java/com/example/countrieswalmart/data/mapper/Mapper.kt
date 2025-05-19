package com.example.countrieswalmart.data.mapper

interface Mapper<Entity, Domain> {
    fun mapToDomain(type: Entity): Domain
}