package com.yes.trackdiialogfeature.data.repository.entity

import com.yes.trackdiialogfeature.data.mapper.Mapper
import com.yes.trackdiialogfeature.domain.MediaItem

class MediaMapper:Mapper<MediaItem,MediaEntity> {
    override fun mapToDomain(repositoryEntity: ArrayList<MediaEntity>): ArrayList<MediaItem> {
        val items= arrayListOf<MediaItem>()
        for (i in 0 until repositoryEntity.size){
            val mediaItem=MediaItem()
            mediaItem.title=repositoryEntity[i].title
            mediaItem.title=repositoryEntity[i].categoryName
            mediaItem.album=repositoryEntity[i].album
            mediaItem.uri= repositoryEntity[i].uri
            mediaItem.artist= repositoryEntity[i].artist
            mediaItem.genre=repositoryEntity[i].genre
            items+=mediaItem
        }

        return items
    }

    override fun matToRepository(domainEntity: ArrayList<MediaItem>): ArrayList<MediaEntity> {
        TODO("Not yet implemented")
    }
}