package com.daon.music_part4_02.service

data class PlayerModel(
    private val playMusicList: List<MusicModel> = emptyList(),
    var currentPosition: Int = -1,
    var isWatchingPlayListView: Boolean = true
) {
    // playMusicList 접근자
    fun getAdapterModels() : List<MusicModel> {
        return playMusicList.mapIndexed{ index, musicModel ->
            val newItem = musicModel.copy(
                isPlaying = index == currentPosition
            )
            newItem
        }
    }

    fun updateCurrentPosition(musicModel: MusicModel) {
        currentPosition = playMusicList.indexOf(musicModel)
    }

    fun getNextMusic(): MusicModel? {
        if (playMusicList.isEmpty()) return null
        currentPosition = if ((currentPosition+1) == playMusicList.size) 0 else currentPosition+1
        return playMusicList[currentPosition]
    }

    fun getPrevMusic(): MusicModel? {
        if (playMusicList.isEmpty()) return null
        currentPosition = if ((currentPosition-1) == -1) playMusicList.lastIndex else currentPosition-1
        return playMusicList[currentPosition]
    }

    fun currentMusicModel(): MusicModel? {
        if (playMusicList.isEmpty()) return null
        return playMusicList[currentPosition]
    }
}
